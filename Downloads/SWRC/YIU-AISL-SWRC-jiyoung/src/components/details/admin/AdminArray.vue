<template>
  <div class="admin-array space-y-10">
    <!-- 🔹 승인 대기자 섹션 -->
    <div class="bg-white p-6 rounded-xl shadow border border-gray-200">
      <h2 class="text-xl font-bold mb-4 text-gray-800">승인 대기자</h2>

      <div v-if="pendingUsers.length === 0" class="text-gray-500 italic">
        대기 중인 관리자가 없습니다.
      </div>

      <table v-else class="w-full border-collapse">
        <thead class="bg-gray-50">
          <tr>
            <th class="p-3 text-left">이름</th>
            <th class="p-3 text-left">종목</th>
            <th class="p-3 text-left">이메일</th>
            <th class="p-3 text-center">처리</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(user, index) in pendingUsers" :key="index" class="border-t hover:bg-gray-50">
            <td class="p-3 font-medium">{{ user.name }}</td>
            <td class="p-3 text-blue-600">{{ user.sport }}</td>
            <td class="p-3">{{ user.email }}</td>
            <td class="p-3 text-right space-x-2">
              <button @click="rejectUser(index)" class="bg-red-500 text-white px-4 py-1 rounded hover:bg-red-600">거절</button>
              <button @click="approveUser(index)" class="bg-green-600 text-white px-4 py-1 rounded hover:bg-green-700">승인</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>

    <!-- 🔹 승인된 관리자 섹션 -->
    <div class="bg-white p-6 rounded-xl shadow border border-gray-200">
      <h2 class="text-xl font-bold mb-4 text-gray-800">가입자</h2>

      <div v-if="approvedUsers.length === 0" class="text-gray-500 italic">
        가입된 관리자가 없습니다.
      </div>

      <table v-else class="w-full border-collapse">
        <thead class="bg-gray-50">
          <tr>
            <th class="p-3 text-left">이름</th>
            <th class="p-3 text-left">종목</th>
            <th class="p-3 text-left">이메일</th>
            <th class="p-3 text-center">처리</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(user, index) in approvedUsers" :key="index" class="border-t hover:bg-gray-50">
            <td class="p-3 font-medium">{{ user.name }}</td>
            <td class="p-3 text-blue-600">{{ user.sport }}</td>
            <td class="p-3">{{ user.email }}</td>
            <td class="p-3 text-right">
              <button @click="removeUser(index)" class="bg-gray-700 text-white px-4 py-1 rounded hover:bg-gray-800">삭제</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AdminArray',
  props: {
    pendingUsers: Array,
    approvedUsers: Array,
  },
  methods: {
    approveUser(index) {
      this.$emit('approve', index);
    },
    rejectUser(index) {
      this.$emit('reject', index);
    },
    removeUser(index) {
      this.$emit('remove', index);
    },
  },
};
</script>

<style scoped>
/* scoped 스타일 최소화: Tailwind 기반으로 변경했기 때문에 대부분 class로 처리함 */
</style>