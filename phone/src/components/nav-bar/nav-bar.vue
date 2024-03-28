<template>
  <div>
    <div class="nav">
      <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal">
        <el-menu-item index="/dishes">
          <router-link to="/dishes"><i class="el-icon-dish-1"></i>点餐</router-link>
        </el-menu-item>
        <el-menu-item index="/orders">
          <router-link to="/orders"><i class="el-icon-notebook-2"></i>订单</router-link>
        </el-menu-item>
        <el-menu-item index="/my">
          <router-link to="/my"><i class="el-icon-user"></i>我的</router-link>
        </el-menu-item>
      </el-menu>
    </div>
  </div>
</template>
<script>
export default {
  name: "nav-bar",
  data() {
    return {
      activeIndex: '/dishes'
    }
  },
  created() {
    let path = this.$route.path
    if (path==='/vip'||path==='/notLogin'||path==='/loginByPwd'||path==='/loginByPhone'||path==='/phoneCode'||path==='/getBackPwd') {
      this.activeIndex = '/my'
    } else if (path==='/orders') {
      this.activeIndex = '/orders'
    } else {
      this.activeIndex = '/dishes'
    }
  }
}
</script>
<style scoped>
.nav {
  border-top: 1px solid #f1f1f1;
  width: 100%;
  height: 50px;
  position: fixed;
  bottom: 0;
  z-index: 999;
  background-color: #f9f9f9;
}

.el-menu {
  width: 100%;
}

.el-menu-item {
  width: 33.3%;
  height: 50px !important;
  line-height: 50px !important;
  border-bottom: 0 !important;
}

.el-menu--horizontal > .el-menu-item.is-active {
  color: #ff9900 !important;
}
</style>
