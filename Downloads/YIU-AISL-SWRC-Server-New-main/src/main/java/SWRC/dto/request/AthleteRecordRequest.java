// AthleteRecordRequest.java
package SWRC.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AthleteRecordRequest {

    @NotNull(message = "기록 날짜는 필수입니다.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate recordDate;

    @NotNull(message = "기록 값은 필수입니다.")
    @DecimalMin(value = "0.0", inclusive = false, message = "기록 값은 0보다 커야 합니다.")
    @Digits(integer = 5, fraction = 2, message = "기록 값은 소수점 둘째 자리까지 가능합니다.")
    private Double recordValue;

    @Size(max = 255, message = "특이사항은 255자 이하로 입력해 주세요.")
    private String etc;
}
