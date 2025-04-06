<template>
  <div class="exercisephysiology-content">
    <h1 class="exercisephysiology-title">
      운동생리학 & 생화학
    </h1>
    <img
      src="@/assets/images/exercise-physiology.png"
      alt="exercise-physiology"
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
.exercisephysiology-content {
    position: relative;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start;
    width: 100%;
    height: auto;
    min-height: 150vh;
    margin-top: 45vh;
    margin-bottom: 20vh;
}

.exercisephysiology-title {
  font-size: 1.7rem;
  font-weight: 500;
  margin-bottom: 4vh;
  margin-right: 62%;
  color: #3F3F3F;
}

img {
  width: 76.7%;
  height: auto;
  object-fit: cover; /* 이미지가 잘리지 않도록 조절 */
}

</style>