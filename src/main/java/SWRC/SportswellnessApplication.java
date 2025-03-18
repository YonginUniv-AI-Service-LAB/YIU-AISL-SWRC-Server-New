package SWRC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "SWRC.entity")  // ✅ 엔티티 패키지 경로 확인
@EnableJpaRepositories(basePackages = "SWRC.repository")  // ✅ JPA Repository 경로 확인
public class SportswellnessApplication {
	public static void main(String[] args) {
		SpringApplication.run(SportswellnessApplication.class, args);
	}
}
