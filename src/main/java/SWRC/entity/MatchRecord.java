package SWRC.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "match_records")
public class MatchRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate matchDate;  // 경기 날짜

    @Column(nullable = false)
    private String competitionName;  // 대회명

    @Column(nullable = false)
    private String opponent;  // 상대 선수 또는 팀

    @Column(nullable = false)
    private String result;  // 승/패 (예: "WIN", "LOSE", "DRAW")

    @Column
    private String etc;  // 기타 사항 (선택)
}
