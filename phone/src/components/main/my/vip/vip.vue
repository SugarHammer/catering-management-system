<template>
  <div class="vue">
    <header>
      <!--会员头像-->
      <div class="top_info">
        <div class="touxiang" @click="editTouXiang()">
          <img :src="imgSrc" alt="#">
        </div>
        <div class="Name-lev">
          <span>{{ userList.mumberName }}</span>
          <span>LV1</span>
        </div>
      </div>
      <!--会员姓名-->

      <!--  账户信息-->
      <div class="my-info">
        账户信息
        <i class="el-icon-edit" @click="Edit()"></i>
      </div>

      <div class="accountInfo">
        <ul>
          <!-- 手机号-->
          <li>
            手机号:{{ userList.mumberPhone }}
          </li>
          <!-- 积分-->
          <li>
            积分:99999豆豆
          </li>
          <!-- 邮箱-->
          <li>
            邮箱:{{ userList.mumberEmail }}
          </li>
          <!-- 修改密码-->
          <li>
            <el-button size="small" @click="editPwd()">修改密码</el-button>
          </li>
          <li>
            <el-button size="small" @click="quitLogin()">设置</el-button>
          </li>
        </ul>
      </div>

    </header>
    <!-- 修改头像对话框-->
    <el-dialog
        title="修改头像"
        :visible.sync="EditTouxiang"
        width="95%"
        class="abow_dialog">
      <form action="" ref="form1">
        <input type="file" name="file">
      </form>


      <!-- ************-->


      <span slot="footer" class="dialog-footer">
                <div class="juzhong">
                     <el-button @click="EditTouxiang = false">取 消</el-button>
                   <el-button type="primary" @click="saveTouXiang">确 定</el-button>
                </div>

  </span>
    </el-dialog>
    <!--   编辑资料    修改-->
    <el-drawer
        title="编辑资料"
        :visible.sync="drawer"
        :direction="direction"
        size='100%'>
      <el-form :model="userList" status-icon label-width="60px">
        <el-form-item label="用户名" :span="20">
          <el-input v-model="userList.mumberName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="userList.mumberEmail"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="saveInfo()" class="save">保存</el-button>
        </el-form-item>
      </el-form>
    </el-drawer>
    <!--退出登录-->
    <el-drawer
        title="设置"
        :visible.sync="quitdrawer"
        :direction="direction"
        size='100%'>
      <div class="accountInfoSetting">
        <ul>
          <li>
            账号与安全
            <div class="el-icon-arrow-right">
            </div>
          </li>

          <li>
            帮助与反馈
            <div class="el-icon-arrow-right">
            </div>
          </li>

          <li>
            关于然狗餐饮
            <div class="el-icon-arrow-right">
            </div>
          </li>
          <!-- 生日-->
          <li>
            插件
            <div class="el-icon-arrow-right">
            </div>
          </li>
          <li style="border-bottom: none">
            隐私
            <div class="el-icon-arrow-right">
            </div>
          </li>
          <li style="background-color: #f1f1f1;height: 20px;"></li>
          <li style="text-align: center;border-bottom: none" @click="changevip">
            切换账号
          </li>
          <li style="background-color: #f1f1f1;height: 10px;"></li>
          <li style="text-align: center" @click="quitNoLogin">
            退出
          </li>
          <li style="background-color: #f1f1f1;height: 20px;"></li>

        </ul>


      </div>
    </el-drawer>
    <!-- 修改密码-->
    <el-drawer
        title="修改密码"
        :visible.sync="editPassword"
        :direction="direction"
        size='100%'>
      <el-form :model="userList" status-icon label-width="80px">
        <el-form-item label="原始密码">
          <el-input type="password" v-model="oldPwd" show-password></el-input>
        </el-form-item>
        <el-form-item label="新密码">
          <el-input v-model="newPwd" type="password" show-password></el-input>
        </el-form-item>
        <el-form-item label="确认密码">
          <el-input v-model="surePwd" type="password" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click='savePwd' class="save">保存</el-button>
        </el-form-item>
      </el-form>

    </el-drawer>
  </div>
</template>

<script>
//用户名，手机号，密码，生日，邮箱

