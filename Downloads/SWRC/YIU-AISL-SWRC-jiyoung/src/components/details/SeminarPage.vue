<template>
  <div class="seminar-content">
    <h1 class="seminar-title">
      세미나실 & 스터디룸
    </h1>
    <img
      src="@/assets/images/seminar-study.png"
      alt="Yongin Image"
    >
    <div class="contact">
      <h3>연락처</h3>
      <p>031-8020-2653</p>
    </div>
  </div>
</template>

<script>
export default {
  setup () {
    

    return {}
  }
}
</script>

<style lang="scss" scoped>
.seminar-content {
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: center; /* 중앙 정렬 */
    justify-content: flex-start;
    width: 100%;
    height: auto;
    min-height: 75vh;
    margin-top: 45vh;
    margin-bottom: 20vh;
}

.seminar-title {
  font-size: 1.7rem;
  font-weight: 500;
  margin-bottom: 4vh;
  margin-right: 62%;
  color: #3F3F3F;
}

.contact {
  display: flex;
  align-items: center; /* 세로 정렬 */
  gap: 30px; /* 연락처와 전화번호 사이 간격 */
  transform: translate(100px,-57px);
  font-size: 0.8rem;
  font-weight: 700;
}
.contact h3 {
  color: #262626;
}
.contact p {
  color: #3F3F3F;
}
img {
  width: 76.7%;
  height: auto;
  object-fit: cover; /* 이미지가 잘리지 않도록 조절 */
}
</style>