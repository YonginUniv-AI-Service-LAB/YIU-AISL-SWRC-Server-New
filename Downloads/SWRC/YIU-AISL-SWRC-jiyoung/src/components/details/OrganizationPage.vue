<template>
  <div class="organization-content">
    <h2 class="organization-title">
      조직도
    </h2>
    <img
      src="../../assets/images/organization.png"
      alt="organization"
    >
  </div>
</template>

<script>
    export default {
        name: 'OrganizationPage',
    }
</script>

<style lang="scss" scoped>
.organization-content {
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

.organization-title {
    font-size: 1.7rem;
    font-weight: 500;
    margin-bottom: 4vh;
    margin-right: 66.5%;
    color: #3F3F3F;
}

img {
    width: 76%;
    height: auto;
    object-fit: cover;
}
</style>