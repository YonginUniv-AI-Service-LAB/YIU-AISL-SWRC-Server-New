
// MatchRecord.java
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
    private String tournamentName;

    @Column(nullable = false)
    private LocalDate matchDate;

    @Column(nullable = false)
    private String opponent;

    @Column(nullable = false)
    private String result;

    private String etc;
}