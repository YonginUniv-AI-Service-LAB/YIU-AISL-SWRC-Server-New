<template>
  <div class="hello-content">
    <h2 class="hello-title">
      인사말
    </h2>
    <img
      src="../../assets/images/hello.png"
      alt="hello"
    >
  </div>
</template>

<script>
export default {
  name: 'HelloPage',
}
</script>

<style lang="scss" scoped>
.hello-content {
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

.hello-title {
  font-size: 1.7rem;
  font-weight: 500;
  margin-bottom: -8vh;
  margin-right: 66.5%;
  color: #3f3f3f;
}

img {
  width: 73%;
  height: auto;
  object-fit: cover;
}
</style>
