// .eslintrc.js
module.exports = {
    root: true,
    env: {
      browser: true,
      node: true,
    },
    extends: [
      'eslint:recommended',
      'plugin:vue/vue3-recommended'
    ],
    parserOptions: {
      ecmaVersion: 2020,
      sourceType: 'module'
    },
    globals: {
      defineProps: 'readonly',
      defineEmits: 'readonly',
      defineExpose: 'readonly',
      withDefaults: 'readonly',
    },
    rules: {
      // 필요하면 규칙 추가
      'vue/max-attributes-per-line': 'off',
      'vue/singleline-html-element-content-newline': 'off',
      'vue/multiline-html-element-content-newline': 'off',
      'vue/html-self-closing': 'off',
      'vue/html-indent': 'off',
      'vue/attributes-order': 'off',
      'vue/require-default-prop': 'off',         // 🔥 props default 경고 제거
      'vue/require-explicit-emits': 'off',      //  🔥 defineEmits 안 써도 됨warn
      'vue/v-on-event-hyphenation': 'off',       // 🔥 이벤트 이름 경고 제거warn
    }
  };  