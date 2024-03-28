<template>
  <div>
    <div class="tab_header">
      <!-- 用户名和头像-->
      <div class="userinfo">
        <div class="avtar"></div>
        <div class="u-name">
          <h2>{{username}}</h2>
        </div>
      </div>
      <ul>
        <li>个人信息</li>
      </ul>
    </div>
    <div class="tab_content">
      <div class="mydata">
        <el-form label-width="100px" :model="mumberRoleList">
          <el-form-item label="登录ID">
            <!--                    accountForm.mumberRoleList[0].-->
            <el-col :span="20">
              <el-input size="small" v-model="mumberRoleList.id" autocomplete="off" width="50%" :disabled="true"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="用户名">
            <el-col :span="20">
              <el-input size="small" v-model="mumberRoleList.mumberName" autocomplete="off" width="50%"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="密码">
            <el-col :span="20" style="text-align: left">
              <el-button v-if="notChangePwd" size="small" type="primary" id="editBtn" @click="changePwd">修改密码</el-button>
              <el-input v-if="!notChangePwd" size="small" v-model="newPwd" autocomplete="off" placeholder="请输入新密码"></el-input>
              <el-button v-if="!notChangePwd" size="small" plain @click="changePwd">取消修改</el-button>
            </el-col>
          </el-form-item>
          <el-form-item label="手机号">
            <el-col :span="20">
              <el-input size="small" v-model="mumberRoleList.mumberPhone" autocomplete="off" width="50%"></el-input>
            </el-col>
          </el-form-item>

        </el-form>
        <div class="savebtn">
          <el-button type="primary" size="small" @click="editForm" class="edit">保存修改</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "accountInfo",
  data() {
    return {
      // accountForm: {},
      username:"",
      mumberRoleList:
          {
            id: '',
            mumberName: '',
            mumberPassword: '',
            mumberPhone: ''
          },
      loginUserId: '',
      notChangePwd: true,
      newPwd: ''
    }
  },
  mounted() {
    this.getAccount()
  },
  methods: {
    getAccount() {
      this.loginUserId = localStorage.getItem('loginUserId')
      console.log(this.loginUserId)
      this.$axios.get(this.$api.System.accountInfo + '?id=' + this.loginUserId).then(res => {
        console.log(res)
        let result = res.data
        if (result.code === 200) {
          this.mumberRoleList.id = result.data.mumberRoleList[0].id
          this.mumberRoleList.mumberName = result.data.mumberRoleList[0].mumberName
          this.mumberRoleList.mumberPassword = result.data.mumberRoleList[0].mumberPassword
          this.mumberRoleList.mumberPhone = result.data.mumberRoleList[0].mumberPhone
          this.username = result.data.mumberRoleList[0].mumberName
          console.log(result.data.mumberRoleList[0].id)
        } else {
          this.message.error('数据库出错')
        }
      })
    },
    editForm: function () {
      let data = {
        // accountForm.mumberRoleList[0]
        id: this.mumberRoleList.id,
        mumberName: this.mumberRoleList.mumberName,
        mumberPassword: this.newPwd===''?this.mumberRoleList.mumberPassword:this.newPwd,
        mumberPhone: this.mumberRoleList.mumberPhone
      }
      this.$axios.post(this.$api.System.accountEdit, data).then(res => {
        var result = res.data
        console.log(res)
        if (result.code === 200) {
          this.$message.success('修改成功')
          this.username = this.mumberRoleList.mumberName
          this.getAccount()
        } else {
          this.$message.error('你修改的用户名已经存在')
        }
      })
      console.log(data)
    },
    changePwd() {
      this.notChangePwd = !this.notChangePwd;
      this.newPwd = ''
    }
  }
}
</script>

<style scoped>
.userinfo {
  height: 130px;
  line-height: 80px;
  display: flex;
  align-items: center;
  background-color: rgb(255, 255, 255);
  padding: 15px;
}

.savebtn {
  padding-bottom: 40px;
  padding-left: 100px;
  text-align: left;
}

/*头像*/
.avtar {
  width: 80px;
  height: 80px;
  border-radius: 50%;
  border: 1px solid #f1f1f1;
  background-image: url("./../../../assets/avatar.jpeg");
  background-size: 81px 82px;
  background-repeat: no-repeat;
  margin-left: 20px;
}

.avtar img {
  width: 80%;
  height: 80%;
}

.u-name {
  width: 180px;
  height: 80px;
}

.u-name h2 {
  height: 30px;
  color: rgb(255, 165, 0);
}

ul {
  height: 46px;
  display: flex;
  align-items: center;
  /*border: 1px solid rgb(255,153,23);*/
}

ul li {
  list-style-type: none;
}

.tab_header {
  color: black;
  background-color: rgb(255, 235, 204);
}

.tab_header ul li {
  float: left;
  height: 30px;
  width: 80px;
  line-height: 30px;
  margin-left: 20px;
  /*border-left: 5px solid rgb(255,165,0);*/

}

.tab_header ul {
  overflow: hidden;
}

.tab_content {
  background-color: rgb(255, 255, 255);
  position: relative;
}

.active {
  color: rgb(255, 165, 0);
}

.mydata {
  width: 600px;
  padding-top: 30px;
}
</style>
