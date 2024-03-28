<template>
  <el-container class="home-container">
    <el-aside width="auto">
      <navbar :navbarIsCollapse="navbarIsCollapse"></navbar>
    </el-aside>
    <el-container>
      <el-header>
        <ourHeader @changeCollapseStatus="getData"></ourHeader>
      </el-header>
      <el-breadcrumb>
        <breadcrumb></breadcrumb>
      </el-breadcrumb>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
const path = require('path')
let files = require.context('@/components', true, /\.vue$/)
let modules = {}
files.keys().forEach(key => {
  const name = path.basename(key, '.vue')
  modules[name] = files(key).default || files(key)
})

export default {
  name: "home",
  data() {
    return {
      navbarWidth: '200px',
      navbarIsCollapse: false
    }
  },
  components: {
    modules,
    navbar: modules['nav-bar'],
    ourHeader: modules['ourHeader'],
    breadcrumb: modules['breadcrumb']
  },
  methods: {
    getData(navbarIsCollapse) {
      this.navbarIsCollapse = navbarIsCollapse
    }
  }
}
</script>

<style scoped>
.home-container {
  height: 100%;
}

.el-header {
  height: 60px;
  text-align: center;
  line-height: 60px;
  position: relative;
  z-index: 99;
  border-bottom: 1px solid rgba(0, 0, 0, .1);
}

.el-breadcrumb {
  padding-left: 15px;
}

.el-aside {
  text-align: center;
  line-height: 200px;
  box-shadow: 0 2px 5px 0 rgba(0, 0, 0, .2), 0 2px 10px 0 rgba(0, 0, 0, .2);
  position: relative;
  z-index: 999;
}

.el-main {
  text-align: center;
  background-color: #f0f0f9;
  position: relative;
  z-index: 10;
}

</style>
