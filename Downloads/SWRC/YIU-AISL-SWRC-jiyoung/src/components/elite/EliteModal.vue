<template>
  <teleport to="body">
    <div
      v-if="show"
      class="overlay"
    >
      <div class="modal">
        <!-- 상단 영역 -->
        <div class="upper">
          <p>내 기록 추가</p>
          <div class="buttons">
            <button
              class="delete"
              @click="closeModal"
            >
              닫기
            </button>
            <button class="delete-record">
              기록 삭제
            </button>
            <button class="add" @click="saveAndClose">
              저장 및 추가
            </button>
          </div>
        </div>
        <hr class="line">

        <!-- 기본 사항 -->
        <div class="basic-section">
          <p class="option1">기본 사항</p>
          <EliteSelect @update-field="updateField" @update-unit="updateUnit" />
        </div>

        <hr class="line2">

        <div class="record-section">
          <!-- 왼쪽 텍스트 영역 -->
          <div class="record-text">
            <p class="option2">
              내 기록
            </p>
            <p class="description">
              순서는 자동으로 <br>
              정렬됩니다.
            </p>
          </div>
          <!-- 오른쪽 테이블 영역 -->
          <div class="record-table-container">
            <table class="record-table">
              <thead>
                <tr>
                  <th>날짜</th>
                  <th>기록</th>
                  <th>특이사항 (선택사항)</th>
                  <th /> <!-- X 버튼 열 -->
                </tr>
              </thead>
              <tbody>
                <tr
                  v-for="(record, index) in myRecords"
                  :key="index"
                >
                  <td>
                    <input
                      v-model="record.date"
                      @input="formatDate(index)" 
                      placeholder="날짜를 입력해주세요"
                      type="text"
                    >
                  </td>
                  <td>
                    <input
                      v-model="record.record"
                      placeholder="기록을 입력해주세요"
                      type="text"
                    >
                  </td>
                  <td class="detail">
                    <input
                      v-model="record.notes"
                      placeholder="특이사항이 있다면 입력해주세요"
                      type="text"
                    >
                  </td>
                  <td>
                    <button
                      class="close-button"
                      @click="removeRecord(index)"
                    >
                      <img
                        src="@/assets/images/close.png"
                        alt="삭제"
                      >
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>

        <!-- 새로운 행(기록) 추가 -->
        <hr class="line3">
        <div
          class="add-record"
          @click="addRecord"
        >
          기록 추가 +
        </div>
      </div>
    </div>
  </teleport>
</template>

<script>
import { defineComponent, ref } from "vue";
import EliteSelect from "@/components/layout/EliteSelect.vue";

export default defineComponent({
  name: "MyRecordModal",
  components: { EliteSelect },
  props: {
    show: Boolean,
  },
  emits: ["update:show"],
  setup(props, { emit }) {
    // 기록 배열
    const myRecords = ref([
      { date: "2024.07.01", record: "80", notes: "-" },
      { date: "", record: "", notes: "" },
    ]);

    const selectedField = ref("지구력");
    const selectedUnit = ref("분");

    const updateField = (newField) => {
      selectedField.value = newField;
    };

    const updateUnit = (newUnit) => {
      selectedUnit.value = newUnit;
    };

    // 날짜 형식 자동 적용
    const formatDate = (index) => {
      let formattedDate = myRecords.value[index].date.replace(/[^\d]/g, ''); // 숫자만 남기고, 점은 포함되지 않음
      if (formattedDate.length > 4 && formattedDate.length <= 6) {
        formattedDate = formattedDate.slice(0, 4) + '.' + formattedDate.slice(4, 6); // yyyy.mm 형식
      } else if (formattedDate.length > 6) {
        formattedDate = formattedDate.slice(0, 4) + '.' + formattedDate.slice(4, 6) + '.' + formattedDate.slice(6, 8); // yyyy.mm.dd 형식
      }
      myRecords.value[index].date = formattedDate;
    };

    const saveAndClose = () => {
      const enhancedRecords = myRecords.value.map((r) => ({
        ...r,
        field: selectedField.value,
        unit: selectedUnit.value,
      }));
      emit("save-records", enhancedRecords); // 필드/단위 포함해서 emit!
      closeModal();
    };

    // 기록 추가
    const addRecord = () => {
      myRecords.value.push({ date: "", record: "", notes: "" });
    };

    // 기록 삭제
    const removeRecord = (index) => {
      myRecords.value.splice(index, 1);
    };

    // 모달 닫기
    const closeModal = () => {
      emit("update:show", false);
    };

    return {
      myRecords,
      saveAndClose,
      addRecord,
      removeRecord,
      closeModal,
      formatDate,
      updateField,
      updateUnit,
      selectedField,
      selectedUnit,
    };
  },
});
</script>

