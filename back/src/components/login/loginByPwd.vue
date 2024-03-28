<template>
  <el-form :model="loginByPwdForm" size="medium" class="login-form">
    <el-form-item prop="username">
      <el-input v-if="!wrongInput" type="text" v-model="loginByPwdForm.mumberName" placeholder="输入用户名"
                style="margin-top:30px"></el-input>
      <div v-else>
        <el-alert v-if="emptyUsernamePwd" title="用户名或密码不能为空" type="error" :closable="false"></el-alert>
        <el-alert v-if="wrongUsernamePwd" title="用户名或密码错误，请检查后输入" type="error" :closable="false"></el-alert>
        <el-input warningColor="warning" type="text" v-model="loginByPwdForm.mumberName"
                  @focus="resetInputBox" placeholder="输入用户名"></el-input>
      </div>
    </el-form-item>
    <el-form-item prop="pwd">
      <el-input v-if="!wrongInput" type="password" show-password v-model="loginByPwdForm.mumberPassword"
                placeholder="输入密码"></el-input>
      <el-input v-else warningColor="warning" type="password" show-password v-model="loginByPwdForm.mumberPassword"
                @focus="resetInputBox" placeholder="输入密码"></el-input>
    </el-form-item>
    <el-form-item>
      <el-checkbox v-model="rememberPwdChecked">记住密码</el-checkbox>
      <el-button class="forgetPwdBtn" type="text" @click="switchToForget">忘记密码？</el-button>
    </el-form-item>
    <el-form-item>
      <el-button class="loginBtn" type="primary" @click="login">登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import {mapMutations} from 'vuex';

export default {
  name: "loginByPwd",
  data() {
    return {
      wrongInput: false,
      emptyUsernamePwd: false,
      wrongUsernamePwd: false,
      rememberPwdChecked: false,
      loginByPwdForm: {
        mumberName: '', // 后端字段拼写错误
        mumberPassword: ''
      },
      notForget: true
    }
  },
  methods: {
    ...mapMutations(['changeLogin']),
    // 提交登录请求
    login() {
      // if (this.loginByPwdForm.mumberName === '' || this.loginByPwdForm.mumberPassword === '') {
      //   this.wrongInput = true
      //   this.emptyUsernamePwd = true
      // } else {
      //   this.changeLogin({
      //     Authorization: 'fake token',
      //     loginUser: {
      //       mumberName: this.loginByPwdForm.mumberName,
      //       mumberHeadImg: require('@/assets/avatar.jpeg'),
      //       id: 1
      //     }
      //   });
      //   this.$message.success("登录成功");
      //   this.$router.push('/home/dishes/categories');
      // }
        this.postServerRequest(this.$api.userLogInOut.userLoginByPwd, this.loginByPwdForm).then((res) => {
          console.log(res);
          if (res.data.code === 200) {
            // 登录成功
            // 将用户token保存到vuex中
            this.changeLogin({
               Authorization: res.data.token,
               loginUser: res.data.data
            });
            // 跳转界面
            this.$message.success("登录成功");
            this.$router.push('/home/dishes/categories');
          } else if (res.data.code === 600) {
            // 用户名或密码出错
            this.wrongInput = true;
            this.wrongUsernamePwd = true;
          } else {
            this.$message.warning('出错了，请联系软件管理员！');
          }
        })
    },
    resetInputBox() {
      this.wrongInput = false;
      this.emptyUsernamePwd = false;
      this.wrongUsernamePwd = false;
    },
    switchToForget() {
      this.notForget = !this.notForget
      this.$emit('forget')
    }
  }
}
</script>

<style scoped>
.el-form-item {
  width: 300px;
  margin: 10px auto !important;
}

/deep/ .el-input {
  border-bottom: 1px solid #f1f1f1;
  margin: 10px 0 10px;
}

/deep/ .el-input__inner {
  background-color: transparent;
  border: none;
  color: #f1f1f1;
}

/deep/ .el-input__inner:focus {
  outline: 0;
}

.loginBtn {
  width: 300px;
  margin: 30px 0;
}

.forgetPwdBtn {
  color: #f1f1f1;
  font-weight: lighter;
  float: right;
  font-size: 13px;
}

.forgetPwdBtn:hover {
  color: #ff9900;
}

.el-form-item label {
  float: left;
  text-align: left;
  width: 300px;
  color: #f1f1f1;
}

.el-checkbox {
  width: auto !important;
  font-weight: lighter;
}

/deep/ .el-input__inner[warningColor="warning"] {
  background-color: rgba(230, 0, 0, .4) !important;
  border-radius: 0;
  color: white !important;
}

.el-alert {
  height: 20px;
  border-radius: 0;
  font-size: 8px;
  padding-top: 12px;
}
</style>
