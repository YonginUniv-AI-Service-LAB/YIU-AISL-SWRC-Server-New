  <!-- Admin_main.vue -->
  <template>
      <div class="admin-main">
        <!-- 헤더 섹션 -->
        <AdminHeader />
    
        <!-- 배열 섹션 (승인 대기자 및 가입자) -->
        <AdminArray
          :pending-users="pendingUsers"
          :approved-users="approvedUsers"
          @approve="approveUser"
          @reject="rejectUser"
          @remove="removeUser"
        />
      </div>
      <MainFooter />
    </template>
<script>
import axios from 'axios';
import AdminHeader from '../components/details/admin/AdminHeader.vue';
import AdminArray from '../components/details/admin/AdminArray.vue';
import MainFooter from '@/components/layout/MainFooter.vue';

export default {
  name: 'AdminMain',
  components: {
    AdminHeader,
    AdminArray,
    MainFooter,
  },
  data() {
    return {
      pendingUsers: [],
      approvedUsers: [],
      token: localStorage.getItem('token') || '', // 로그인 토큰
      baseUrl: 'http://localhost:8080' // ✅ 여기에 백엔드 주소를 설정
    };
  },
  methods: {
    approveUser(index) {
      const user = this.pendingUsers[index];
      axios.post(`${this.baseUrl}/api/admin/approve/${user.id}`, null, {
        headers: {
          Authorization: `Bearer ${this.token}`
        }
      }).then(() => {
        this.pendingUsers.splice(index, 1);
        this.approvedUsers.push(user);
      }).catch(err => {
        console.error('승인 실패:', err);
      });
    },
    rejectUser(index) {
      const user = this.pendingUsers[index];
      axios.post(`${this.baseUrl}/api/admin/reject/${user.id}`, null, {
        headers: {
          Authorization: `Bearer ${this.token}`
        }
      }).then(() => {
        this.pendingUsers.splice(index, 1);
      }).catch(err => {
        console.error('거절 실패:', err);
      });
    },
    removeUser(index) {
      const user = this.approvedUsers[index];
      axios.delete(`${this.baseUrl}/api/admin/delete/${user.id}`, {
        headers: {
          Authorization: `Bearer ${this.token}`
        }
      }).then(() => {
        this.approvedUsers.splice(index, 1);
      }).catch(err => {
        console.error('삭제 실패:', err);
      });
    }
  },
  mounted() {
    axios.get(`${this.baseUrl}/api/admin/pending`, {
      headers: {
        Authorization: `Bearer ${this.token}`
      }
    }).then(res => {
      console.log('✅ pending 응답:', res.data);
      this.pendingUsers = res.data;
    }).catch(err => {
      console.error('대기 목록 불러오기 실패:', err);
    });

    axios.get(`${this.baseUrl}/api/admin/approved`, {
      headers: {
        Authorization: `Bearer ${this.token}`
      }
    }).then(res => {
      console.log('✅ approved 응답:', res.data);
      this.approvedUsers = res.data;
    }).catch(err => {
      console.error('승인 목록 불러오기 실패:', err);
    });
  }
};
</script>
    <style scoped>
    /* AdminMain 컴포넌트에 대한 전역 스타일 */
    .admin-main {
      padding: 20px;
    }
    </style>