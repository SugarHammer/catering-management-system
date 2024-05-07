<template>
  <div>
    <el-menu
        :default-active="activePath"
        router
        unique-opened
        :collapse="navbarIsCollapse"
        ref="navbar"
        class="el-menu-vertical-demo">
      <el-menu-item class="logo-box">
        <div class="logo"></div>
        <span slot="title">
          餐饮管理
        </span>
      </el-menu-item>
      <el-submenu index="dishes">
        <template slot="title">
          <i class="el-icon-food"></i>
          <span slot="title">菜品管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/home/dishes/categories">菜品大类</el-menu-item>
          <el-menu-item index="/home/dishes/subcategories">菜品小类</el-menu-item>
          <el-menu-item index="/home/dishes/info">菜品信息</el-menu-item>
          <el-menu-item index="/home/dishes/unit">菜品单位</el-menu-item>
          <el-menu-item index="/home/dishes/combo">套餐管理</el-menu-item>
          <el-menu-item index="/home/dishes/practices">菜品做法</el-menu-item>
          <el-menu-item index="/home/dishes/relevanceWay">做法关联</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="tables">
        <template slot="title">
          <i class="el-icon-data-board"></i>
          <span slot="title">餐桌管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/home/tables/info">餐桌信息</el-menu-item>
          <el-menu-item index="/home/tables/areas">餐桌区域</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-menu-item index="/home/orders">
        <i class="el-icon-notebook-2"></i>
        <span slot="title">订单管理</span>
      </el-menu-item>
      <el-submenu index="members">
        <template slot="title">
          <i class="el-icon-user"></i>
          <span slot="title">会员管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/home/members/info">会员信息</el-menu-item>
          <el-menu-item index="/home/members/integralTrading">积分交易</el-menu-item>
          <el-menu-item index="/home/members/topUp">会员充值</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="report">
        <template slot="title">
          <i class="el-icon-pie-chart"></i>
          <span slot="title">报表分析</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/home/charts/dishesSales">菜品销售查询</el-menu-item>
          <el-menu-item index="/home/charts/businessSearch">营业情况查询</el-menu-item>
          <el-menu-item index="/home/charts/turnover">门店流水查询</el-menu-item>
          <el-menu-item index="/home/charts/businessReport">营业情况分析</el-menu-item>
          <el-menu-item index="/home/charts/memberData">会员增量分析</el-menu-item>
          <el-menu-item index="/home/charts/dishesRanking">消费菜品排行</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
      <el-submenu index="system">
        <template slot="title">
          <i class="el-icon-setting"></i>
          <span slot="title">系统管理</span>
        </template>
        <el-menu-item-group>
          <el-menu-item v-if="haveAuthToAccessJur" index="/home/system/jurisdiction">权限设置</el-menu-item>
          <el-menu-item v-if="haveAuthToAccessJur" index="/home/system/log">系统日志</el-menu-item>
          <el-menu-item index="/home/system/accountInfo">账号信息</el-menu-item>
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script>
export default {
  name: "nav-bar",
  data() {
    return {
      activePath: this.$route.path
    }
  },
  props: {
    navbarIsCollapse: Boolean
  },
  computed: {
    haveAuthToAccessJur() {
      return localStorage.getItem('loginRoleId')==='2'
    }
  }
}
</script>

<style scoped>
.el-menu {
  border-right: none !important;
  width: 200px;
}

.logo-box:hover {
  background-color: white;
}

.logo-box {
  cursor: default;
  color: #ff9900;
  font-size: 16px;
  margin-bottom: 10px;
  border-bottom: 1px solid #f1f1f1;
  height: 76px;
  line-height: 76px;
}

.logo {
  display: inline-block;
  width: 46px;
  height: 46px;
  background-image: url('./../../assets/logo copy.jpg'), linear-gradient(#ff9900, #ff9900);
  background-blend-mode: lighten;
  background-size: cover;
  margin-left: 20px;
}

.el-menu-item:hover,
.el-menu-item.is-active,
/deep/ .el-submenu__title:hover {
  color: #ff9900;
  background-color: white;
}

.el-submenu__title:hover i,
.el-menu-item:hover i {
  color: #ff9900 !important;
}

.el-submenu__title i,
.el-menu-item i {
  transition: all .3s;
}

.el-submenu__title i,
.el-menu-item i {
  margin-right: 10px;
}

.el-menu-item-group {
  background-color: #f1f1f1;
}

/deep/ .el-menu-item {
  padding-left: 0 !important;
}

/deep/ .el-menu-item > span {
  margin-right: 10px;
}

/deep/ .el-submenu__title {
  padding: 0 50px;
}

/deep/ .el-menu-item-group__title {
  padding: 0;
}

.el-menu-item-group {
  padding: 10px 0;
}

.el-menu-item-group .el-menu-item {
  margin-left: 25px;
}

.el-menu-item-group .el-menu-item:hover {
  background-color: #f1f1f1;
}

.el-menu-item-group .el-menu-item.is-active {
  background-color: #f1f1f1;
}

/*折叠后的样式*/

.el-menu--collapse {
  width: 80px;
}

.el-menu--collapse .logo {
  margin-left: 0;
}

/deep/ .el-menu--collapse .el-submenu__title {
  padding: 0 !important;
  transition: all 0s;
}
</style>
