package SWRC.service;
//이메일에 JWT 기반 인증 링크를 포함하여 전송합니다.
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final Map<String, String> verificationCodes = new HashMap<>();

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // ✅ 이메일 인증 코드 전송
    public void sendVerificationEmail(String email) {
        String verificationCode = generateVerificationCode();
        verificationCodes.put(email, verificationCode); // 인증 코드 저장

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
            helper.setTo(email);
            helper.setSubject("이메일 인증 코드");
            helper.setText("인증 코드: " + verificationCode);

            mailSender.send(message);
            System.out.println("✅ 이메일 전송 성공: " + email);
        } catch (MessagingException e) {
            System.err.println("❌ 이메일 전송 실패: " + e.getMessage());  // 로그 출력
            e.printStackTrace();
            throw new RuntimeException("이메일 전송 실패", e);
        }
    }

    // ✅ 인증 코드 검증
    public boolean verifyCode(String email, String code) {
        return verificationCodes.containsKey(email) && verificationCodes.get(email).equals(code);
    }

    // ✅ 랜덤 인증 코드 생성 (6자리)
    private String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
