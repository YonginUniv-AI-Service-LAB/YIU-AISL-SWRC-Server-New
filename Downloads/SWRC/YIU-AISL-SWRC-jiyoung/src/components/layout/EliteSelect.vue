<template>
  <div class="select-box">
    <div class="select-box-position">
      <div class="select-box1">
        <div class="title">
          분야
        </div>
        <select v-model="field" @change="$emit('update-field', field)">
          <option disabled value="">선택</option>
          <option>지구력</option>
          <option>근력</option>
          <option>점프력</option>
          <option>순발력</option>
        </select>
      </div>

      <div class="select-box2">
        <div class="title">
          단위
        </div>
        <select v-model="unit" @change="$emit('update-unit', unit)">
          <option disabled value="">선택</option>
          <option>분</option>
          <option>kg</option>
          <option>cm</option>
          <option>s</option>
        </select>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      field: "", // 분야 선택값
      unit: "" // 단위 선택값
    };
  }
};
</script>

<style lang="scss" scoped>
.select-box div {
  padding-bottom: 2%;
  
}
.select-box {
  position: absolute;
  top: 9.5rem;
  left: 195px;
}
.title {
  color: #737373;
  font-weight: 700;
}
.select-box select {
  width: 210px;
  padding: 12px;
  border-radius: 5px;
  border: 2px solid #737373;
  background-color: #F9F9F9;
  font-size: 14px;
  cursor: pointer;
  appearance: none;
  outline: none;
  
  color: #262626;
  background-image: url("@/assets/images/Polygon 3.png");
  background-repeat: no-repeat;
  background-position: right 10px center;
  background-size: 12px;
}
.select-box option {
  background: white;
  font-size: 16px;
  padding: 10px;
}
.select-box-position {
  display: flex;
  gap: 15%;
}
</style>