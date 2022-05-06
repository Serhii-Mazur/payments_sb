package mono.it.school.payments.validation;

import mono.it.school.payments.domain.AbstractDomainEntity;

public abstract class AbstractDomainEntityValidation {
    private static final String EMPTY_STRING = "";
    private static final String FIELD_NAME_PATTERN = "field_name";
    private static StringBuffer message = new StringBuffer("Field 'field_name' can't be empty");

//    public abstract boolean validate();

    static boolean isEmpty(String in) {

        return in.equals(EMPTY_STRING);
    }

    static String getExceptionMessage(String fieldName) {
        return message.replace(message.indexOf(FIELD_NAME_PATTERN),
                        message.indexOf(FIELD_NAME_PATTERN) + FIELD_NAME_PATTERN.length(),
                        fieldName)
                .toString();
    }

    static void throwValidationException(String message) throws ValidationException {
        throw new ValidationException(message);
    }

    public static class ValidationException extends Exception {
        public ValidationException() {
            super();
        }

        public ValidationException(String message) {
            super(message);
        }

        public ValidationException(String message, Throwable cause) {
            super(message, cause);
        }

        protected ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }
}
