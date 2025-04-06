<template>
  <div class="main-container">
    <MainHeader />
    <MainBanner />
    <MainContent />
    <MainPopup />
    <MainFooter />
  </div>
</template>

<script>
import MainHeader from '../components/layout/MainHeader.vue'
import MainBanner from '../components/main/MainBanner.vue'
import MainContent from '../components/main/MainContent.vue'
import MainPopup from '../components/main/MainPopup.vue'
import MainFooter from '../components/layout/MainFooter.vue';

export default {
  name: 'MainPage',
  components: {
    MainHeader,
    MainBanner,
    MainContent,
    MainPopup,
    MainFooter
  }
}
</script>

<style>
/* 전체 페이지 레이아웃을 Flexbox로 설정 */
.main-container {
  display: flex;
  flex-direction: column;
  min-height: 100vh; /* 전체 화면 높이를 100%로 설정 */
}

/* 콘텐츠 영역을 푸터 위로 밀어내기 */
.hero {
  flex-grow: 1; /* 내용 영역을 푸터 위로 밀어내기 */
}

body {
  overflow-x: hidden;
}
</style>