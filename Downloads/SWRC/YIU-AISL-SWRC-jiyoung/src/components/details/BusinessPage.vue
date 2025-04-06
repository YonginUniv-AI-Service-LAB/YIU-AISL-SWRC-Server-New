<template>
  <div class="business-content">
    <h2 class="business-title">
      주요 사업
    </h2>
    <img
      src="../../assets/images/business.png"
      alt="business"
    >
  </div>
</template>

<script>
export default {
  name: 'BusinessPage',
}
</script>

<style lang="scss" scoped>
.business-content {
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

.business-title {
  font-size: 1.7rem;
  font-weight: 500;
  margin-bottom: 4vh;
  margin-right: 66.5%;
  color: #3f3f3f;
}

img {
  width: 73%;
  height: auto;
  object-fit: cover;
}
</style>
