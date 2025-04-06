<template>
  <li class="dropdown">
    <a @click="$emit('navigate', title, '/')">{{ title }}</a>

    <div class="dropdown-content">
      <ul>
        <li
          v-for="(item, index) in items"
          :key="index"
          @click="$emit('navigate', title, item.path)"
        >
          {{ item.title }}
          <div
            v-if="index !== items.length - 1"
            class="divider"
          />
        </li>
      </ul>
    </div>
  </li>
</template>

<script>
export default {
    props: {
        title: String, // 드롭다운 제목 (메인 카테고리)
        items: Array,  // 드롭다운 내부 항목들
    },    
};
</script>

<style lang="scss" scoped>
.dropdown {
    position: relative;
    color: #ffffff;
    margin: 0rem 2rem;
    cursor: pointer;
    font-size: 0.9rem;
    font-weight: 600;
    display: flex;  /* 부모 항목의 크기를 계산하도록 함 */
    align-items: center; /* 텍스트가 수직으로 중앙에 위치하게 함 */
    height: 100%;
    &:hover .dropdown-content{
    display: block;
    opacity: 1;
    visibility: visible;
    transition: opacity 0.3s ease, visibility 0s linear 0.3s;
    }

    /* 가상 요소로 여백 인식 */
    &::after {
        content: '';
        position: absolute;
        top: 100%; /* 부모 요소의 아래에 위치 */
        left: 0;
        right: 0;
        height: 0.7rem; /* 드롭다운과 부모 요소 사이 연결 */
        background: transparent; /* 투명 영역 */
    }
    
    /* 여백을 포함한 마우스 인식 영역 확장 */
    // padding-bottom: 1rem; /* 여백 공간 추가 */
}

.dropdown-content {
    display: none;  /* 기본적으로 숨김 */
    position: absolute;
    background-color: #ffffff;
    top: calc(100% + 0.7rem); /* 부모 요소 바로 아래에 0.7rem 띄우기 */
    left: 50%;
    transform: translateX(-50%); /* 드롭다운의 너비의 절반만큼 왼쪽으로 이동시켜 중앙 정렬 */
    min-width: 138px;
    z-index: 1;
    border-radius: 4px;
    overflow: hidden; /* 항목들이 박스 밖으로 나가지 않도록 설정 */

    ul {
        list-style: none;
        margin: 0;
        padding: 0;

        li {
            color: #737373;
            padding: 0.5rem 0rem; /* 좌우 여백 제거, 세로 패딩 유지 */
            cursor: pointer;
            font-size: 0.8rem;
            font-weight: 500;
            text-align: center;
            line-height: 1.7rem;

            .divider {
            position: absolute;
            width: 60%; /* 선의 길이 조절 */
            height: 0.05vh; /*선의 굵기 조절*/
            margin-top: 0.5rem; /*선의 위아래 위치 조절*/
            left: 21%; /* 왼쪽 정렬 위치 조정 */
            background-color: #E6E6E6;
            }
            &:hover {
                color: #72BDD6;
            }
        }
    }
}
</style>