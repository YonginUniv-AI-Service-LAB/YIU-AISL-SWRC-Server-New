<template>
  <div class="kinetics-content">
    <h1 class="kinetics-title">
      운동역학
    </h1>
    <img
      src="@/assets/images/kinetics.png"
      alt="kinetics"
    >
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
.kinetics-content {
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: center; /* 중앙 정렬 */
    justify-content: flex-start;
    width: 100%;
    height: auto;
    min-height: 150vh;
    margin-top: 45vh;
    margin-bottom: 20vh;
}

.kinetics-title {
  font-size: 1.7rem;
  font-weight: 500;
  margin-bottom: 4vh;
  margin-right: 70.5%;
  color: #3F3F3F;
}
img {
  width: 76.7%;
  height: auto;
  object-fit: cover; /* 이미지가 잘리지 않도록 조절 */
}

</style>