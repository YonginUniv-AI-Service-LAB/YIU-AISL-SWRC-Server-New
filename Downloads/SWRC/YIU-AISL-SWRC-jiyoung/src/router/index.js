import { createRouter, createWebHistory } from 'vue-router';

const routes = [
    {
        path: '/',
        name: 'MainPage',
        component: () => import('@/pages/MainPage.vue'),
    },
    {
        path: '/elite-player',
        name: 'ElitePlayerPage',
        component: () => import('@/pages/ElitePlayerPage.vue'),
    },
    {
        path: '/elite-manager',
        name: 'EliteManagerPage',
        component: () => import('@/pages/EliteManagerPage.vue'),
    },
    {
        path: '/login',
        name: 'UserLogin',
        component: () => import('@/pages/auth/UserLogin.vue'),
        alias: '/sign-in', // /sign-in으로도 접근 가능
    },
    {
        path: '/register',
        name: 'UserRegister',
        component: () => import('@/pages/auth/UserRegister.vue'),
        alias: '/sign-up', // /sign-up으로도 접근 가능
    },
    {
        path: '/detail/:section',  // 동적 라우팅 추가
        name: 'DetailPage',
        component: () => import('@/pages/DetailPage.vue'),
    },
    // 비밀번호 재설정 페이지 추가
    {
        path: '/reset-password', // 경로 설정
        name: 'UserResetPassword', // 라우트 이름
        component: () => import('@/pages/auth/UserResetPassword.vue'), // 컴포넌트 경로
    },
    // 프로필 작성 페이지 추가
    {
        path: '/profile', // 경로 설정
        name: 'ProfilePage', // 라우트 이름
        component: () => import('@/pages/ProfilePage.vue'), // 컴포넌트 경로
    },
      // 최고관리자 
    {
        path: '/admin', // 경로 설정
        name: 'AdminPage', // 라우트 이름
        component: () => import('@/pages/AdminPage.vue'), // 컴포넌트 경로
    },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;