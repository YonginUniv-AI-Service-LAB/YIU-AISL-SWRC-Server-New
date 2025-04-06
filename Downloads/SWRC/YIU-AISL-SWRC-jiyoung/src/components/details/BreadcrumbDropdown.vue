<template>
  <nav class="breadcrumb-container">
    <!-- 홈 버튼 -->
    <button
      class="home-btn"
      @click="goHome"
    >
      <div class="icon">
        <img
          src="@/assets/images/home.png"
          alt="home"
        >
      </div>
      <span class="sr-only">홈</span>
    </button>

    <!-- 첫 번째 드롭다운 -->
    <div class="dropdown">
      <button
        class="dropdown-btn1"
        @click="toggleDropdown('main')"
      >
        <span class="active-main-text">{{ selectedMainMenu.title }}</span>
        <span :class="{ 'rotate-180': dropdowns.main }">
          <img
            src="@/assets/images/arrow.png"
            alt="arrow"
            class="dropdown-arrow"
          >
        </span>
      </button>
      <ul
        v-if="dropdowns.main"
        class="dropdown-menu"
      >
        <li
          v-for="menu in Object.keys(subMenuOptions)"
          :key="menu"
          @click="selectMenu(menu)"
        >
          {{ menu }}
        </li>
      </ul>
    </div>

    <!-- 두 번째 드롭다운 -->
    <div class="dropdown">
      <button
        class="dropdown-btn2"
        @click="toggleDropdown('sub')"
      >
        <span class="active-text">{{ selectedSubMenu.title }}</span>
        <span :class="{ 'rotate-180': dropdowns.sub }">
          <img
            src="@/assets/images/arrow.png"
            alt="arrow"
            class="dropdown-arrow"
          >
        </span>
      </button>
      <ul
        v-if="dropdowns.sub"
        class="dropdown-menu"
      >
        <li
          v-for="item in currentSubMenuOptions"
          :key="item.title"
          @click="selectSubMenu(item)"
        >
          {{ item.title }}
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
export default {
    data() {
        return {
            dropdowns: {main: false, sub: false,}, // 드롭다운 메뉴 토글 상태

            selectedMainMenu: { title: '스포츠·웰니스연구센터 소개' },
            selectedSubMenu: { title: '인사말', path: '/' },

            // 메뉴와 해당 페이지 경로 매핑
            subMenuOptions: {
                '스포츠·웰니스연구센터 소개': [
                    { title: '인사말', path: '/detail/hello' },
                    { title: '주요사업', path: '/detail/business' },
                    { title: '기관연혁', path: '/detail/history' },
                    { title: '조직도 위원회', path: '/detail/organization' },
                    { title: '위치 및 연락처', path: '/detail/location' },
                ],
                '스포츠·웰니스연구센터 연구실': [
                    { title: '운동생리학 & 생화학', path: '/detail/exercisephysiology' },
                    { title: '운동 역학', path: '/detail/kinetics' },
                    { title: '세미나실 & 스터디룸', path: '/detail/seminar' },
                ],
                '국민체력100': [],
                '엘리트 선수 기록 관리 시스템': [
                    { title: '체력 측정분석', path: '/elite-player' },
                    { title: '경기 기록', path: '/elite-manager' },
                ],
            },

            // 현재 선택된 첫 번째 드롭다운의 두 번째 드롭다운 항목들
            currentSubMenuOptions: [],
            pathToMenuMap: {}, // 경로와 메뉴 매핑
        };
    },

    watch: {
        // 라우트 변경 감지 -> 브레드크럼 업데이트
        '$route.path'() {
            this.updateBreadcrumbFromRoute();
        },
    },

    created() {
        // 경로별 매핑 객체 초기화
        this.generatePathToMenuMap();
        this.updateBreadcrumbFromRoute();
    },

    
    methods: {
        goHome() {
            this.$router.push('/');
        },
        toggleDropdown(menu) {
            this.dropdowns[menu] = !this.dropdowns[menu];
        },
        selectMenu(menu) {
            this.selectedMainMenu = { title: menu }; // 첫 번째 드롭다운 항목 변경
            this.currentSubMenuOptions = this.subMenuOptions[menu] || []; // 두 번째 드롭다운 항목 변경

            // 국민체력100인 경우
            if (menu === '국민체력100') {
                window.open('https://nfa.kspo.or.kr/main.kspo', '_blank');
                this.toggleDropdown('main');
                return;
            }

            this.selectedSubMenu = this.currentSubMenuOptions.length > 0 ? this.currentSubMenuOptions[0] : {}; // 두 번째 드롭다운 첫 번째 항목 선택
            this.toggleDropdown('main');

            if (this.currentSubMenuOptions.length === 0) {
                this.$router.push('/');
            }
        },
        selectSubMenu(menu) {
            this.selectedSubMenu = menu;
            this.toggleDropdown('sub');

            // 두 번째 드롭다운에서 선택한 항목에 따라 페이지 이동
            if (menu.path) {
                this.$router.push(menu.path);
            }
        },
        generatePathToMenuMap() {
            for (const mainMenu in this.subMenuOptions) {
                this.subMenuOptions[mainMenu].forEach(subMenu => {
                    this.pathToMenuMap[subMenu.path] = { main: mainMenu, sub: subMenu };
                });
            }
        },
        updateBreadcrumbFromRoute() {
            const currentPath = this.$route.path;
            const matchedMenu = this.pathToMenuMap[currentPath];

            if (matchedMenu) {
                this.selectedMainMenu = { title: matchedMenu.main };
                this.currentSubMenuOptions = this.subMenuOptions[matchedMenu.main];
                this.selectedSubMenu = matchedMenu.sub;
            }
        },
    },
};
</script>

<style scoped>
.breadcrumb-container {
    position: absolute;
    top: 33%;
    left: 11.5%;
    width: auto;
    min-width: 30%;
    height: 7%;
    display: inline-flex;
    white-space: nowrap; /* 텍스트 줄바꿈 방지 */
    align-items: center;
    background: white;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    z-index: 5;
}

.home-btn {
    width: 16%;
    height: 100%;
    background: #2D2D2D;
    color: white;
    border-radius: 8px;
    display: flex;
    align-items: center;
    font-size: 0.9rem;
    font-weight: 700;
}

.icon img {
    width: 40%;
    height: 40%;
    margin-top: 3px;
    margin-right: -0.4rem;
}

.dropdown {
    position: relative;
}

.dropdown-btn1, .dropdown-btn2 {
    background: none;
    border: none;
    cursor: pointer;
    display: flex;
    align-items: center;
    gap: 0.6rem;
    font-size: 0.9rem;
    font-weight: 700;
    color: #262626;
}

.dropdown-btn1 {
    margin-left: 0.7rem;
    margin-right: 0.5rem;
}

.dropdown-arrow {
    width: 38%;
    height: auto;
    transition: transform 0.3s ease;
}

.dropdown-menu {
    position: absolute;
    top: 100%;
    left: 0;
    background: white;
    border: 1px solid #ddd;
    width: max-content;
    list-style: none;
    padding: 0.5rem 0;
    border-radius: 6px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.dropdown-menu li {
    color: #737373;
    font-size: 0.9rem;
    padding: 10px 12px;
    cursor: pointer;
    text-align: center;
}

.dropdown-menu li:hover {
    color: #005871;
}

.active-main-text {
    font-weight: bold;
    color: #262626;
}

.active-text {
    font-weight: bold;
    color: #005871;
}

.rotate-180 {
    transform: rotate(180deg);
}
</style>

