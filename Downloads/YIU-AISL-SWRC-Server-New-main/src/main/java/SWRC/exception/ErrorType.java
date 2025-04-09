package SWRC.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorType {

    // ✅ 회원가입 관련
    EMAIL_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 등록된 이메일입니다."),
    INVALID_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, "이메일 형식이 올바르지 않습니다."),
    AUTH_CODE_MISMATCH(HttpStatus.UNAUTHORIZED, "인증번호가 일치하지 않습니다."),
    MISSING_REQUIRED_FIELDS(HttpStatus.BAD_REQUEST, "필수 입력값이 누락되었습니다."),
    MISSING_SPORT_TYPE(HttpStatus.BAD_REQUEST, "관리자는 종목을 선택해야 합니다."),

    // ✅ 로그인 관련
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 이메일의 사용자를 찾을 수 없습니다."),
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다."),
    INACTIVE_ACCOUNT(HttpStatus.FORBIDDEN, "비활성화된 계정입니다."),

    // ✅ 리프레시 토큰
    INVALID_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 리프레시 토큰입니다."),

    // ✅ 비밀번호 변경 관련
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "새 비밀번호가 서로 일치하지 않습니다."),
    PASSWORD_SAME_AS_OLD(HttpStatus.BAD_REQUEST, "이전과 동일한 비밀번호는 사용할 수 없습니다."),

    // ✅ 경기기록 / 체력측정 관련
    MATCH_RECORD_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 경기 기록을 찾을 수 없습니다."),
    ATHLETE_RECORD_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 체력 측정 기록을 찾을 수 없습니다."),
    INVALID_MATCH_DATE_FORMAT(HttpStatus.BAD_REQUEST, "날짜 형식이 올바르지 않습니다."),
    INVALID_RECORD_VALUE(HttpStatus.BAD_REQUEST, "기록 값은 0보다 커야 하며, 소수점 둘째 자리까지 입력해야 합니다."),
    DUPLICATE_MATCH_RECORD(HttpStatus.CONFLICT, "동일한 날짜와 상대의 경기 기록이 이미 존재합니다."),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "요청 값이 유효하지 않습니다."),

    // ✅ 프로필 설정 관련
    INVALID_PROFILE_NAME(HttpStatus.BAD_REQUEST, "이름은 1자 이상 입력해야 합니다."),
    INVALID_PROFILE_HEIGHT(HttpStatus.BAD_REQUEST, "키는 0보다 커야 합니다."),
    INVALID_PROFILE_WEIGHT(HttpStatus.BAD_REQUEST, "몸무게는 0보다 커야 합니다."),


    // ✅ 최고관리자 권한 관련
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "해당 작업을 수행할 권한이 없습니다.");

    private final HttpStatus status;
    private final String message;

    ErrorType(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
