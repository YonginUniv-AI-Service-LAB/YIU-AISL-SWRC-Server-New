package SWRC.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder  // ✅ User에도 추가했으므로 정상 작동!
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    @Column(nullable = false)
    private String sportType;  // ✅ 관리자만 종목 선택 가능

}
