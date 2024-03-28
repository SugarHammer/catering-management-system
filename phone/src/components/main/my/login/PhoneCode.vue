<template>
  <div>
    <div class="goBackIcon">
      <router-link to="/loginByPhone">
        <i class="el-icon-arrow-left"></i>返回输入手机号
      </router-link>
    </div>
    <div class="phoneCode">
      <h2>输入验证码</h2>
      <p>验证码已发送至{{ userPhone }}</p>
      <el-row :gutter="20">
        <el-col :span="4">
          <div class="grid-content bg-purple">
            <el-input v-model="input1" maxlength="1"
                      oninput="value=value.replace(/[^\d]/g,'')" :autofocus="true" ref="input1"></el-input>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple">
            <el-input v-model="input2" maxlength="1" oninput="value=value.replace(/[^\d]/g,'')" ref="input2"></el-input>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple">
            <el-input v-model="input3" maxlength="1" oninput="value=value.replace(/[^\d]/g,'')" ref="input3"></el-input>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple">
            <el-input v-model="input4" maxlength="1" oninput="value=value.replace(/[^\d]/g,'')" ref="input4"></el-input>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple">
            <el-input v-model="input5" maxlength="1" oninput="value=value.replace(/[^\d]/g,'')" ref="input5"></el-input>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple">
            <el-input v-model="input6" maxlength="1" oninput="value=value.replace(/[^\d]/g,'')" ref="input6"></el-input>
          </div>
        </el-col>
      </el-row>
      <p id="getCode">
        <span v-show="isShow">{{ content }}</span>
        <span v-show="isShow1" @click="getCodeAgain" class="overCount">{{ content1 }}</span></p>
      <div class="loginBtn">
        <div>
          <el-button :disabled=disabled @click="submitLogin">确定</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PhoneCode",
  data() {
    return {
      content: '',
      content1: '重新获取验证码',
      totalTime: 60,
      disabled: true,
      isShow: true,
      isShow1: false,
      userPhone: this.$route.query.userPhone,
      input1: '',
      input2: '',
      input3: '',
      input4: '',
      input5: '',
      input6: '',
    }
  },
  created() {
    //输入电话号码后点击提交
    this.countDown()
  },
  methods: {
    // 点击重新获取验证码，向后台发起请求重新返回验证码
    getCodeAgain() {
      this.isShow = true
      this.isShow1 = false
      this.content = this.totalTime + "秒后请重新获取验证码"
      //设置定时器
      let contS = setInterval(() => {
        this.content = this.totalTime + "秒后请重新获取验证码"
        this.totalTime--
        if (this.totalTime < 0) {     //当倒计时小于0时清除定时器
          clearInterval(contS)
          this.isShow = false
          this.isShow1 = true
          this.totalTime = 60
        }
      }, 1000)

      let data = {
        phone: this.userPhone
      }
      this.$axios.post('/api/login/sendCode', this.$qs.stringify(data)).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.$message({
            type: "success",
            message: '验证码获取成功',
            center: true,
            duration: 800
          })
        }
      }).catch(err => {
        console.log(err)
      })
    },
    //输入验证码 提交登录
    submitLogin() {
      let data = {
        phone: this.userPhone,
        phoneCode: this.input1 + this.input2 + this.input3 + this.input4 + this.input5 + this.input6
      }
      console.log(data)
      this.$axios.post('/api/login/phoneLogin', this.$qs.stringify(data)).then(res => {
        let result = res.data
        if (result.code === 200) {
          console.log(result)
          this.$message({
            type: "success",
            message: '登录成功',
            center: true,
            duration: 800
          })
          let personInfo = result.data.id
          window.localStorage.setItem("personInfo", JSON.stringify(personInfo))
          this.$router.push({path: '/vip'})
        } else {
          this.$message({
            type: "error",
            message: '验证码错误，请重新输入',
            center: true,
            duration: 800
          })
          this.input1 = ''
          this.input2 = ''
          this.input3 = ''
          this.input4 = ''
          this.input5 = ''
          this.input6 = ''
        }
      })
    },
    countDown() {
      //设置定时器
      let clock = window.setInterval(() => {
        this.content = this.totalTime + "秒后请重新获取验证码"
        this.totalTime--
        if (this.totalTime < 0) {     //当倒计时小于0时清除定时器
          window.clearInterval(clock)
          this.isShow = false
          this.isShow1 = true
          this.totalTime = 60
          console.log(this.totalTime)
        }
      }, 1000)
    }
  },
  computed: {
    //定义监听对象
    watchPhoneCode() {
      const {input1, input2, input3, input4, input5, input6} = this
      return {input1, input2, input3, input4, input5, input6}
    }
  },
  watch: {
    //监听用户输入验证码
    watchPhoneCode(val) {
      if (this.input1.length > 0 && this.input2.length > 0 && this.input3.length > 0
          && this.input4.length > 0 && this.input5.length > 0 && this.input6.length > 0) {
        this.disabled = false
      }
      if (this.input1.length == 0 || this.input2.length == 0 || this.input3.length == 0
          || this.input4.length == 0 || this.input5.length == 0 || this.input6.length == 0) {
        this.disabled = true
      }
      if (this.input1.length > 0) {
        this.$refs.input1.blur();
        this.$refs.input2.focus();
      }
      if (this.input2.length > 0) {
        this.$refs.input2.blur();
        this.$refs.input3.focus();
      }
      if (this.input3.length > 0) {
        this.$refs.input3.blur();
        this.$refs.input4.focus();
      }
      if (this.input4.length > 0) {
        this.$refs.input4.blur();
        this.$refs.input5.focus();
      }
      if (this.input5.length > 0) {
        this.$refs.input5.blur();
        this.$refs.input6.focus();
      }
    }
  }
}
</script>

<style scoped>
#getCode {
  margin-top: 60px;
}

.phoneCode {
  width: 300px;
  margin: 0 auto;
}

h2 {
  font-weight: normal;
  letter-spacing: 3px;
  text-align: left;
  margin-top: 20px;
}

.countNum {
  color: black;
}

.overCount {
  color: orange;
}

p {
  text-align: left;
}

>>> .el-input__inner {
  border: none;
  font-size: 22px;
  margin-top: 20px;
  text-align: center;
  caret-color: orange;
  padding: 0;
}

.loginBtn {
  width: 300px;
  margin: 0 auto;
}
.el-button {
  width: 100%;
  margin-top: 60px;
  background-color: #F5C342;
  border: none;
  height: 40px;
  border-radius: 10px;
  font-size: 18px;
  color: #f1f1f1;
}

.el-button.is-disabled, .el-button.is-disabled:focus, .el-button.is-disabled:hover {
  background-color: rgba(245, 196, 67, .4);
  color: darkgray;
}

.el-row {
  margin-top: 30px;
}

.el-row {
  margin-bottom: 20px;
}

.el-row:last-child {
  margin-bottom: 0;
}

.el-col {
  border-radius: 4px;
}

.bg-purple-dark {
  /*background: #99a9bf;*/
  border-bottom: 1px solid darkgray;
}

.bg-purple {
  /*background: #d3dce6;*/
  border-bottom: 1px solid darkgray;
}

.bg-purple-light {
  /*background: #e5e9f2;*/
  border-bottom: 1px solid darkgray;
}

.grid-content {
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}

.el-button:hover {
  color: black;
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
