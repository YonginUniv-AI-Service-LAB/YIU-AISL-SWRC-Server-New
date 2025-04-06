<template>
  <router-view />
</template>

<script>
export default {
    name: 'App',
};
</script>

<style>
@font-face {
    font-family: 'KoPubBatang';
    src: url('@/assets/fonts/KoPubWorld Batang Light.ttf') format('truetype');
    font-weight: 300;
}

@font-face {
    font-family: 'KoPubBatang';
    src: url('@/assets/fonts/KoPubWorld Batang Medium.ttf') format('truetype');
    font-weight: 500;
}

@font-face {
    font-family: 'KoPubBatang';
    src: url('@/assets/fonts/KoPubWorld Batang Bold.ttf') format('truetype');
    font-weight: 700;
}

@font-face {
    font-family: 'KoPubDotum';
    src: url('@/assets/fonts/KoPubWorld Dotum Light.ttf') format('truetype');
    font-weight: 300;
}

@font-face {
    font-family: 'KoPubDotum';
    src: url('@/assets/fonts/KoPubWorld Dotum Medium.ttf') format('truetype');
    font-weight: 500;
}

@font-face {
    font-family: 'KoPubDotum';
    src: url('@/assets/fonts/KoPubWorld Dotum Bold.ttf') format('truetype');
    font-weight: 700;
}

body {
    font-family: 'KoPubDotum', sans-serif;
}
</style>