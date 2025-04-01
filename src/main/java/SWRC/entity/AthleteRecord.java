// AthleteRecord.java
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
    private String event;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private LocalDate recordDate;

    @Column(nullable = false)
    private Double recordValue;

    private String etc;
}