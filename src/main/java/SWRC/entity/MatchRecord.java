package SWRC.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String matchName;  // 경기 이름 (예: 결승전)

    @Column(nullable = false)
    private String teamA;  // 팀 A 이름

    @Column(nullable = false)
    private String teamB;  // 팀 B 이름

    private int teamAScore;  // 팀 A 점수
    private int teamBScore;  // 팀 B 점수
}
