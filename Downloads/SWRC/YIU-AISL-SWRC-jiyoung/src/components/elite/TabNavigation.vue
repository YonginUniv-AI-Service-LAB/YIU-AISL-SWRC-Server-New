<template>
  <div class="tab-navigation">
    <template
      v-for="(tab, index) in tabs"
      :key="index"
    >
      <button 
        :class="{ active: activeTab === index }" 
        @click="selectTab(index)"
      >
        {{ tab }}
        <!-- 마지막 버튼에는 구분선이 나타나지 않도록 설정 -->
        <!-- <span v-if="index !== tabs.length - 1" class="divider"></span> -->
      </button>
    </template>
  </div>
</template>

<script setup>
import { ref, defineEmits } from 'vue';

const tabs = ['체력측정분석', '경기기록'];
const activeTab = ref(0);

const emit = defineEmits(['update:activeTab']);

const selectTab = (index) => {
    activeTab.value = index;
    emit('update:activeTab', index); // 선택된 탭 인덱스를 부모에게 전달
};
</script>

<style scoped>
.tab-navigation {
    position: absolute;
    width: 25%; /* 네비게이션 너비 */
    height: 7%; /* 네비게이션 높이 */
    left: 22.5%;
    top: 34.5%; /* 오버레이 아래 위치 */
    transform: translateX(-50%);
    
    background: #FFFFFF;
    box-shadow: 0px 0px 5px rgba(0, 0, 0, 0.25);
    /* border-radius: 8px; */
    display: flex;
    justify-content: space-between;
    align-items: center;
    white-space: nowrap;

    z-index: 5;
}

/* 탭 버튼 스타일 */
button {
    width: 50%; /* 탭 네비게이션의 50% */
    height: 100%; /* 네비게이션과 같은 높이 */
    background: none;
    border: none;
    font-size: 1rem;
    font-weight: 600;
    cursor: pointer;
    transition: 0.2s;
    color: #2D2D2D;

    position: relative; /* 구분선 위치 조정 */
    display: flex;
    align-items: center;
    justify-content: center;
}

button:hover {
    color: #000;
}

button.active {
    background: #333;
    color: white;
    /* border-radius: 8px; */
}

.divider {
    position: absolute;
    right: 0;
    width: 1px;
    height: 40%;
    background-color: rgba(0, 0, 0, 0.3); /* 연한 검은색 */
}
</style>
