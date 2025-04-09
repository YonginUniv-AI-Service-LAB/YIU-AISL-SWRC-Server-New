package SWRC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;  // ✅ 추가

@SpringBootApplication
@EnableJpaAuditing  // ✅ Auditing 활성화 (createdAt, updatedAt 자동 기록)
@EntityScan(basePackages = "SWRC.entity")
@EnableJpaRepositories(basePackages = "SWRC.repository")
public class SportswellnessApplication {
	public static void main(String[] args) {
		SpringApplication.run(SportswellnessApplication.class, args);
	}
}
