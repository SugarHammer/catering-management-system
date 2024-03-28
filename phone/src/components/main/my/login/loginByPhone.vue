<template>
  <div>
    <div class="goBackIcon">
      <router-link to="/notLogin">
        <i class="el-icon-arrow-left"></i>返回个人中心
      </router-link>
    </div>
    <div>
      <el-input v-model="userPhone" placeholder="请输入手机号" maxlength="11"
                oninput="value=value.replace(/[^\d]/g,'')"></el-input>
    </div>
    <p>未注册手机号验证后自动创建然狗餐饮会员账户</p>
    <div>
      <router-link :to="{path:'PhoneCode',query:{userPhone:this.userPhone}}">
        <el-button :disabled=disabled @click="getPhoneCode">获取短信验证</el-button>
      </router-link>
    </div>
    <el-row>
      <router-link to="loginByPwd">
        <el-col :span="13" style="font-size:13px;text-align:left">密码登录</el-col>
      </router-link>
      <router-link to="getBackPwd">
        <el-col :span="11" style="font-size:13px;text-align:right">密码找回</el-col>
      </router-link>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "loginByPhone",
  data() {
    return {
      userPhone: '',
      disabled: true,
    }
  },
  methods: {
    getPhoneCode() {
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
      })
    }
  },
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
h1 {
  font-weight: normal;
  margin-top: 30px;
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

.el-button.is-disabled, .el-button.is-disabled:focus, .el-button.is-disabled:hover {
  background-color: rgba(245, 196, 67, .4);
  color: darkgray;
}

.el-row {
  width: 300px;
  margin: 20px auto;
}

p {
  color: #C4C4CF;
  font-size: 12px;
  text-align: left;
  width: 300px;
  margin: 5px auto;
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
