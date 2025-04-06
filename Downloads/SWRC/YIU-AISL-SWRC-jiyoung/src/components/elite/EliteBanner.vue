<template>
  <div class="header-wrapper">
    <div class="header-banner">
      <img
        src="@/assets/images/elitepage.jpg"
        alt="엘리트 선수 배경"
      >
    </div>
    <div class="overlay" />
    <h1 class="overlay-text">
      엘리트 선수 기록 관리 시스템
    </h1>
    <TabNavigation />
  </div>
</template>

<script setup>
import TabNavigation from './TabNavigation.vue';
</script>

<style scoped>
.header-banner {
    position: absolute;
    top: 0;
    left: 0; /* Figma 기준 */
    width: 100%;
    height: 38%;
    border-radius: 0px 0px 40px 40px;
    overflow: hidden;
    z-index: 1; /* 헤더보다 뒤로 배치 */
}

.header-banner img {
    width: 100%;
    height: 100%;
    object-fit: cover; /* 이미지가 비율을 유지하면서 꽉 차도록 설정 */
    z-index: 3; /* 배경 이미지 위에 배치 */
}

.overlay {
    position: absolute;
    width: 100%;
    height: 38%; /* 기존 비율에 맞춰 조정 */
    left: 0;
    top: 0;
    /* top: 9.7%; header 높이 9.7% 바로 아래에서 시작 */
    background: rgba(89, 58, 47, 0.9); /* #593A2F + opacity 0.9 */
    border-radius: 0px 0px 40px 40px;
    z-index: 4; /* 배경 이미지 위에 배치 */
}

.overlay-text {
    position: absolute;
    width: 100%;
    height: 48px;
    left: 50%;
    top: 15%;
    transform: translateX(-50%);
    
    font-style: normal;
    font-weight: 500;
    font-size: 1.6rem; /* 24px */
    line-height: 150%; /* 줄 간격이 글자 크기의 1.5배로 설정됨 (반응형 가능) */
    text-align: center;
    letter-spacing: -0.011em; /* 글자 간격 조정 */
    
    color: #FFFFFF;
    z-index: 5; /* 오버레이 위에 배치 */
}
</style>
