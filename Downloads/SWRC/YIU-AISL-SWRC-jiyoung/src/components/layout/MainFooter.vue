<template>
  <footer class="footer">
    <div class="footer-content">
      <nav class="footer-nav">
        <span @click="goToLink('/detail/business')">주요사업</span> |
        <span @click="goToLink('/detail/location')">위치 및 연락처</span> |
        <span @click="openExternalLink('https://nfa.kspo.or.kr/main.kspo')">국민체력100</span> |
        <span @click="goToLink('/elite-player')">체력측정분석</span> |
        <span @click="goToLink('/elite-manager')">경기기록</span>
      </nav>
      <div class="footer2">
        <p>경기도 용인시 처인구 용인대학로 134 용인대학교 무도대학 6층</p>
        <p>TEL. 031-8020-2653</p>
        <p>COPYRIGHT(c) YONG IN UNIVERSITY, ALL RIGHTS RESERVED.</p>
      </div>
    </div>

    <!-- SNS 링크 버튼 -->
    <div class="link">
      <img
        src="@/assets/images/youtube.png"
        alt="youtube"
        @click="openExternalLink('https://www.youtube.com/@YongInUniversity')"
      >
      <img
        src="@/assets/images/instagram.png"
        alt="instagram"
        @click="openExternalLink('https://www.instagram.com/yongin_university/')"
      >
      <img
        src="@/assets/images/blog.png"
        alt="blog"
        @click="openExternalLink('https://blog.naver.com/yonginuni0')"
      >
      <img
        src="@/assets/images/facebook.png"
        alt="facebook"
        @click="openExternalLink('https://www.facebook.com/yonginuni/')"
      >
    </div>
  </footer>
</template>

<script>
export default {
  methods: {
    goToLink(path) {
      this.$router.push(path); // 같은 페이지 내에서 해당 path로 이동
    },
    openExternalLink(url) {
      window.open(url, "_blank", "noopener,noreferrer"); // 새 창에서 외부 사이트 열기
    }
  },
};
</script>

<style scoped>
/* Footer 기본 스타일 */
.footer {
  background-color:#262626;
  text-align: left;
  padding: 30px;
  font-weight: 500;
  position: relative;
}

/* Footer 내부 컨텐츠 정렬 */
.footer-content {
  max-width: 800px;
  margin: 0 auto;
  line-height: 1;
  position: relative;
  right: 15%;
}
.footer-nav{
  margin-top: 2vh;
  position: relative;
  right: 0.7vh;
  margin-bottom: 3vh;
  font-size:1rem;
  color: #FFFFFF;
  cursor: pointer;
}
/* 메뉴 스타일 */
.footer-nav span {
  margin: 0 0.8vh;
}
.footer2{
  font-size: 0.8rem;
  color: #BBBBBB;
}
/* SNS 링크 버튼 */
.link {
  display: flex;
  position: absolute;
  bottom: 25%;
  right: 18%;
  gap: 65%;
  width: 30px;
  height: 30px;
  cursor: pointer;
}
</style>
