<template>
  <div class="password-change-container">
    <!-- 헤더 -->
    <MainHeader />
  
    <!-- 배경 이미지 -->
    <div class="background-container">
      <img
        src="@/assets/images/image5.svg"
        alt="Password Change Background"
        class="background-image"
      >
    </div>
  
    <!-- 비밀번호 변경 박스 -->
    <div class="password-change-box">
      <!-- 로그인 하러가기 링크 -->
      <div class="login-link">
        <span @click="goToLogin">로그인 하러가기  →</span>
      </div>
  
      <h2 class="title">
        비밀번호 변경
      </h2>
  
      <!-- 폼 영역 -->
      <form class="password-change-form">
        <!-- 이메일 입력 필드와 인증번호 받기 버튼 -->
        <div class="form-group email-group">
          <label
            for="email"
            class="left-align"
          >이메일</label>
          <div class="email-wrapper">
            <input
              id="email"
              v-model="formData.email"
              type="email"
              placeholder="이메일을 입력해주세요."
              class="email-input"
            >
            <button
              type="button"
              class="auth-btn"
              @click="sendVerificationCode"
            >
              인증번호 받기
            </button>
          </div>
        </div>
  
        <!-- 인증번호 입력 + 확인 버튼 -->
        <div class="form-group verificationCode-group">
          <label
            for="verificationCode"
            class="left-align"
          >인증번호</label>
          <div class="input-group">
            <input
              id="verificationCode"
              v-model="formData.verificationCode"
              type="text"
              placeholder="인증번호를 입력해주세요."
              class="verificationCode-input"
            >
            <button
              type="button"
              class="auth-btn"
              @click="validateVerificationCode"
            >
              인증번호 확인
            </button>
          </div>
        </div>
  
        <!-- 새 비밀번호 입력 필드 -->
        <div class="form-group password-group">
          <label
            for="newPassword"
            class="left-align"
          >새 비밀번호 입력</label>
          <input
            id="newPassword"
            v-model="formData.newPassword"
            type="password"
            placeholder="비밀번호를 입력해주세요."
            class="password-input"
          >
        </div>
  
        <!-- 비밀번호 확인 입력 필드 -->
        <div class="form-group password-group">
          <input
            id="confirmPassword"
            v-model="formData.confirmPassword"
            type="password"
            placeholder="비밀번호 확인"
            :class="['password-input', passwordMatchClass]"
          >
        </div>
  
        <!-- 비밀번호 변경 버튼 -->
        <button
          type="submit"
          class="change-btn"
          @click.prevent="changePassword"
        >
          비밀번호 변경
        </button>
      </form>
    </div>
  </div>
  <MainFooter />
