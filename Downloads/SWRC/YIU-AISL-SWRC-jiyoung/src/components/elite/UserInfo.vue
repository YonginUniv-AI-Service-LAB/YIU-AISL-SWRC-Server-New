<template>
  <div class="user-info">
    <img
      src="@/assets/images/profile.png"
      alt="프로필 이미지"
      class="profile-image"
    >

    <div class="user-information">
      <h2 class="user-name">
        {{ userName }}
      </h2>
      <h2 class="user-gender">
        ({{ gender }})
      </h2>
    </div>

    <div class="user-details">
      <span class="category">종목</span> <span class="details-divider">|</span> <span class="sport">{{ sport }}</span>
    </div>

    <div class="user-physical">
      <div class="physical-group">
        <span class="label">키</span>
        <strong class="value">{{ height }}</strong>
      </div>
      <span class="physical-divider" />
      <div class="physical-group">
        <span class="label">몸무게</span>
        <strong class="value">{{ weight }}</strong>
      </div>
    </div>
  </div>
</template>

<script setup>
defineProps({
    userName: String,
    gender: String,
    sport: String,
    height: Number,
    weight: Number,
});
</script>

<style scoped>
.user-info {
  flex-shrink: 0;   /* ✅ 다른 요소 때문에 줄어들지 않도록 */
  text-align: center;
}


.profile-image {
    width: 7vw;
    height: 7vw;
    border-radius: 50%;
    object-fit: cover;
}

.user-information {
    display: flex;
    justify-content: center;
    align-items: center;
}

.user-name {
    font-weight: 700;
    font-size: 1.2rem;
    color: #3F3F3F;
}

.user-gender {
    font-weight: 500;
    font-size: 1.2rem;
    color: #3F3F3F;
}

.user-details {
    background: #F4F4F4;
    border-radius: 8px;
    padding: 1vh 1.5vw;
    text-align: center;
    font-size: 0.9rem;
}

.category, .details-divider, .sport {
    color: #005871;
}
.category, .sport {
    font-weight: 700;
}

.user-physical {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 20px;
    gap: 12px;
}

.physical-group {
    display: flex;
    flex-direction: column;
    align-items: center;
}

.label {
    font-size: 13px;
    font-weight: 700;
    color: #737373;
}

.value {
    font-size: 30px;
    font-weight: 300;
    color: #3F3F3F;
}

.physical-divider {
    display: inline-block;
    width: 1px;
    height: 50px;
    background-color: #ECECEC;
    margin: 0 6px;
}
</style>
