package mono.it.school.payments.service.impl;

import mono.it.school.payments.domain.Template;
import mono.it.school.payments.repository.TemplateRepository;
import mono.it.school.payments.service.TemplateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TemplateServiceImplTest {

    private TemplateRepository templateRepository;
    private TemplateService templateService;

    @BeforeEach
    void setUp() {
        templateRepository = Mockito.mock(TemplateRepository.class);
        templateService = new TemplateServiceImpl(templateRepository);
    }

    @Test
    void save_ShouldReturnTemplateIfSaved() {
        Template templateToSave = new Template(null,
                UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8"),
                "Payment for electric energy Kyiv, Korotka st. 39",
                "Electricity Korotka",
                "UA903052992990004149123456789");
        Template savedTemplate = new Template(UUID.fromString("840eeaed-c44a-4625-966c-0a81e1cf492a"),
                UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8"),
                "Payment for electric energy Kyiv, Korotka st. 39",
                "Electricity Korotka",
                "UA903052992990004149123456789");

        doReturn(null)
                .when(templateRepository)
                .getByTemplateNameAndAddressId(eq("Electricity Korotka"),
                        eq(UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8")));
        doReturn(savedTemplate).when(templateRepository).save(templateToSave);

        Template actual = templateService.save(templateToSave);

        verify(templateRepository, times(1)).getByTemplateNameAndAddressId(any(), any());
        assertThat(actual.getAddressID()).isEqualTo(templateToSave.getAddressID());
        assertThat(actual.getPaymentPurpose()).isEqualTo(templateToSave.getPaymentPurpose());
        assertThat(actual.getTemplateName()).isEqualTo(templateToSave.getTemplateName());
        assertThat(actual.getIban()).isEqualTo(templateToSave.getIban());
    }

    @Test
    void save_ShouldThrowExceptionIfTemplateExists() {
        Template templateToSave = new Template(null,
                UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8"),
                "Payment for electric energy Kyiv, Korotka st. 39",
                "Electricity Korotka",
                "UA903052992990004149123456789");
        Template existingTemplate = new Template(UUID.fromString("840eeaed-c44a-4625-966c-0a81e1cf492a"),
                UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8"),
                "Payment for electric energy Kyiv, Korotka st. 39",
                "Electricity Korotka",
                "UA903052992990004149123456789");

        doReturn(existingTemplate)
                .when(templateRepository)
                .getByTemplateNameAndAddressId("Electricity Korotka",
                        UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8"));

        TemplateServiceImpl.TemplateServiceException thrown = assertThrows(TemplateServiceImpl.TemplateServiceException.class,
                () -> templateService.save(templateToSave));

        verify(templateRepository, times(1)).getByTemplateNameAndAddressId(any(), any());
        assertNotNull(thrown.getMessage());
    }

    @Test
    void update_ShouldReturnTemplateIfUpdated() {
        Template existingTemplate = new Template(UUID.randomUUID(),
                UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8"),
                "Payment for electric energy Kyiv, Korotka st. 39",
                "Electricity Korotka",
                "UA903052992990004149123456789");

        Template templateToUpdate = new Template(UUID.randomUUID(),
                UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8"),
                "Payment for electric energy Kyiv, Korotka st. 39",
                "Electricity Korotka",
                "UA903052992990004149123456987"); // Difference here

        doReturn(existingTemplate)
                .when(templateRepository)
                .getByTemplateNameAndAddressId("Electricity Korotka",
                        UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8"));
        doReturn(templateToUpdate)
                .when(templateRepository)
                .save(templateToUpdate);

        Template actual = templateService.save(templateToUpdate);

        assertThat(actual.getAddressID()).isEqualTo(UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8"));
        assertThat(actual.getPaymentPurpose()).isEqualTo("Payment for electric energy Kyiv, Korotka st. 39");
        assertThat(actual.getTemplateName()).isEqualTo("Electricity Korotka");
        assertThat(actual.getIban()).isEqualTo("UA903052992990004149123456987");
    }

    @Test
    void update_ShouldThrowExceptionIfTemplateDoesNotExist() {
        Template templateToUpdate = new Template(UUID.randomUUID(),
                UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8"),
                "Payment for electric energy Kyiv, Korotka st. 39",
                "Electricity Korotka",
                "UA903052992990004149123456789");

        doReturn(null)
                .when(templateRepository)
                .getByTemplateNameAndAddressId(any(), any());

        TemplateServiceImpl.TemplateServiceException thrown = assertThrows(TemplateServiceImpl.TemplateServiceException.class,
                () -> templateService.update(templateToUpdate));

        verify(templateRepository, times(1)).getByTemplateNameAndAddressId(any(), any());
        assertNotNull(thrown.getMessage());
    }

    @Test
    void getAll_ShouldReturnEmptyListIfTableIsEmpty() {
        List<Template> emptyTemplateList = Collections.EMPTY_LIST;

        doReturn(emptyTemplateList).when(templateRepository).getAll();

        List<Template> actual = templateService.getAll();

        verify(templateRepository, times(1)).getAll();
        assertTrue(actual.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("getTemplateList")
    void getAll_ShouldReturnTemplateList(List<Template> existingTemplates) {

        doReturn(existingTemplates).when(templateRepository).getAll();

        List<Template> actual = templateService.getAll();

        verify(templateRepository, times(1)).getAll();
        assertFalse(actual.isEmpty());
        assertThat(actual.size()).isEqualTo(existingTemplates.size());
    }

    @ParameterizedTest
    @MethodSource("getTemplateListByAddress")
    void getByAddressID_ShouldReturnTemplateListByAddressID(List<Template> existingTemplates) {

        doReturn(existingTemplates).when(templateRepository).getByAddressID(any());

        List<Template> actual = templateService.getByAddressID(UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8"));

        verify(templateRepository, times(1)).getByAddressID(any());
        assertFalse(actual.isEmpty());
        assertThat(actual.size()).isEqualTo(existingTemplates.size());
    }

    @Test
    void getByAddressID_ShouldReturnEmptyList() {
        List<Template> emptyList = Collections.EMPTY_LIST;

        doReturn(emptyList).when(templateRepository).getByAddressID(any());

        List<Template> actual = templateService.getByAddressID(UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8"));

        verify(templateRepository, times(1)).getByAddressID(any());
        assertTrue(actual.isEmpty());
    }

    @Test
    void getByTemplateNameAndAddressID_ShouldReturnTemplate() {
        Template templateWithID = new Template(UUID.randomUUID(),
                UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8"),
                "Payment for electric energy Kyiv, Korotka st. 39",
                "Electricity Korotka",
                "UA903052992990004149123456789");

        doReturn(templateWithID).when(templateRepository).getByTemplateNameAndAddressId(eq("Electricity Korotka"),
                eq(UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8")));

        Template actual = templateService.getByTemplateNameAndAddressID("Electricity Korotka", UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8"));

        verify(templateRepository, times(1)).getByTemplateNameAndAddressId(any(), any());
        assertThat(actual.getAddressID()).isEqualTo(UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8"));
        assertThat(actual.getTemplateName()).isEqualTo("Electricity Korotka");
        assertThat(actual.getPaymentPurpose()).isEqualTo("Payment for electric energy Kyiv, Korotka st. 39");
        assertThat(actual.getIban()).isEqualTo("UA903052992990004149123456789");
    }

    @Test
    void getByTemplateNameAndAddressID_ShouldReturnNull() {

        doReturn(null).when(templateRepository).getByTemplateNameAndAddressId(any(), any());

        Template actual = templateService.getByTemplateNameAndAddressID(eq("Template_1"),
                eq(UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8")));

        verify(templateRepository, times(1)).getByTemplateNameAndAddressId(any(), any());
        assertNull(actual);
    }

    private static Stream<Arguments> getTemplateList() {
        List<Template> templateList = new ArrayList<>();
        templateList.add(new Template(UUID.randomUUID(),
                UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8"),
                "Payment for electric energy Kyiv, Korotka st. 39",
                "Electricity Korotka",
                "UA903052992990004149123456789"));
        templateList.add(new Template(UUID.randomUUID(),
                UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8"),
                "Payment for heating Kyiv, Korotka st. 39",
                "Heating Korotka",
                "UA903052992990004149123456791"));
        templateList.add(new Template(UUID.randomUUID(),
                UUID.fromString("4544a076-e1ac-401c-a203-2aed1be6de19"),
                "Payment for electric energy Dnipro, Dovga st. 137",
                "Electricity Dovga",
                "UA903052992990004149123456789"));

        return Stream.of(Arguments.of(templateList));
    }

    private static Stream<Arguments> getTemplateListByAddress() {
        List<Template> templateList = new ArrayList<>();
        templateList.add(new Template(UUID.randomUUID(),
                UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8"),
                "Payment for electric energy Kyiv, Korotka st. 39",
                "Electricity Korotka",
                "UA903052992990004149123456789"));
        templateList.add(new Template(UUID.randomUUID(),
                UUID.fromString("3440d09b-e2d7-47fc-b3b2-fdbf6256e2d8"),
                "Payment for heating Kyiv, Korotka st. 39",
                "Heating Korotka",
                "UA903052992990004149123456791"));

        return Stream.of(Arguments.of(templateList));
    }
}