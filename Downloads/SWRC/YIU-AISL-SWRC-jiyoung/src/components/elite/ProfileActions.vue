<template>
  <div class="profile-buttons">
    <button 
      v-if="showEditButton" 
      class="btn-edit-profile"
      @click="openProfileModal"
    >
      프로필 수정
    </button>

    <button 
      v-if="showAddRecordButton" 
      class="btn-add-record" 
      @click="openAddRecordModal"
    >
      {{ recordButtonText }}
      <img
        src="@/assets/images/plus.png"
        alt="plus"
      >
    </button>

    <!-- 조건에 따라 해당 모달 렌더링 -->
    <ProfileModal
      v-if="showProfileModal"
      v-model:show="showProfileModal"
    />

    <EliteModal
      v-if="showEliteModal"
      v-model:show="showEliteModal"
      @save-records="handleUpdateRecords"
    />
    
    <MatchRecordModal
      v-if="showMatchModal"
      v-model:show="showMatchModal"
    />
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import EliteModal from './EliteModal.vue';
import MatchRecordModal from './MatchRecordModal.vue';
import ProfileModal from './ProfileModal.vue';

const props = defineProps({
  showEditButton: { type: Boolean, default: true },
  showAddRecordButton: { type: Boolean, default: true },
  pageTitle: { type: String, default: '' }
});

// emit으로 상위로 전달
const emit = defineEmits(["save-records"]);

const handleUpdateRecords = (records) => {
  emit("save-records", records); // UserProfile.vue로 전달
};

// 각각 모달 표시 여부
const showEliteModal = ref(false);
const showMatchModal = ref(false);
const showProfileModal = ref(false);

// 버튼 클릭 시 모달 분기
const openAddRecordModal = () => {
  if (props.pageTitle === '경기기록') {
    showMatchModal.value = true;
  } else {
    showEliteModal.value = true;
  }
};

const openProfileModal = () => {
  showProfileModal.value = true;
};

// 버튼 텍스트 변경
const recordButtonText = computed(() => {
  return props.pageTitle === '경기기록' ? '경기 기록 추가' : '기록 추가';
});

</script>

<style scoped>
.profile-buttons {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 1.5rem;
  width: 100%;
}

/* 버튼 공통 스타일 */
button {
  width: 122%;
  font-weight: 700;
  text-align: center;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  padding: 10px;
  margin: 0.5rem 0;
}

/* 프로필 수정 버튼 */
.btn-edit-profile {
  background-color: #C9C9C9;
  color: #6B6B6B;
  font-size: 0.9rem;
  padding: 0.7rem;
  margin-top: 0rem;
}

/* 기록 추가 버튼 */
.btn-add-record {
  white-space: nowrap;
  background-color: #005871;
  color: white;
  font-size: 1rem;
  padding: 1rem;
  margin-top: 0.1rem;
}

/* 버튼 내 이미지 */
.btn-add-record img {
  width: 0.9rem;
  margin-left: 0.5rem;
}
</style>
