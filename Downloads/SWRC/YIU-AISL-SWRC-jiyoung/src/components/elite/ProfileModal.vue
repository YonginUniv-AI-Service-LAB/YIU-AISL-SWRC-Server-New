<template>
    <div class="modal-overlay" @click.self="close">
        <div class="profile-box">
            <h2 class="title">내 프로필 설정</h2>

            <!-- 프로필 사진 -->
            <div class="profile-picture">
                <div class="profile-circle">
                <img
                    src="@/assets/images/profile.png"
                    alt="Profile Picture"
                    class="profile-image"
                >
                </div>
            </div>
        
            <!-- 인적사항 -->
            <div class="section">
                <h3 class="section-title">
                    인적사항
                </h3>
                <div class="input-group">
                    <div class="form-group">
                        <input
                            v-model="formData.name"
                            type="text"
                            placeholder="이름"
                        >
                    </div>
                <div class="form-group">
                    <input
                        v-model="formData.birthdate"
                        type="date"
                    >
                </div>
                <div class="form-group">
                    <select v-model="formData.gender">
                        <option value="" disabled selected>
                            성별
                        </option>
                        <option value="male">
                            남성
                        </option>
                        <option value="female">
                            여성
                        </option>
                    </select>
                </div>
                </div>
            </div>
        
            <!-- 신체 정보 -->
            <div class="section">
                <h3 class="section-title">
                신체 정보
                </h3>
                <div class="input-group">
                <div class="form-group">
                    <input
                        v-model="formData.height"
                        type="number"
                        placeholder="키 (cm)"
                    >
                </div>
                <div class="form-group">
                    <input
                        v-model="formData.weight"
                        type="number"
                        placeholder="몸무게 (kg)"
                    >
                </div>
                <div class="form-group">
                    <select v-model="formData.sport">
                        <option value="" disabled selected>
                            종목
                        </option>
                        <option value="축구">
                            축구
                        </option>
                        <option value="유도">
                            유도
                        </option>
                        <option value="태권도">
                            태권도
                        </option>
                    </select>
                </div>
                </div>
            </div>
        
            <!-- 버튼 그룹 -->
            <div class="button-group">
                <button
                    type="button"
                    class="back-btn"
                    @click="close"
                >
                뒤로가기
                </button>
                <button
                    type="submit"
                    class="save-btn"
                    @click="save"
                >
                    프로필 저장
                </button>
            </div>
            </div>
    </div>
</template>

<script setup>
import { ref, watch, defineProps, defineEmits } from 'vue';

const props = defineProps({
    show: Boolean,
    initialData: {
        type: Object,
        default: () => ({})
    }
});

const emit = defineEmits(['update:show', 'save']);

const formData = ref({ ...props.initialData });

watch(() => props.initialData, (newVal) => {
    formData.value = { ...newVal };
});

const close = () => {
  emit('update:show', false); // ✅ v-model 바인딩 해제
};

const save = () => {
    emit('save', formData.value);
    emit('update:show', false); // 저장 후 모달 닫기
};
</script>

<style scoped>

.modal-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
    background: rgba(0, 0, 0, 0.4);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000;
}

/* 프로필 작성 박스 */
.profile-box {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: center;
    padding: 1.8rem 3rem;
    background: white;
    border-radius: 8px;
    box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.25);
    width: 70%;
    max-width: 820px;
    min-width: 350px;
    height: auto;
    min-height: 400px;
}

/* 제목 스타일 */
.title {
    font-size: 2rem;
    font-weight: 700;
    color: #000000;
    margin-bottom: 2rem;
}


/* 프로필 사진 컨테이너 */
.profile-picture {
    margin-bottom: 1rem;
}

/* 프로필 사진 원형 컨테이너 */
.profile-circle {
    width: 120px;
    height: 120px;
    border-radius: 50%;
    background: #f0f0f0;
    display: flex;
    justify-content: center;
    align-items: center;
    overflow: hidden;
    position: relative;
}

/* 프로필 사진 */
.profile-image {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

/* 섹션 스타일 */
.section {
    width: 100%;
    margin-bottom: 20px;
}

/* 섹션 제목 스타일 */
.section-title {
    font-size: 18px;
    font-weight: 700;
    color: #3F3F3F;
    text-align: left;
    margin-bottom: 10px;
}

/* 입력 그룹 스타일 */
.input-group {
    display: flex;
    gap: 10px; /* 입력 필드 사이 간격 */
}

/* 입력 필드 스타일 */
.form-group {
    flex: 1;
}

.form-group input,
.form-group select {
    width: 100%;
    padding: 10px;
    border: 2px solid #737373;
    border-radius: 8px;
    font-size: 0.9rem;
    font-weight: 500;
    color: #262626;
    box-sizing: border-box;
}

/* 버튼 그룹 스타일 */
.button-group {
    display: flex;
    justify-content: flex-end; /* 오른쪽 정렬 */
    gap: 10px; /* 버튼 사이 간격 */
    width: 100%;
    margin-top: 20px;
}

/* 뒤로가기 버튼 */
.back-btn {
    padding: 0.8rem 2.8rem;
    border-radius: 8px;
    border: none;
    background: #ECECEC;
    color: #737373;
    font-size: 1rem;
    font-weight: 700;
    cursor: pointer;
    transition: all 0.3s ease;
}

.back-btn:hover {
    background: #f0f0f0;
}

/* 프로필 저장 버튼 */
.save-btn {
    padding: 0.8rem 2.2rem;
    border: none;
    border-radius: 8px;
    background: #005871;
    color: #ECECEC;
    font-size: 1rem;
    font-weight: 700;
    cursor: pointer;
    transition: all 0.3s ease;
}

.save-btn:hover {
    background: #004a63;
}
</style>