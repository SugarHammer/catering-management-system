<template>
  <el-breadcrumb separator-class="el-icon-arrow-right">
    <el-breadcrumb-item>首页</el-breadcrumb-item>
    <el-breadcrumb-item v-for="(item,index) in breadcrumbList" :key="index">
      {{ item }}
    </el-breadcrumb-item>
  </el-breadcrumb>
</template>

<script>
export default {
  name: "breadcrumb",
  computed: {
    breadcrumbList() {
      let breadcrumbs = []
      breadcrumbs = this.$route.name.split('/')
      return breadcrumbs
    }
  }
}
</script>

<style scoped>
.el-breadcrumb {
  margin: 15px 0;
}
</style>
