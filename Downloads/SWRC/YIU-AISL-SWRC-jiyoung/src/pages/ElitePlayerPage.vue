<template>
  <div class="elite-player-page">
    <MainHeader />
    <div class="content-wrapper">
      <EliteBanner />
      <TabNavigation v-model:activeTab="activeTab" />
      <EliteContent
        :active-tab="activeTab"
        :performance-data="performanceData"
        @save-performance="handleSavePerformance"
      />           
    </div>
    <MainFooter />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import MainHeader from '../components/layout/MainHeader.vue';
import EliteBanner from '../components/elite/EliteBanner.vue';
import TabNavigation from '../components/elite/TabNavigation.vue';
import EliteContent from '../components/elite/EliteContent.vue';
import MainFooter from '../components/layout/MainFooter.vue';

// ✅ Composition API 방식으로 activeTab 상태 관리
const activeTab = ref(0);

const performanceData = ref([]);

const handleSavePerformance = (data) => {
  performanceData.value = data;
};

</script>

<style lang="scss" scoped>

/* 전체 페이지 레이아웃 */
.elite-player-page {
    display: flex;
    flex-direction: column; /* 세로 정렬 */
    min-height: 100vh;
    overflow-x: hidden;
}

/* 콘텐츠 영역 */
.content-wrapper {
    flex: 1; /* 남은 공간을 모두 차지하도록 설정 */
    display: flex;
    flex-direction: column;
}

/* 푸터 */
.MainFooter {
    width: 100%;
}

</style>