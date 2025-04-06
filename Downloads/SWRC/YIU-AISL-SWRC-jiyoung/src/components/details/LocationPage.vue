<template>
  <div class="location-content">
    <h2 class="location-title">
      위치 및 연락처
    </h2>
    <img
      src="../../assets/images/map.png"
      alt="location"
    >
  </div>
</template>

<script>
    export default {
        name: 'LocationPage',
    }
</script>

<style lang="scss" scoped>
.location-content {
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

.location-title {
    font-size: 1.7rem;
    font-weight: 500;
    margin-bottom: 4vh;
    margin-right: 66.5%;
    color: #3F3F3F;
}

img {
    width: 77%;
    height: auto;
    object-fit: cover;
}
</style>