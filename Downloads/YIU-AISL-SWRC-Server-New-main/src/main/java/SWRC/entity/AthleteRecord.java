package SWRC.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // 추가하면 나중에 builder 패턴으로 객체 생성하기 편해!
@Table(name = "athlete_records")
public class AthleteRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ✅ 추가: 이 기록이 누구(userId)의 것인지 구분
    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String event;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private LocalDate recordDate;

    @Column(nullable = false)
    private Double recordValue;

    private String etc;
}
