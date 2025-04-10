package SWRC.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {

    private final ErrorType errorType;

    public ApiException(ErrorType errorType) {
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}
