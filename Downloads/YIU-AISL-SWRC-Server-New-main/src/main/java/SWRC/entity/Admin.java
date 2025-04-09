package SWRC.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    @Column(nullable = false)
    private String sportType; // ✅ 관리자만 종목 선택 가능

    // approvalStatus는 상위 클래스 User에서 상속됨
}