<style lang="scss" scoped>
/* ---------- 전체 배경 오버레이 ---------- */
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.4);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 100;
}

/* ---------- 모달 ---------- */
.modal {
  background: white;
  width: 80%;
  height: 70%;
  border-radius: 5px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.3);
  position: relative;
  overflow-y: auto; /* 스크롤 */
  overflow-x: hidden;
  padding-bottom: 20px;
}

/* ---------- 상단 영역 ---------- */
.upper {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-left: 3rem;
  margin-right: 3rem;
}
.upper p {
  font-weight: bold;
  font-size: 2rem;
  color: #3f3f3f;
  white-space: nowrap;
}
.buttons {
  display: flex;
  gap: 10px;
  white-space: nowrap;
}

/* 버튼 스타일 */
.upper button {
  font-weight: bold;
  border-radius: 8px;
  border: none;
  cursor: pointer;
}
.delete-record {
  background-color: #262626;
  color: #ececec;
  padding: 15px 30px;
}
.delete {
  background-color: #ececec;
  color: #737373;
  padding: 15px 40px;
}
.add {
  background-color: #005871;
  color: #ececec;
  padding: 15px 23px;
}

/* ---------- 구분선 ---------- */
.line{
  width: 93%;
  margin: 0 auto;
margin-bottom: 20px;
  border: none;
  border-top: 1px solid #ccc;
}

.line2 {
  width: 93%;
  margin: 0 auto;
  margin-top: 5.5rem; /* 기존보다 margin-top을 증가시킴 */
  border: none;
  border-top: 1px solid #ccc;
}
.line3 { 
  max-width: 84%;
  margin: 0 auto;
  border: none;
  margin-left: 13%;
  border-top: 1px solid #ccc;
}

/* ---------- 섹션 제목 ---------- */
.option1 {
  color: #3F3F3F;
  font-weight: 700;
  font-size: 1.2rem;
}

.basic-section {
  margin: 1rem 3.5% 3rem 3.5%; // 상하 간격 일정하게
  display: flex;
  flex-direction: column;
  gap: 20px; // 요소 간 거리 일정
}

/* ---------- record-section: 텍스트와 테이블을 가로로 정렬 ---------- */
.record-section {
  display: flex;
  align-items: flex-start;
  margin: 20px 3.5%;
}

/* 왼쪽 텍스트 영역 */
.record-text {
  flex: 0 0 145px;  /* 영역 너비는 필요에 따라 조정 */
  margin-top: 1rem;
}

/* 오른쪽 테이블 영역 */
.record-table-container {
  flex: 1;
  margin-top: 0.4rem;
}

/* 텍스트 스타일 (마진 초기화) */
.option2 {
  margin: 0;
  font-weight: bold;
  font-size: 1.2rem;
}
.description {
  margin: 0;
  font-size: 13px;
  color: #868686;
  margin-top: 5px;
}

/* ---------- 테이블 ---------- */
.record-table {
  width: 100%;
  border-collapse: collapse;
  table-layout: fixed; /* 칸 너비 균등 분배 */
}

.record-table th,
.record-table td {
  text-align: left;
  padding: 3px 0;
  vertical-align: middle;
}

/* ---------- 입력창 스타일 ---------- */
.record-table input {
  width: 100%;
  box-sizing: border-box;
  padding: 15px 15px;
  font-size: 14px;
  color: #595959;
  border: 1px solid #595959;
  background-color: #f8f8f8;
}
.detail input{

  width: 185%;
}
/* ---------- X 버튼 ---------- */
.close-button {
  background-color: #595959;
  border: 1px solid #595959;
  cursor: pointer;
  padding: 13px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-left: 85%;

}
.close-button img {
  width: 21px;
  height: 21px;
}

/* ---------- 기록 추가 버튼 ---------- */
.add-record {
  cursor: pointer;
  display: flex;
  justify-content: center;
  margin-top: 1%;
  color: #737373;
}
</style>
