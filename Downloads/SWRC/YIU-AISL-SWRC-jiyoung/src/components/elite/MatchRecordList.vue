<template>
  <div class="match-records">
    <table class="match-table">
      <thead>
        <tr>
          <th>날짜</th>
          <th>대회명</th>
          <th>상대</th>
          <th>승패</th>
          <th>경기 기록</th>
          <th />
        </tr>
      </thead>
      <tbody>
        <template
          v-for="(group, index) in groupedRecordsArray"
          :key="index"
        >
          <tr class="year-header">
            <td colspan="6">
              {{ group.year }}
            </td> <!-- ✅ 연도 헤더 -->
          </tr>
          <tr 
            v-for="(record, idx) in group.records" 
            :key="idx" 
            class="record-row"
            @mouseenter="hoveredRow = `${group.year}-${idx}`"
            @mouseleave="hoveredRow = null"
          >
            <td class="date-cell">
              {{ record.date }}
            </td>
            <td class="tournament-cell">
              {{ record.tournament }}
            </td>
            <td class="opponent-cell">
              {{ record.opponent }}
            </td>
            <td :class="['result-cell', getResultClass(record.result)]">
              {{ record.result }}
            </td>
            <td class="notes-cell">
              {{ record.notes || '-' }}
            </td>
            <td class="action-cell">
              <img 
                v-if="hoveredRow === `${group.year}-${idx}`" 
                src="@/assets/images/edit.png" 
                alt="수정" 
                class="icon-button"
                @click="editRecord(record)" 
              >
              <img 
                v-if="hoveredRow === `${group.year}-${idx}`" 
                src="@/assets/images/delete.png" 
                alt="삭제" 
                class="icon-button"
                @click="deleteRecord(record)" 
              >
            </td>
          </tr>
        </template>
      </tbody>
    </table>
    <MatchRecordModal
      v-if="showModal && selectedRecord"
      v-model:show="showModal"
      :record="selectedRecord"
    />
  </div>
</template>

<script setup>
import { computed, ref } from 'vue';
import MatchRecordModal from './MatchRecordModal.vue';

const hoveredRow = ref(null);
const showModal = ref(false); // ✅ 모달 표시 여부
const selectedRecord = ref(null); // ✅ 현재 선택한 수정 기록

const editRecord = (record) => {
  selectedRecord.value = { ...record }; // 선택된 기록 저장
  showModal.value = true; // 모달 열기
  console.log('수정할 기록:', record);
};

const deleteRecord = (recordToDelete) => {
    if (confirm('경기 기록을 삭제하시겠습니까?')) {
        matchRecords.value = matchRecords.value.filter(
        record => record !== recordToDelete
        );
        console.log('삭제됨:', recordToDelete);
    }
};

// ✅ 날짜 내림차순 정렬 (문자열을 Date 객체로 변환 후 비교)
const sortByDateDesc = (records) => {
    return records.slice().sort((a, b) => {
        const dateA = new Date(a.date.replace(/\./g, '-')); // "YYYY.MM.DD" → "YYYY-MM-DD"
        const dateB = new Date(b.date.replace(/\./g, '-'));
        return dateB - dateA; // 최신순 정렬
    });
};

const matchRecords = ref([
    { date: '2024.05.02', tournament: 'U리그', opponent: '경희대학교', result: '승', year: '2024', notes: '3-0 승리' },
    { date: '2024.04.15', tournament: '전국 대학축구 선수권 대회', opponent: '단국대학교', result: '무', year: '2024', notes: '8강전, 승부차기 패배 (3-4)' },
    { date: '2024.04.05', tournament: '전국 대학축구 선수권 대회', opponent: '울산대학교', result: '승', year: '2024', notes: '16강전, 연장전 3-2 승리' },
    { date: '2024.03.17', tournament: 'KUSF 대학리그', opponent: '연세대학교', result: '패', year: '2024', notes: '라이벌전, 후반 85분 결승골 허용' },
    { date: '2024.03.10', tournament: 'KUSF 대학리그', opponent: '고려대학교', result: '승', year: '2024', notes: '개막전, 2-1 승리' },
    { date: '2023.05.15', tournament: 'U리그', opponent: '한양대학교', result: '승', year: '2023', notes: '원정 경기, 후반 2골 역전승' },
    { date: '2023.04.08', tournament: '전국 대학축구 선수권 대회', opponent: '부경대학교', result: '무', year: '2023', notes: '조별리그, 1-1 무승부' },
    { date: '2023.03.18', tournament: '춘계 대학축구 연맹전', opponent: '홍익대학교', result: '패', year: '2023', notes: '16강전, 후반 추가시간 실점' },
    { date: '2023.03.12', tournament: '춘계 대학축구 연맹전', opponent: '중앙대학교', result: '승', year: '2023', notes: '조별리그, 선제골 포함 2-0 승리' }
]);

const groupedRecordsArray = computed(() => {
    const sortedRecords = sortByDateDesc(matchRecords.value); // 날짜 최신순 정렬

    const grouped = sortedRecords.reduce((acc, record) => {
        if (!acc[record.year]) acc[record.year] = [];
        acc[record.year].push(record);
        return acc;
    }, {});

    // ✅ 객체 → 배열 변환 후 연도 내림차순 정렬 (2024 → 2023 → 2022 ...)
    return Object.entries(grouped)
        .sort(([yearA], [yearB]) => Number(yearB) - Number(yearA))
        .map(([year, records]) => ({ year, records })); // 배열 객체로 변환
});

// ✅ 승무패 스타일 지정
const getResultClass = (result) => {
    return {
        'win': result === '승',
        'lose': result === '패',
        'draw': result === '무'
    };
};
</script>

<style scoped>
.match-table {
    width: 100%;
    overflow-x: hidden;
    white-space: nowrap;
    border-collapse: collapse;
    background: #FFFFFF;
}

.match-table th, .match-table td {
    padding: 0.8rem 1.4rem;
    text-align: left;
}

.match-table th {
    background: #3F3F3F;
    color: #ECECEC;
    font-weight: 700;
}

.year-header {
    background: #F4F4F4;
    color: #3F3F3F;
    font-weight: 700;
}

.match-table tr {
    border-bottom: 2px solid #ECECEC; /* 목록 사이 구분선 */
}

/* 마지막 행에는 구분선 제거 */
.match-table tr:last-child {
    border-bottom: none;
}


.match-table td.date-cell {
    color: #737373;
    font-weight: 500;
}

.match-table td.tournament-cell {
    color: #262626;
    font-weight: 700;
}

.match-table td.opponent-cell {
    color: #737373;
    font-weight: 500;
}

.match-table td.win {
    color: #458FFF;
    font-weight: bold;
}

.match-table td.lose {
    color: #FF4545;
    font-weight: bold;
}

.match-table td.draw {
    color: #737373;
    font-weight: bold;
}

.match-table td.notes-cell {
    color: #737373;
    font-weight: 500;
}

.match-table td.action-cell {
    width: 75px;
    padding: 0.5rem;
    text-align: right;
    vertical-align: middle;
}

.icon-button {
    width: 20px;
    height: 20px;
    margin-left: 8px;
    margin-right: 8px;
    cursor: pointer;
}

</style>
