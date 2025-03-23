package SWRC.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // 이름

    @Column(nullable = false)
    private LocalDate birthDate; // 생년월일

    @Column(nullable = false)
    private String gender; // 성별

    private Double height; // 키 (cm)
    private Double weight; // 몸무게 (kg)

    private String event; // 분야 (예: 100m)
    private String unit;  // 단위 (예: 초, kg)
}
