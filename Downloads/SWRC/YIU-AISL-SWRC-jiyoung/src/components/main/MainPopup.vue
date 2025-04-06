<template>
  <div
    v-if="isVisible"
    class="popup-overlay"
  >
    <div class="popup">
      <button
        class="close-btn"
        @click="closePopup"
      >
        <img
          src="@/assets/images/popupclose.png"
          alt="닫기 버튼"
        >
      </button>
      <img
        src="@/assets/images/popup-image.png"
        alt="국민체력100 팝업"
      >
    </div>
  </div>
</template>

<script>
export default {
    data() {
        return {
        isVisible: true, // 페이지 로드 시 팝업이 뜨도록 설정
        };
    },
    methods: {
        closePopup() {
        this.isVisible = false;
        },
    },
};
</script>

<style scoped>
.popup-overlay {
    position: fixed;
    top: 3vh;
    left: 18vw;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000;
}


.popup {
    position: relative; /* 부모 기준으로 닫기 버튼 위치 설정 */
    text-align: center;
    max-width: 500px;
}

img {
    width: 100%;
}

.close-btn {
    position: absolute;
    top: -1.7px;  /* 팝업 이미지의 위쪽 여백 */
    right: -6.8px; /* 팝업 이미지의 오른쪽 여백 */
    background: none;
    border: none;
    cursor: pointer;
}

.close-btn img {
    width: 30px; /* 닫기 버튼 이미지 크기 조정 */
    height: 30px;
}
</style>