</template>
  
  <script>
  import MainHeader from "@/components/layout/MainHeader.vue";
  import MainFooter from "@/components/layout/MainFooter.vue";
  import axios from "axios";
  
  export default {
    components: {
      MainHeader,
      MainFooter,
    },
    data() {
      return {
        formData: {
          email: "",
          verificationCode: "",
          newPassword: "",
          confirmPassword: "",
        },
      };
    },

    computed: {
      passwordMatchClass() {
        if (this.formData.confirmPassword === "") return "";
        return this.formData.newPassword === this.formData.confirmPassword
          ? "match"
          : "mismatch";
      }
    },

    methods: {
      async sendVerificationCode() {
        if (!this.formData.email) {
          alert("이메일을 입력해주세요.");
          return;
        }

        try {
          await axios.post("http://localhost:8080/api/auth/email/send-verification", null, {
            params: { email: this.formData.email }
          });
          alert("인증번호가 전송되었습니다.");
        } catch (error) {
          console.error("인증번호 요청 실패:", error);
          alert("인증번호 요청 중 오류가 발생했습니다.");
        }
      },

      async validateVerificationCode() {
        try {
          const response = await axios.get("http://localhost:8080/api/auth/email/verify", {
            params: {
              email: this.formData.email,
              verificationCode: this.formData.verificationCode
            }
          });
          console.log(response);
          alert("인증 성공! 이메일이 확인되었습니다.");
        } catch (error) {
          alert("인증 실패: 인증번호가 올바르지 않습니다.");
        }
      },

      async changePassword() {
        if (this.formData.newPassword !== this.formData.confirmPassword) {
          alert("비밀번호가 일치하지 않습니다.");
          return;
        }

        try {
          const payload = {
            email: this.formData.email,
            verificationCode: this.formData.verificationCode,
            newPassword: this.formData.newPassword,
          };

          const response = await axios.put("http://localhost:8080/api/password/reset", payload);

          console.log(response);
          alert("비밀번호가 성공적으로 변경되었습니다.");
          this.$router.push("/login");
        } catch (error) {
          console.error("비밀번호 변경 실패:", error.response?.data || error);
          alert("비밀번호 변경 실패: " + (error.response?.data?.message || "오류가 발생했습니다."));
        }
      }
    },
  };
  </script>
  
  <style scoped>
  /* 전체 컨테이너 */
  .password-change-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 100%;
    min-height: 100vh;
    position: relative;
    overflow: hidden;
  }
  
  /* 배경 컨테이너 */
  .background-container {
    width: 100vw;
    height: 130vh;
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;
    background: var(--BK, #262626);
  }
  
  /* 배경 이미지 */
  .background-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
    filter: blur(7px);
    opacity: 0.8;
  }
  
  /* 어두운 오버레이 추가 */
  .background-container::after {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
  }
  
  /* 비밀번호 변경 박스 */
  .password-change-box {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    text-align: center;
    padding: 2.5rem 2rem;
    background: white;
    border-radius: 12px;
    box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.25);
    width: 60%;
    max-width: 1190px;
    min-width: 320px;
    height: auto;
  }
  
  /* 로그인 하러가기 링크 */
  .login-link {
    position: absolute;
    top: 30px;
    right: 30px;
    font-size: 1rem;
    font-weight: 700;
    color: #737373; /* 회색으로 변경 */
    cursor: pointer;
    transition: color 0.2s ease-in-out;
  }
  
  .title {
  font-size: 2rem;
  font-weight: 700;
  color: #000000;
}
  
  /* 폼 영역 */
  .password-change-form {
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    max-width: 400px;
  }
  
  /* 폼 그룹 */
  .form-group {
    display: flex;
    flex-direction: column;
    width: 100%;
    margin-bottom: 10px; /* 필드 간격 줄이기 */
  }
  
  /* 왼쪽 정렬 라벨 */
  .left-align {
    text-align: left;
    width: 100%;
    font-size: 1rem;
    font-weight: 600;
    color: #3f3f3f;
    margin-bottom: 8px;
  }
  
  /* 이메일 그룹 */
  .email-group {
    margin-bottom: 20px;
  }
  
  /* 이메일 입력 필드와 버튼을 감싸는 컨테이너 */
  .email-wrapper {
    display: flex;
    align-items: center;
    gap: 10px; /* 입력 필드와 버튼 사이 간격 */
  }
  
  /* 이메일 입력 필드 */
  .email-input {
    flex: 2; /* 더 많은 공간 차지 */
    padding: 12px 24px;
    border: 2px solid #737373;
    border-radius: 8px;
    font-size: 1rem;
    text-align: left;
    box-sizing: border-box;
  }
  
  /* 인증번호 받기 버튼 */
  .auth-btn {
    flex: 1; /* 더 적은 공간 차지 */
    padding: 14px 12px;
    background: #e6e6e6; /* 평소 색상 */
    color: #737373; /* 평소 글자 색상 */
    border: none;
    border-radius: 8px;
    cursor: pointer;
    font-size: 14px;
    font-weight: 600;
    transition: background 0.2s ease-in-out, color 0.2s ease-in-out;
  }
  
  .auth-btn:hover {
    background: #005871; /* 눌렀을 때 색상 */
    color: white; /* 눌렀을 때 글자 색상 */
  }

  /* 인증번호 입력 필드 + 버튼 그룹 */
  .verificationCode-group .input-group {
    display: flex;
    align-items: center;
    gap: 10px; /* 입력 필드와 버튼 사이 간격 */
    margin-bottom: 1rem;
  }

  /* 인증번호 입력 필드 */
  .verificationCode-input {
    flex: 2;
    padding: 12px 24px;
    border: 2px solid #737373;
    border-radius: 8px;
    font-size: 1rem;
    text-align: left;
    box-sizing: border-box;
    height: 48px;
    line-height: 24px;
  }
  
  /* 비밀번호 입력 필드 */
  .password-input {
    width: 100%;
    padding: 12px 24px;
    border: 2px solid #737373;
    border-radius: 8px;
    font-size: 1rem;
    text-align: left;
    box-sizing: border-box;
    margin-bottom: 5px; /* 필드 간격 최소화 */
  }
  
  .password-input.match {
    border: 3px solid #005871;
  }

  .password-input.mismatch {
    border: 3px solid #FF4545;
  }

  /* 비밀번호 변경 버튼 */
  .change-btn {
    width: 100%;
    max-width: 400px;
    padding: 12px;
    background: #e6e6e6; /* 평소 색상 */
    border: none;
    border-radius: 8px;
    font-size: 16px;
    font-weight: 600;
    color: #737373; /* 평소 글자 색상 */
    cursor: pointer;
    transition: background 0.2s ease-in-out, color 0.2s ease-in-out;
    margin-top: 2rem;
    margin-bottom: 1rem; /* 버튼과 폼 간격 최소화 */
  }
  
  .change-btn:hover {
    background: #005871; /* 눌렀을 때 색상 */
    color: white; /* 눌렀을 때 글자 색상 */
  }
  </style>