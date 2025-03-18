package SWRC.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "athlete_records")
public class AthleteRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String athleteName;  // 선수 이름

    @Column(nullable = false)
    private String sportType;  // 종목 (예: 축구, 유도)

    private int score;  // 점수
}
