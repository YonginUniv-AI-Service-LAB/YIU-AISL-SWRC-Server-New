package SWRC.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex) {
        ErrorType errorType = ex.getErrorType();
        return ResponseEntity
                .status(errorType.getStatus())
                .body(new ErrorResponse(errorType.name(), errorType.getMessage()));
    }

    // 내부 응답 포맷 클래스
    record ErrorResponse(String code, String message) {}
}
