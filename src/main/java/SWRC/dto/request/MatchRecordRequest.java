// MatchRecordRequest.java
package SWRC.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class MatchRecordRequest {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate matchDate;

    @NotBlank(message = "대회명은 비어 있을 수 없습니다.")
    @Size(max = 100, message = "대회명은 100자 이하로 입력해 주세요.")
    private String tournamentName;

    @NotBlank(message = "상대는 비어 있을 수 없습니다.")
    @Size(max = 50, message = "상대는 50자 이하로 입력해 주세요.")
    private String opponent;

    @Pattern(regexp = "승|무|패", message = "승패 결과는 '승', '무', '패' 중 하나여야 합니다.")
    private String result;

    @Size(max = 255, message = "기타사항은 255자 이하로 입력해 주세요.")
    private String etc;
}
