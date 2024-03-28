<template>
  <div>
    <div class="goBackIcon">
      <router-link to="/loginByPwd">
        <i class="el-icon-arrow-left"></i>返回登录
      </router-link>
    </div>
    <h3>请填写你要找回密码的账号</h3>
    <div>
      <el-input v-model="userPhone" placeholder="请输入手机号" maxlength="11"
                oninput="value=value.replace(/[^\d]/g,'')"></el-input>
    </div>
    <div class="loginBtn">
      <div>
        <el-button :disabled=disabled>下一步</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "getBackPwd",
  data() {
    return {
      userPhone: '',
      disabled: true,

    }
  },
  methods: {},
  watch: {
    //监听账号密码的改变
    userPhone(val) {
      if (this.userPhone.length > 0 && this.userPhone.length === 11 && this.userPhone.charAt(0) == 1) {
        this.disabled = false  //启用按钮
      }
      if (this.userPhone.length === 0) {
        this.disabled = true //禁用按钮
      }
    },
  }
}
</script>

<style scoped>
h3 {
  font-weight: normal;
  margin-top: 30px;
  width: 300px;
}

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

.el-button:hover {
  color: black;
}

.loginBtn {
  position: relative;
}

>>> .el-button.is-disabled, .el-button.is-disabled:focus, .el-button.is-disabled:hover {
  background-color: rgba(245, 196, 67, .4);
  color: darkgray;
}

.el-row {
  margin-top: 30px;
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
