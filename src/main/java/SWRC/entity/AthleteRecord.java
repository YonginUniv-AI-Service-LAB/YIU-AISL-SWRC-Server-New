package SWRC.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

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
    private String event; // 분야 (예: 100m 달리기)

    @Column(nullable = false)
    private String unit; // 단위 (예: 초, m)

    @Column(nullable = false)
    private LocalDate recordDate; // 기록 날짜

    @Column(nullable = false)
    private Double recordValue; // 기록 수치 (예: 10.23초)

    private String etc; // 특이사항 (선택)
}
