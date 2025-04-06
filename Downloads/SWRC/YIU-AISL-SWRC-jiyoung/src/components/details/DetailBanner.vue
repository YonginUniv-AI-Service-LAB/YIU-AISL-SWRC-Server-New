<template>
  <div class="header-wrapper">
    <div class="header-banner">
      <img
        src="@/assets/images/yonginUniversity.png"
        alt="상세 페이지 배경"
      >
    </div>
    <div class="overlay" />
    <div class="overlay-text">
      <h1>{{ selectedMainMenu }}</h1>
    </div>
  </div>
</template>

<script>

export default {
    props: {
        selectedMainMenu: {
            type: String,
            required: true
        }
    }
};

</script>

<style scoped>
.header-banner {
    position: absolute;
    top: 0;
    left: 0; 
    width: 100%;
    height: 36.5%;
    border-radius: 0px 0px 0px 0px;
    overflow: hidden;
    z-index: 0; /* 헤더보다 뒤로 배치 */
}

.header-banner img {
    width: 100%;
    height: 100%;
    object-fit: cover; /* 이미지가 비율을 유지하면서 꽉 차도록 설정 */
    z-index: 1; /* 배경 이미지 위에 배치 */
}

.overlay {
    position: absolute;
    width: 100%;
    height: 36.5%; /* 기존 비율에 맞춰 조정 */
    left: 0;
    top: 0;
    background: rgba(0, 0, 0, 0.3); /* 70% 불투명, 30% 투명 */
    border-radius: 0px 0px 0px 0px;
    z-index: 2; /* 배경 이미지 위에 배치 */
}

.overlay-text {
    position: absolute;
    width: 100%;
    height: 48px;
    left: 50%;   
    top: 15%;
    transform: translate(-50%);
    
    line-height: 150%; /* 줄 간격이 글자 크기의 1.5배로 설정됨 (반응형 가능) */
    text-align: center;
    letter-spacing: -0.011em; /* 글자 간격 조정 */
    
    color: #FFFFFF;
    z-index: 3; /* 오버레이 위에 배치 */
}

.overlay-text h1 {
    font-size: 1.8rem;
    font-weight: 300;
}


</style>
