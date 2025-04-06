<template>
  <div class="history-content">
    <div class="yongin-university">
      <img
        src="../../assets/images/history-logo1.png"
        class="logo1"
        alt="yongin-logo1"
      >
      <img
        src="../../assets/images/history-logo2.png"
        class="logo2"
        alt="yongin-logo2"
      >
    </div>
    <h2 class="history-title">
      기관 연혁
    </h2>
    <img
      src="../../assets/images/history.png"
      class="history-picture"
      alt="history"
    >
  </div>
</template>

<script>
  export default {
      name: 'HistoryPage',
  }
</script>

<style lang="scss" scoped>
.history-content {
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  width: 100%;
  height: auto;
  min-height: 100vh;
  margin-top: 45vh;
  margin-bottom: 20vh;
}

.history-title {
  font-size: 1.7rem;
  font-weight: 500;
  margin-top: 1vh;
  margin-bottom: 4vh;
  margin-right: 72%;
  color: #3F3F3F;
}

img {
  width: 73%;
  height: auto;
  object-fit: cover;
 
}

.yongin-university {
  display: flex;
  justify-content: space-between; /* 양쪽 끝 정렬 */
  width: 100%; /* 부모 컨테이너 전체 너비 사용 */
  position: absolute;
}

.yongin-university img {
  width: 15%; /* 원하는 크기로 조정 */
  height: auto; /* 비율 유지 */
  z-index: -1;
}
.history-picture{
  transform:translateY(-2%);
  margin-bottom: -29vh;
}
.logo1{
  transform: translateY(-7%);
}
.logo2{
  transform: translateY(250%);
}
</style>