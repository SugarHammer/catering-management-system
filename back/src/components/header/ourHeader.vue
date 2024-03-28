<template>
  <div style="height: 60px">
    <div class="collapseBtn-box" @click="changeCollapseStatus">
      <i v-if="!navbarIsCollapse" class="el-icon-s-fold"></i>
      <i v-else class="el-icon-s-unfold"></i>
    </div>
    <div class="userInfo-box">
      <span>欢迎，{{ username }}！</span>
      <el-image
          style="width: 40px; height: 40px;border-radius: 50%"
          :src="imgSrc">
      </el-image>
      <el-button @click="logout" class="logoutBtn" size="mini">退出登录</el-button>
    </div>
  </div>
</template>

<script>
import {mapMutations} from "vuex";

export default {
  name: "ourHeader",
  data() {
    return {
      username: localStorage.getItem('loginUsername'),
      // imgSrc: localStorage.getItem('loginHeadImg'),
      imgSrc: require('@/assets/avatar.jpeg'),
      navbarIsCollapse: false
    }
  },
  methods: {
    ...mapMutations(['changeLogin']),
    changeCollapseStatus() {
      this.navbarIsCollapse = !this.navbarIsCollapse
      this.$emit('changeCollapseStatus', this.navbarIsCollapse)
    },
    logout() {
      // this.changeLogin({
      //   Authorization: '',
      //   loginUser: {}
      // });
      // this.$message.success("退出登录成功");
      // this.$router.push('/login');
      this.getServerRequest(this.$api.userLogInOut.userLogOut).then((res) => {
        if (res.data.code===200) {
          this.changeLogin({
            Authorization: '',
            loginUser: {}
          });
          localStorage.clear();
          this.$message.success("退出登录成功");
          this.$router.push('/login');
        } else {
          this.$message.error("系统出故障了，退出登录失败");
        }
      })
    }
  }
}
</script>

<style scoped>
.collapseBtn-box {
  padding: 2px 0 0 0;
  float: left;
}

.collapseBtn-box i {
  color: #777;
  cursor: pointer;
}

.collapseBtn-box i:hover {
  color: #ff9900;
}

.userInfo-box {
  height: 60px;
  float: right;
}

.userInfo-box span {
  color: #666666;
}

.userInfo-box span,
.userInfo-box .el-image {
  vertical-align: middle;
  margin: 0 5px;
  font-size: 14px;
}

.logoutBtn {
  margin-left: 20px;
}

.logoutBtn:hover {
  border: 1px solid rgba(255, 153, 0, .4);
  color: #ff9900;
  background-color: rgba(255, 153, 0, .2);
}
</style>
