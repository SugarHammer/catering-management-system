<template>
  <div>
    <div class="goBackIcon">
      <router-link to="/notLogin">
        <i class="el-icon-arrow-left"></i>返回个人中心
      </router-link>
    </div>
    <div>
      <el-input size="small" v-model="userPhone" placeholder="请输入手机号" maxlength="11"
                oninput="value=value.replace(/[^\d]/g,'')"></el-input>
    </div>
    <div>
      <el-input size="small" placeholder="请输入密码" v-model="pwd" show-password maxlength="18"></el-input>
    </div>
    <div class="loginBtn">
      <div>
        <el-button size="small" :disabled=disabled @click="Login" v-loading.fullscreen.lock="fullscreenLoading">登录</el-button>
      </div>
    </div>
    <el-row>
      <router-link to="/loginByphone">
        <el-col :span="13" style="font-size:13px;text-align:left">手机验证码登录</el-col>
      </router-link>
      <router-link to="/getBackPwd">
        <el-col :span="11" style="font-size:13px;text-align:right">密码找回</el-col>
      </router-link>
    </el-row>
  </div>
</template>

<script>

export default {
  name: "loginByPwd",
  data() {
    return {
      userPhone: '',
      pwd: '',
      disabled: true,
      fullscreenLoading: false
    }
  },
  computed: {
    //定义监听对象
    checkLogin() {
      const {userPhone, pwd} = this
      return {userPhone, pwd}
    }
  },
  methods: {
    //点击登录
    Login() {
      this.openFullScreen1()
      setTimeout(this.requestLog, 600);
    },
    //发起登录请求
    requestLog() {
      // this.$router.push({path:'/dishes'})
      // this.$message({
      //     type:"success",
      //     message:'登录成功',
      //     center: true,
      //     duration:800
      // })
      let data = {
        phone: this.userPhone,
        password: this.pwd
      }
      console.log(data)
      this.$axios.post('/api/login/phonePasswordLogin', this.$qs.stringify(data)).then(res => {
        let result = res.data
        console.log(result)
        if (result.code == 200) {
          this.$router.push({path: '/vip'})
          console.log(result)
          this.$message({
            type: "success",
            message: '登录成功',
            center: true,
            duration: 500
          })
          let personInfo = result.data.id
          window.localStorage.setItem("personInfo", JSON.stringify(personInfo))
        } else if (result.code == 601) {
          this.$message({
            type: "error",
            message: result.msg,
            center: true,
            duration: 800
          })
        }
      }).catch(err => {
        console.log(err);
      })
    },
    openFullScreen1() {
      this.fullscreenLoading = true;
      setTimeout(() => {
        this.fullscreenLoading = false;
      }, 600);
    },

  },
  watch: {
    //监听账号密码的改变
    checkLogin(val) {
      if (this.userPhone.length > 0 && this.pwd.length > 0 && this.userPhone.charAt(0) == 1) {
        this.disabled = false  //启用按钮
      }
      if (this.userPhone.length === 0 || this.pwd.length === 0) {
        this.disabled = true //禁用按钮
      }
    },
  }
}
</script>

<style scoped>
>>> .el-input__inner {
  height: 55px;
  line-height: 50px;
  font-size: 14px;
  border-top: none;
  border-left: none;
  border-right: none;
  border-radius: 0;
  padding-left: 0;
  caret-color: orange;
}

.el-input {
  margin-top: 30px;
  width: 300px;
}

.el-button {
  width: 310px;
  margin-top: 60px;
  background-color: #F5C342;
  border: none;
  height: 40px;
  border-radius: 10px;
  font-size: 18px;
  color: #f1f1f1;
}

.loginBtn {
  position: relative;
}

.el-button.is-disabled, .el-button.is-disabled:focus, .el-button.is-disabled:hover {
  background-color: rgba(245, 196, 67, .4);
  color: darkgray;
}

.el-row {
  width: 300px;
  margin: 20px auto;
}

.goBackIcon {
  padding: 20px;
  text-align: left;
  font-size: 16px;
}

.goBackIcon i {
  font-size: 18px;
  vertical-align: middle;
}
</style>
