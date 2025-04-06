<template>
  <div class="player-record-list">
    <table>
      <thead>
        <tr>
          <th>종목</th>
          <th>이름</th>
          <th>키</th>
          <th>몸무게</th>
          <th>|</th>
          <th>승</th>
          <th>무</th>
          <th>패</th>
          <th />
        </tr>
      </thead>
      <tbody>
        <tr
          v-for="(record, index) in records"
          :key="index"
          class="clickable-row"
          @mouseover="hoverIndex = index"
          @mouseleave="hoverIndex = null"
          @click="selectRecord(index + 1)"
        >
          <td class="gray-text">
            {{ record.sport }}
          </td>
          <td class="bold-text">
            {{ record.name }}
          </td>
          <td>{{ record.height }}</td>
          <td>{{ record.weight }}</td>
          <td>|</td>
          <td>{{ record.win }}</td>
          <td>{{ record.lose }}</td>
          <td>{{ record.draw }}</td>
          <!-- 호버된 행에만 나타나는 '자세히 보기' -->
          <td class="last-cell">
            <span
              :style="{ visibility: hoverIndex === index ? 'visible' : 'hidden' }"
            >
              자세히보기  &gt;
            </span>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, defineEmits } from 'vue';

const emits = defineEmits(['select-record']); // ✅ 부모 컴포넌트로 이벤트 전달

const records = ref([
    { sport: '축구', name: '김태훈', height: 178, weight: 72, win: 10, lose: 3, draw: 2 },
    { sport: '축구', name: '박성민', height: 182, weight: 76, win: 8, lose: 5, draw: 3 },
    { sport: '축구', name: '이재욱', height: 175, weight: 68, win: 6, lose: 7, draw: 3 },
    { sport: '축구', name: '정민수', height: 180, weight: 74, win: 9, lose: 4, draw: 3 },
    { sport: '축구', name: '오승환', height: 177, weight: 70, win: 7, lose: 6, draw: 3 },
]);

// 마우스가 올라간 행 인덱스
const hoverIndex = ref(null);

// ✅ 클릭 시 부모에게 ID 전달
const selectRecord = (recordId) => {
    emits('select-record', recordId);
};

</script>

<style scoped>
.player-record-list {
    width: 100%;
    min-width: 100%;
    margin-top: 20px;
}

/* 호버 효과 추가 */
.clickable-row {
    cursor: pointer;
    transition: background-color 0.1s;
}

.clickable-row:hover {
    background-color: #E1F9FF;
}

.clickable-row:hover td,
.clickable-row:hover td.bold-text,
.clickable-row:hover td.gray-text {
    color: #005871;
}

table {
    width: 100%;
    border-collapse: separate;
    border-spacing: 0;
}

th {
    background-color: #3F3F3F;
    color: white;
    padding: 0.8rem 1.5rem;
    font-size: 0.9rem;
    font-weight: 700;
    text-align: left;
}

td {
    padding: 0.8rem 1.5rem;
    border-bottom: 2px solid #ECECEC;
    font-size: 0.9rem;
    color: #262626;
}

.last-cell {
    text-align: right;
    font-size: 0.9rem;
    font-weight: 700;
    padding-right: 2rem; /* 필요에 따라 조절 */
}

tbody tr:nth-child(odd) {
    background-color: #F4F4F4;   
}

tbody tr:nth-child(even) {
    background-color: white;
}

.gray-text {
    color: #737373;
    font-weight: 500;
}

.bold-text {
    color: #262626;
    font-weight: bold;
}
</style>