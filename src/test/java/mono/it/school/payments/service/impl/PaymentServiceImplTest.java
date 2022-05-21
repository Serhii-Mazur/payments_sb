package mono.it.school.payments.service.impl;

import mono.it.school.payments.constants.PaymentStatus;
import mono.it.school.payments.domain.Payment;
import mono.it.school.payments.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    private PaymentRepository paymentRepository;
    private PaymentServiceImpl paymentService;

    @BeforeEach
    void setUp() {
        paymentRepository = Mockito.mock(PaymentRepository.class);
        paymentService = new PaymentServiceImpl(paymentRepository);
    }

    @Test
    void save_ShouldReturnPaymentIfSaved() {
        Payment paymentToSave = new Payment(null,
                "Payment description",
                UUID.fromString("4544a076-e1ac-401c-a203-2aed1be6de19"),
                "4321876511101312",
                123.45F,
                PaymentStatus.NEW,
                null,
                null
        );
        Payment savedPayment = new Payment(UUID.fromString("29351ca2-3dce-42c1-86b4-8e959c76aeed"),
                "Payment description",
                UUID.fromString("4544a076-e1ac-401c-a203-2aed1be6de19"),
                "4321876511101312",
                123.45F,
                PaymentStatus.NEW,
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        doReturn(null)
                .when(paymentRepository)
                .getByTemplateIDAndDescription(any(), any());
        doReturn(savedPayment).when(paymentRepository).save(paymentToSave);

        Payment actual = paymentService.save(paymentToSave);

        verify(paymentRepository, times(1)).getByTemplateIDAndDescription(any(), any());
        assertThat(actual.getTemplateID()).isEqualTo(paymentToSave.getTemplateID());
        assertThat(actual.getDescription()).isEqualTo(paymentToSave.getDescription());
        assertThat(actual.getPaymentAmount()).isEqualTo(paymentToSave.getPaymentAmount());
        assertThat(actual.getCardNumber()).isEqualTo(paymentToSave.getCardNumber());
        assertThat(actual.getPaymentStatus()).isEqualTo(paymentToSave.getPaymentStatus());
    }

    @Test
    void save_ShouldThrowExceptionIfPaymentExists() {
        Payment paymentToSave = new Payment(null,
                "Payment description",
                UUID.fromString("4544a076-e1ac-401c-a203-2aed1be6de19"),
                "4321876511101312",
                123.45F,
                PaymentStatus.NEW,
                null,
                null
        );
        Payment existingPayment = new Payment(UUID.fromString("29351ca2-3dce-42c1-86b4-8e959c76aeed"),
                "Payment description",
                UUID.fromString("4544a076-e1ac-401c-a203-2aed1be6de19"),
                "4321876511101321",  // Difference here
                123.45F,
                PaymentStatus.FAILED,          // Difference here
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().minusHours(3)
        );

        doReturn(existingPayment)
                .when(paymentRepository)
                .getByTemplateIDAndDescription(any(), any());

        PaymentServiceImpl.PaymentServiceException thrown = assertThrows(PaymentServiceImpl.PaymentServiceException.class,
                () -> paymentService.save(paymentToSave));

        verify(paymentRepository, times(1)).getByTemplateIDAndDescription(any(), any());
        assertNotNull(thrown.getMessage());
    }

    @Test
    void update_ShouldReturnPaymentIfUpdated() {
        Payment existingPayment = new Payment(UUID.fromString("29351ca2-3dce-42c1-86b4-8e959c76aeed"),
                "Payment description",
                UUID.fromString("4544a076-e1ac-401c-a203-2aed1be6de19"),
                "4321876511101321",
                123.45F,
                PaymentStatus.NEW,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().minusHours(3)
        );
        Payment paymentToUpdate = new Payment(UUID.fromString("29351ca2-3dce-42c1-86b4-8e959c76aeed"),
                "Payment description",
                UUID.fromString("4544a076-e1ac-401c-a203-2aed1be6de19"),
                "4321876511101321",
                123.45F,
                PaymentStatus.DONE,         // Difference here
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now()         // Difference here
        );

        doReturn(existingPayment)
                .when(paymentRepository)
                .getByTemplateIDAndDescription(any(), any());
        doReturn(paymentToUpdate).when(paymentRepository).save(paymentToUpdate);

        Payment actual = paymentService.update(paymentToUpdate);

        verify(paymentRepository, times(1)).getByTemplateIDAndDescription(any(), any());
        assertThat(actual.getTemplateID()).isEqualTo(paymentToUpdate.getTemplateID());
        assertThat(actual.getDescription()).isEqualTo(paymentToUpdate.getDescription());
        assertThat(actual.getPaymentAmount()).isEqualTo(paymentToUpdate.getPaymentAmount());
        assertThat(actual.getCardNumber()).isEqualTo(paymentToUpdate.getCardNumber());
        assertThat(actual.getPaymentStatus()).isEqualTo(PaymentStatus.DONE);
        assertThat(actual.getCreatedDateTime()).isEqualTo(paymentToUpdate.getCreatedDateTime());
        assertThat(actual.getEtlDateTime()).isEqualTo(paymentToUpdate.getEtlDateTime());
    }

    @Test
    void update_ShouldThrowExceptionIfPaymentDoesNotExist() {
        Payment paymentToUpdate = new Payment(UUID.fromString("29351ca2-3dce-42c1-86b4-8e959c76aeed"),
                "Payment description",
                UUID.fromString("4544a076-e1ac-401c-a203-2aed1be6de19"),
                "4321876511101321",
                123.45F,
                PaymentStatus.DONE,         // Difference here
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now()         // Difference here
        );

        doReturn(null)
                .when(paymentRepository)
                .getByTemplateIDAndDescription(any(), any());

        PaymentServiceImpl.PaymentServiceException thrown = assertThrows(PaymentServiceImpl.PaymentServiceException.class,
                () -> paymentService.update(paymentToUpdate));

        verify(paymentRepository, times(1)).getByTemplateIDAndDescription(any(), any());
        assertNotNull(thrown.getMessage());
    }

    @ParameterizedTest
    @MethodSource("getNewPaymentsList")
    void getByStatus_ShouldReturnPaymentList(List<Payment> payments) {

        doReturn(payments).when(paymentRepository).getByStatus(PaymentStatus.NEW);

        List<Payment> actual = paymentService.getByStatus(PaymentStatus.NEW);

        verify(paymentRepository, times(1)).getByStatus(any());
        assertFalse(actual.isEmpty());
        assertThat(actual.size()).isEqualTo(3);
    }

    @Test
    void getByStatus_ShouldReturnEmptyList() {
        List<Payment> emptyList = Collections.EMPTY_LIST;

        doReturn(emptyList).when(paymentRepository).getByStatus(any());

        List<Payment> actual = paymentService.getByStatus(PaymentStatus.NEW);

        verify(paymentRepository, times(1)).getByStatus(any());
        assertTrue(actual.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("getNewPaymentsList")
    void getAll_ShouldReturnPaymentList(List<Payment> payments) {

        doReturn(payments).when(paymentRepository).getAll();

        List<Payment> actual = paymentService.getAll();

        verify(paymentRepository, times(1)).getAll();
        assertFalse(actual.isEmpty());
        assertThat(actual.size()).isEqualTo(3);
    }

    @Test
    void getAll_ShouldReturnEmptyList() {
        List<Payment> emptyList = Collections.EMPTY_LIST;

        doReturn(emptyList).when(paymentRepository).getAll();

        List<Payment> actual = paymentService.getAll();

        verify(paymentRepository, times(1)).getAll();
        assertTrue(actual.isEmpty());
    }

    private static Stream<Arguments> getNewPaymentsList() {
        List<Payment> newPaymentsList = new ArrayList<>();
        newPaymentsList.add(new Payment(UUID.fromString("29351ca2-3dce-42c1-86b4-8e959c76aeed"),
                "Payment_1",
                UUID.fromString("4544a076-e1ac-401c-a203-2aed1be6de19"),
                "4321876511101321",
                123.45F,
                PaymentStatus.NEW,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().minusHours(3)
        ));
        newPaymentsList.add(new Payment(UUID.fromString("f5383b23-3d9d-42ae-a2d3-dec5c3f2054f"),
                "Payment_2",
                UUID.fromString("42fe9758-97f2-4d49-bd3a-f254bca0ded3"),
                "4321876511101321",
                456.78F,
                PaymentStatus.NEW,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().minusDays(1)
        ));
        newPaymentsList.add(new Payment(UUID.fromString("f90b9bb1-8197-491d-ad7e-86d64fb6b16f"),
                "Payment_3",
                UUID.fromString("93568a85-e4ae-4ee4-b50c-5ca0d253d6a7"),
                "4321876511101321",
                789.12F,
                PaymentStatus.NEW,
                LocalDateTime.now().minusDays(2),
                LocalDateTime.now().minusDays(1)
        ));

        return Stream.of(Arguments.of(newPaymentsList));
    }
}