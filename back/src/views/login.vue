<template>
  <el-container class="login-box">
    <el-main>
      <!--    背景图-->
      <img src="./../assets/3.png" alt="#">
      <div class="masking"></div>
      <!--    登陆框-->
      <div class="login-box">
        <h1 class="login-title">餐饮管理系统后台</h1>
        <h5 class="login-subtitle">CATERING SYSTEM</h5>
        <el-tabs v-if="notForget" v-model="activeName" type="border-card" :stretch="true">
          <el-tab-pane label="用户名密码登录" name="pwd">
            <loginByPwd @forget="switchForget"></loginByPwd>
          </el-tab-pane>
          <el-tab-pane label="手机验证码登录" name="phone">
            <loginByPhone></loginByPhone>
          </el-tab-pane>
        </el-tabs>
        <div class="forgetPass-box" v-else>
          <forgetPassword @notForget="switchForget"></forgetPassword>
        </div>
      </div>
    </el-main>
    <!--    尾部-->
    <el-footer>

    </el-footer>
  </el-container>
</template>

<script>
import loginByPwd from './../components/login/loginByPwd'
import loginByPhone from './../components/login/loginByPhone'
import forgetPassword from './../components/login/forgetPassword'

export default {
  name: "login",
  data() {
    return {
      activeName: 'pwd',
      notForget: true
    }
  },
  components: {
    loginByPwd,
    loginByPhone,
    forgetPassword
  },
  methods: {
    switchForget() {
      this.notForget = !this.notForget
    }
  }
}
</script>

<style scoped>
.el-container {
  height: 100%;
}

img {
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  z-index: -2;
}

.masking {
  background-color: rgba(0, 0, 0, .4);
  width: 100%;
  height: 100%;
  position: fixed;
  top: 0;
  left: 0;
  z-index: -1;
}

.login-box {
  margin: 0 auto;
}

.login-title {
  color: white;
  width: 400px;
  margin: 3% auto 0;
  font-weight: lighter;
}

.login-subtitle {
  color: white;
  width: 400px;
  margin: 5px auto 40px;
  font-weight: lighter;
}

.el-tabs,
.forgetPass-box {
  width: 500px;
  margin: 0 auto;
}

.el-tabs--border-card {
  background: rgba(0, 0, 0, .7);
  border: none;
  border-radius: 5px;
}

/deep/ .el-tabs--border-card > .el-tabs__header {
  background-color: transparent !important;
  border-bottom: 1px solid darkgrey;
  border-radius: 5px 5px 0 0;
  height: 50px;
}

/deep/ .el-tabs--border-card > .el-tabs__header .el-tabs__item {
  color: #f1f1f1 !important;
  background-color: transparent !important;
  border: none !important;
  border-radius: 5px 5px 0 0;
  height: 51px;
  line-height: 55px;
}

/deep/ .el-tabs--border-card > .el-tabs__header .el-tabs__item.is-active {
  color: #ff9900 !important;
  border: none;
  background-color: #f1f1f1 !important;
  border-radius: 5px 5px 0 0;
}

/deep/ .el-tabs--border-card > .el-tabs__header .el-tabs__item.is-focus {
  box-shadow: none !important;
}

/deep/ .el-tabs--border-card > .el-tabs__header .el-tabs__item:hover {
  color: #ff9900 !important;
}

.el-main {
  height: 95% !important;
}

.el-footer {
  color: #f1f1f1;
  font-size: 12px;
  height: 5% !important;
}
</style>
