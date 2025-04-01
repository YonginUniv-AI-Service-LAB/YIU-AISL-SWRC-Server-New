package SWRC.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException ex) {
        ErrorType errorType = ex.getErrorType();
        return ResponseEntity
                .status(errorType.getStatus())
                .body(new ErrorResponse(errorType.name(), errorType.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getDefaultMessage())
                .findFirst()
                .orElse("입력값이 유효하지 않습니다.");

        return ResponseEntity
                .status(ErrorType.INVALID_REQUEST.getStatus())
                .body(new ErrorResponse(ErrorType.INVALID_REQUEST.name(), errorMessage));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException e) {
        String errorMessage = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getDefaultMessage())
                .findFirst()
                .orElse("입력값이 유효하지 않습니다.");

        return ResponseEntity
                .status(ErrorType.INVALID_REQUEST.getStatus())
                .body(new ErrorResponse(ErrorType.INVALID_REQUEST.name(), errorMessage));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
        return ResponseEntity
                .status(500)
                .body(new ErrorResponse("INTERNAL_SERVER_ERROR", "서버 내부 오류가 발생했습니다."));
    }

    // 내부 응답 포맷 클래스
    public record ErrorResponse(String code, String message) {}
}
