package SWRC.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {

    // 회원가입 관련
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 등록된 이메일입니다."),
    INVALID_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, "이메일 형식이 올바르지 않습니다."),
    AUTH_CODE_MISMATCH(HttpStatus.UNAUTHORIZED, "인증번호가 일치하지 않습니다."),
    MISSING_REQUIRED_FIELDS(HttpStatus.BAD_REQUEST, "필수 입력값이 누락되었습니다."),
    MISSING_SPORT_TYPE(HttpStatus.BAD_REQUEST, "관리자는 종목을 선택해야 합니다."),

    // 로그인 관련
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 이메일의 사용자를 찾을 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    INACTIVE_ACCOUNT(HttpStatus.FORBIDDEN, "비활성화된 계정입니다."),

    //리프레시 토큰
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 리프레시 토큰입니다."),

    // 비밀번호 변경 관련
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "새 비밀번호가 서로 일치하지 않습니다."),
    PASSWORD_SAME_AS_OLD(HttpStatus.BAD_REQUEST, "이전과 동일한 비밀번호는 사용할 수 없습니다.");


    private final HttpStatus status;
    private final String message;

    ErrorType(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