export default {
  name: "LoginMember",
  data() {
    return {

      oldPwd: '',
      newPwd: '',
      surePwd: '',
      EditTouxiang: false,
      // 抽屉
      drawer: false,
      editPassword: false,
      quitdrawer: false,
      direction: 'rtl',
      imgSrc: require('./../../../../assets/1.jpeg'),
      userList: {
        mumberName: '张三',
        mumberPassword: '123456',
        mumberPhone: '152468888',
        mumberEmail: 'zhangsan@123.com',
        mumberBirthday: '1992-5-10'

      }
    }
  },
  created() {
    this.userLoginData()
  },
  methods: {
    quitLogin() {
      this.quitdrawer = true

    },
    Edit() {
      // console.log(10)
      this.drawer = true
    },
    // 修改密码
    savePwd() {
      if (this.newPwd != this.surePwd) {
        this.$message.error('两次密码输入不一致')
      } else {
        let userId = localStorage.getItem('personInfo')
        let data = {
          id: userId,
          oldPassword: this.oldPwd,
          password: this.newPwd

        }
        console.log(data)
        this.$axios.post(this.$api.vip.editPassword, this.$qs.stringify(data)).then((res) => {
          var result = res.data
          if (result.code == 200) {
            this.$message.success('修改密码成功')
            this.oldPwd = ''
            this.newPwd = ''
            this.surePwd = ''
            this.editPassword = false
          }

        })


      }
    },
    editPwd() {
      this.editPassword = true
    },
    // 已登录用户的数据渲染
    userLoginData() {
      var ID = localStorage.getItem('personInfo')
      console.log(ID)
      this.$axios.get(this.$api.vip.MemberInfo + '?id=' + ID).then((res) => {
        var result = res.data
        console.log(111)
        console.log(result)
        if (result.code == 200) {
          this.userList = result.data
          this.imgSrc = '/api' + result.data.mumberHeadImg
        } else {
          this.$message.error('数据渲染失败')
        }
      })
    },
    // 修改用户信息
    saveInfo() {
      // var ID = localStorage.getItem('personInfo')
      console.log('修改用户信息')
      let data = {
        id: this.userList.id,
        mumberName: this.userList.mumberName,
        mumberEmail: this.userList.mumberEmail,
        mumberBirthday: this.userList.mumberBirthday
      }
      this.$axios.post(this.$api.vip.editMemberInfo, data).then((res) => {
        console.log(res)
        var result = res.data
        if (result.code == 200) {
          this.$message.success('修改用户资料成功!')
        } else {
          this.$message.error('修改用户信息失败')
        }
      })

    },
    // 修改头像
    editTouXiang() {
      console.log('修改用户头像')
      this.EditTouxiang = true
    },
    //上传图标事件


    // 保存头像
    saveTouXiang() {
      var ID = localStorage.getItem('personInfo')
      this.EditTouxiang = false
      let form2 = this.$refs.form1;
      let formData = new FormData(form2);
      formData.append('id', ID)
      console.log(formData)

      this.$axios.post(this.$api.vip.savetouxiang, formData, {
        headers: {
          'content-type': 'multipart/formdata'
        }
      }).then(res => {
        let result = res.data;
        console.log(result);
        this.imgSrc = '/api/' + res.data;
        console.log(this.imgSrc)
        this.userLoginData()
      })

    },
    // 切换账户
    changevip() {
      window.localStorage.removeItem('personInfo')
      this.$router.push('/loginByPwd')
    },
    // 退出
    quitNoLogin() {
      window.localStorage.removeItem('personInfo')
      this.$router.push('/notLogin')
    }


  },


}
</script>

<style scoped>
.juzhong {
  margin: auto;
}

.touxiang {
  width: 70px;
  height: 70px;
  border-radius: 50%;
  /*float: left;*/
  position: absolute;
  color: white;
  line-height: 70px;
  left: 40px;
}

.touxiang img {
  border: 1px solid white;
  width: 70px;
  height: 70px;
  border-radius: 50%;
  /*float: left;*/
  position: absolute;
  color: white;
  line-height: 70px;
  left: 0;
  /*top: 14px;*/
}

.avatar-uploader img {
  width: 100%;
  height: 100%;
  border-radius: 50%;
}

.el-form {
  width: 90%;
}

.el-drawer {
  padding: 20px;
}

.save {
  position: absolute;
  left: 0;
  background-color: #ff9900;
  border: none;
}

.my-info {
  height: 40px;
  line-height: 40px;
  background-color: rgb(249, 249, 249);
  font-size: 16px;
  color: black;
  text-align: left;
  padding-left: 10px;

}

.my-info i {
  float: right;
  line-height: 40px;
  margin-right: 20px;
}

/*姓名和会员*/
.Name-lev {
  /*border: 1px solid red;*/
  width: 150px;
  line-height: 100px;
  height: 100px;
  /*float: left;*/
  margin-left: 90px;

}

.Name-lev span:nth-of-type(1) {
  font-size: 20px;
  margin-right: 20px;
}

.top_info {
  padding: 30px;
  display: flex;
  align-items: center;
  background-color: #ffebcc;
  border-bottom: 1px solid #f1f1f1;
  color: #666666;
  height: 80px;
}

.accountInfo {
  height: 300px;
  padding: 20px;
}

.accountInfoSetting {
  height: 300px;
}

.accountInfo ul li {
  list-style: none;
  height: 50px;
  text-align: left;
  line-height: 50px;
  color: black;
  padding-left: 15px;
  padding-right: 15px;
  background-color: white;
}

.accountInfoSetting ul li {
  list-style: none;
  height: 50px;
  text-align: left;
  line-height: 50px;
  color: black;
  padding-left: 40px;
  padding-right: 40px;
  background-color: white;
  border-top: 1px solid #f1f1f1;
}

.accountInfo ul li div {
  color: #C4C4CF;
  font-size: 30px;
  float: right;
  height: 50px;
  line-height: 50px;
}

</style>
