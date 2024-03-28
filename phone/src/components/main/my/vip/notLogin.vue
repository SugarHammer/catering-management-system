<template>
  <div>
    <div class="headerImg">
      <img src="./../../../../assets/1.jpeg" alt="#">
      <router-link to="/loginByPwd"><span>未登录</span></router-link>
    </div>
    <div class="Mlogin">
      <router-link to="/loginByPwd">
        <p>
          <el-button plain class="login">登录</el-button>
        </p>
      </router-link>
      <router-link to="/loginByPhone">
        <p>
          <el-button plain type="primary" class="reg">注册</el-button>
        </p>
      </router-link>
    </div>

  </div>
</template>
<script>
import Vue from 'vue';
import {Icon} from 'vant';

Vue.use(Icon);
export default {
  data() {
    return {
      // url:require('../../../assets/noLogin.png')
    }
  },
}
</script>
<style scoped>
a {
  text-decoration: none;
}

.headerImg img {
  border: 1px solid white;
  width: 70px;
  height: 70px;
  vertical-align: middle;
  border-radius: 50%;
  margin-right: 20px;
  float: left;
}

.headerImg span {
  color: #666666;
  line-height: 80px;
  position: absolute;
  left: 50%;
}

.headerImg {
  padding: 30px;
  background-color: #ffebcc;
  height: 80px;
  position: relative;
  border-bottom: 1px solid #f1f1f1;
}

.Mlogin {
  margin-top: 100px;
}

.Mlogin P {
  padding-top: 20px;
}

.Mlogin P .login {
  width: 80%;
  border-radius: 40px;
}

.Mlogin P .reg {
  width: 80%;
  border-radius: 40px;
}

</style>
