<template>
  <div class="elite-detail-content">
    <div class="content-wrapper">
      <UserProfile 
        user-name="김태훈"
        gender="남자"
        sport="축구"
        height="178"
        weight="72"
        :show-edit-button="false"
        :show-add-record-button="true"  
        :page-title="pageTitle"
        @save-records="updateChartRecords"
      />

      <div class="main-section">
        <div class="main-title">
          <h2>{{ pageTitle }}</h2>
          <button class="back-button" @click="$emit('go-back')">
            <img src="@/assets/images/back.png" alt="돌아가기" class="back-button" />
            목록으로 돌아가기
          </button>
        </div>

        <div class="main-content">
          <div class="content-area">
            <PerformanceCharts
              v-if="activeTab === 0"
              :record-id="recordId"
              :external-records="chartRecords"
            />
            <MatchRecordList
              v-else-if="activeTab === 1"
              :record-id="recordId"
            />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, defineProps} from 'vue';
import UserProfile from './UserProfile.vue';
import MatchRecordList from './MatchRecordList.vue';
import PerformanceCharts from './PerformanceCharts.vue';

const chartRecords = ref([]);

const updateChartRecords = (records) => {
  chartRecords.value = records;
};

const props = defineProps({
    activeTab: Number,
    recordId: Number, // ✅ 부모에서 받은 recordId
});

const pageTitles = ['체력측정분석', '경기기록'];
const pageTitle = computed(() => pageTitles[props.activeTab]);

</script>

<style scoped>
.elite-detail-content {
    /* position: absolute; */
    width: 100%;
    height: 170vh;
    min-height: 100vh;
    top: 40vh;
    left: 0;
    background: #F4F4F4;
    overflow: hidden; /* 내용이 넘칠 경우 스크롤바 표시 */
    z-index: 0;
}

/* 프로필과 메인 컨텐츠를 감싸는 레이아웃 */
.content-wrapper {
    display: flex;
    gap: 2vw;
    width: 81%;
    margin-left: 10vw;
}

.user-profile {
    flex: 0 0 10vw; /* 고정된 너비 */
    margin-top: 47vh; /* 화면 높이의 18%만큼 아래로 이동 */
    max-height: 280px;
}

/* 세로 정렬: 타이틀과 컨텐츠 */
.main-section {
    flex: 1;
    display: flex;
    flex-direction: column; /* 타이틀과 컨텐츠를 세로 정렬 */
    gap: 1rem; /* 세로 간격 */
    margin-top: 34vh;
}

/* 타이틀 스타일 */
.main-title {
    margin-top: 10vh;
    border-bottom: 1px solid #3f3f3f; /* 구분선 추가 */
    display: flex;
    justify-content: space-between;
    align-items: center;

}

/* h2에 유동적인 밑줄 적용 */
.main-title h2 {
    display: inline-block; /* 글자 길이에 맞게 width 조정 */
    position: relative; /* ::after 가상 요소 위치 기준 */
    font-size: 1.5rem;
    font-weight: bold;
    margin-bottom: 10px;
    color: #3f3f3f;
}

/* 글자 길이에 맞는 굵은 밑줄 */
.main-title h2::after {
    content: "";
    position: absolute;
    left: 0;
    bottom: -11px;
    width: 100%; /* 글자 길이에 맞게 */
    height: 4.3px; /* 굵은 밑줄 두께 */
    background-color: #3F3F3F; /* 굵은 밑줄 색상 */
}

.back-button {
  background: none;
  border: none;
  color: #005871;
  font-weight: 700;
  cursor: pointer;
  font-size: 0.9rem;
  gap: 0.4rem;
  margin-top: 14px;
  letter-spacing: -0.08em; /* 글자 간격 조정 */
}

.back-button img {
  object-fit: contain;
  width: 0.8rem;
  height: 0.8rem;
}

/* 메인 컨텐츠 (오른쪽 배치) */
.main-content {
    height: 100%;
}

.main-content p {
    margin-top: 1.5vh;
    background: #D6D6D6;
    border-radius: 8px;
}

/* 콘텐츠 스타일 */
.content-area {
    font-size: 0.9rem;
}

.content-area p {
    font-weight: 600;
    color: #7e7e7e;
    padding: 1rem 1rem;
    border-radius: 8px;
}
</style>