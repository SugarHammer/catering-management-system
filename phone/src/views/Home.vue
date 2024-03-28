<template>
  <el-container class="home">
    <el-header height="50px">
      <ourHeader></ourHeader>
    </el-header>
    <el-main>
      <router-view></router-view>
    </el-main>
    <el-footer height="50px">
      <navbar></navbar>
    </el-footer>
  </el-container>
</template>

<script>
import ourHeader from '../components/header/ourHeader'
import navbar from '../components/nav-bar/nav-bar'

export default {
  name: 'Home',
  components: {
    navbar,
    ourHeader,
  }
}
</script>

<style scoped>
.el-container, .el-header, .el-main, .el-footer {
  padding: 0;
  height: 100%;
}
</style>
