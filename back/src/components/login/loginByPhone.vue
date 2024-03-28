<template>
  <el-form :model="loginByPhoneForm" size="medium" class="login-form">
    <el-form-item prop="username">
      <el-input v-if="!wrongInput" type="text" v-model="loginByPhoneForm.phone" placeholder="输入手机号"
                style="margin-top:30px"></el-input>
      <div v-else>
        <el-alert v-if="emptyPhone" title="手机号不能为空" type="error" :closable="false"></el-alert>
        <el-alert v-if="wrongPhoneCode" title="手机号验证码错误" type="error" :closable="false"></el-alert>
        <el-input warningColor="warning" type="text" v-model="loginByPhoneForm.phone" @focus="resetInputBox"
                  placeholder="输入手机号"></el-input>
      </div>
    </el-form-item>
    <el-form-item prop="pwd">
      <el-row :gutter="20" type="flex" align="middle">
        <el-col :span="14">
          <el-input v-if="!wrongInput||emptyPhone" type="text" v-model="loginByPhoneForm.phoneCode"
                    placeholder="输入验证码"></el-input>
          <el-input v-else warningColor="warning" type="text" v-model="loginByPhoneForm.phoneCode"
                    @focus="resetInputBox" placeholder="输入验证码"></el-input>
        </el-col>
        <el-col :span="6" style="float: left">
          <span v-if="codeShow" @click="getPhoneCode">
            <el-button class="phoneCode-btn">发送验证码</el-button>
          </span>
          <span v-if="!codeShow">
            <el-button class="retry-btn">{{ count }}秒后重试</el-button>
          </span>
        </el-col>
      </el-row>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="login" class="login-btn">登录</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import {mapMutations} from "vuex";

export default {
  name: "loginByPhone",
  data() {
    return {
      wrongInput: false,
      emptyPhone: true,
      wrongPhoneCode: false,
      codeShow: true,
      count: '',
      timer: null,
      loginByPhoneForm: {
        phone: '',
        phoneCode: ''
      }
    }
  },
  methods: {
    ...mapMutations(['changeLogin']),
    //点击获取验证码，变为倒计时60秒按钮
    phoneCodeSent() {
      const TIME_COUNT = 60;  //倒计时60秒
      if (!this.timer) {
        this.count = TIME_COUNT;
        this.codeShow = false;
        this.timer = setInterval(() => {
          if (this.count > 0 && this.count <= TIME_COUNT) {
            this.count--;
          } else {
            this.codeShow = true;
            clearInterval(this.timer);
            this.timer = null;
          }
        }, 1000);
      }
    },
    // 获取验证码
    getPhoneCode() {
      let phoneInput = this.loginByPhoneForm.phone;
      if (phoneInput === '') { // 输入手机号为空时
        this.wrongInput = true;
        this.emptyPhone = true;
      } else {
        this.emptyPhone = false;
        this.phoneCodeSent();
        this.postServerRequest(this.$api.userLogInOut.userPhoneCodeSend, this.$qs.stringify({phone: this.loginByPhoneForm.phone})).then((res) => {
          if (res.data.code === 200) {
            this.$message.success("验证码发送成功");
          } else {
            this.$message.error("出错了！验证码发送失败。");
          }
        })
      }
    },
    // 提交登录请求
    login() {
      this.postServerRequest(this.$api.userLogInOut.userLoginByPhone, this.$qs.stringify(this.loginByPhoneForm)).then((res) => {
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
          this.$router.push('/home');
        } else if (res.data.code === 600) {
          // 验证码错误
          this.wrongInput = true;
          this.wrongPhoneCode = true;
        } else {
          this.$message.warning('出错了，请联系软件管理员！');
        }
      })
    },
    resetInputBox() {
      this.wrongInput = false;
      this.emptyPhone = false;
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

.login-btn {
  width: 300px;
  margin: 30px 0 30px;
}

.el-form-item label {
  float: left;
  text-align: left;
  width: 300px;
  color: #f1f1f1;
}

.phoneCode-btn, .retry-btn {
  border: 1px solid #ff9900;
  color: #ff9900;
  background: transparent;
}

.phoneCode-btn:hover, .phoneCode-btn:focus {
  color: #ff9900;
  border: 1px solid #ff9900;
  background-color: rgba(255, 153, 0, .1);
}

.retry-btn {
  pointer-events: none;
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
