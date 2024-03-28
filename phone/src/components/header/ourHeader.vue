<template>
  <div class="header">
    <div class="logo">
      <el-image :src="imgUrl" style="width: 35px; height: 35px; display:inline-block;vertical-align:middle;"></el-image>
      <span style="margin-left:5px">然狗餐饮</span>
    </div>

  </div>
</template>

<script>
export default {
  name: 'ourHeader',
  data() {
    return {
      imgUrl: require('../../assets/logo.png')
    }
  },
}
</script>
<style scoped>
.header {
  border-bottom: 1px solid #f1f1f1;
  box-sizing: border-box;
  height: 50px;
  line-height: 50px;
  background-color: #f9f9f9;
}

.header span {
  font-size: 16px;
}

.logo {
  color: #ff9900;
}
</style>
