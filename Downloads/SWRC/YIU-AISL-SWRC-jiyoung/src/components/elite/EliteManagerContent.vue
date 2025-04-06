<template>
  <div class="elite-manager-content">
    <div class="content-wrapper">
      <div class="main-section">
        <div class="main-title">
          <h2>{{ pageTitle }}</h2>
        </div>

        <div class="main-content">
          <div class="content-area">
            <PlayerPerformanceList
              v-if="activeTab === 0"
              @select-record="$emit('select-record', $event)"
            />
            <PlayerRecordList
              v-else-if="activeTab === 1"
              @select-record="$emit('select-record', $event)"
            />
            <p v-else>
              현재 입력된 기록이 없습니다. 내 기록 추가를 통해 몸 상태를 기록해 보세요!
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed, defineProps } from 'vue';
import PlayerRecordList from './PlayerRecordList.vue';
import PlayerPerformanceList from './PlayerPerformanceList.vue';

const props = defineProps({
    activeTab: Number,
});

const pageTitles = ['체력측정분석', '경기기록'];
const pageTitle = computed(() => pageTitles[props.activeTab]);

</script>

<style scoped>
.elite-manager-content {
    /* position: absolute; */
    width: 100%;
    height: 160vh;
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