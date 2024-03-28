<template>
  <div class="box-container">
    <div class="forgetPass-box">
      <i class="el-icon-arrow-left" @click="goBack"></i>
      <h4 class="forget-title">修改密码</h4>
    </div>
    <el-form :model="resetPwdForm" size="medium" class="resetPwd-form">
      <el-form-item prop="username">
        <el-input v-if="!wrongInput" type="text" v-model="resetPwdForm.phone" placeholder="输入邮箱"
                  style="margin-top:30px"></el-input>
        <div v-else>
          <el-alert v-if="emptyEmail" title="邮箱不能为空" type="error" :closable="false"></el-alert>
          <el-alert v-if="wrongEmailCode" title="邮箱验证码错误" type="error" :closable="false"></el-alert>
          <el-input warningColor="warning" type="text" v-model="resetPwdForm.email" @focus="resetInputBox"
                    placeholder="输入邮箱"></el-input>
        </div>
      </el-form-item>
      <el-form-item prop="pwd">
        <el-row :gutter="20" type="flex" align="middle">
          <el-col :span="14">
            <el-input v-if="!wrongInput||emptyEmail" type="text" v-model="resetPwdForm.confirmCode"
                      placeholder="输入验证码"></el-input>
            <el-input v-else warningColor="warning" type="text" v-model="resetPwdForm.confirmCode"
                      @focus="resetInputBox" placeholder="输入验证码"></el-input>
          </el-col>
          <el-col :span="6" style="float: left">
          <span v-if="codeShow" @click="getEmailCode">
            <el-button class="phoneCode-btn">发送验证码</el-button>
          </span>
            <span v-if="!codeShow">
            <el-button class="retry-btn">{{ count }}秒后重试</el-button>
          </span>
          </el-col>
        </el-row>
      </el-form-item>
      <el-form-item>
        <el-input type="password" show-password v-model="resetPwdForm.newPwd" placeholder="输入新密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input type="password" show-password v-model="resetPwdForm.confirmNewPwd" placeholder="确认新密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="resetPwd" class="login-btn">确认修改</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  name: "forgetPassword",
  data() {
    return {
      wrongInput: false,
      emptyEmail: true,
      wrongEmailCode: false,
      codeShow: true,
      resetPwdForm: {
        email: '',
        confirmCode: '',
        newPwd: '',
        confirmNewPwd: ''
      }
    }
  },
  methods: {
    goBack() {
      this.$emit('notForget')
    },
    resetInputBox() {
      this.wrongInput = false;
      this.emptyEmail = false;
    },
    //点击获取验证码，变为倒计时60秒按钮
    emailCodeSent() {
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
    getEmailCode() {
      let emailInput = this.resetPwdForm.email;
      if (emailInput === '') { // 输入邮箱为空时
        this.wrongInput = true;
        this.emptyEmail = true;
      } else {
        this.emptyEmail = false;
        this.emailCodeSent();
      }
    },
    resetPwd() {

    }
  }
}
</script>

<style scoped>
.box-container {
  height: 450px;
  background-color: rgba(0, 0, 0, .7);
  color: #f1f1f1 !important;
}

.forgetPass-box i {
  float: left;
  padding: 15px 0 0 15px;
  cursor: pointer;
}

.forgetPass-box .forget-title {
  font-weight: normal;
  padding-top: 10px;
  padding-right: 30px;
  display: inline-block;
}

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
