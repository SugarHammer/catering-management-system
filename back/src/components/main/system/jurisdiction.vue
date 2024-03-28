<template>
  <div>
    <div>
      <div class="sub">
        <el-row class="btnsBar" :gutter="15">
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-button type="primary" size="small" plain class="addBtn" @click="addRole">新增</el-button>
            </div>
          </el-col>
          <el-col :span="5">
            <div class="grid-content bg-purple">
              <el-input size="small" v-model="input" placeholder="请输入角色名搜索"></el-input>
            </div>
          </el-col>
          <el-col :span="2">
            <el-button size="small" class="searchBtn" type="primary" @click="searchBtn">搜索</el-button>
          </el-col>
        </el-row>
      </div>

    </div>
    <div class="tableBox">
      <template>
        <el-table
            stripe
            :data="jurisData"
            style="width: 100%"
            :header-cell-style="{
                textAlign: 'center',
                background: 'orange',
                color:'white'}"
            :cell-style="{ textAlign: 'center' }">
          <el-table-column
              prop="id"
              label="ID">
          </el-table-column>
          <el-table-column
              prop="mumberName"
              label="用户名">
          </el-table-column>
<!--          <el-table-column-->
<!--              prop="mumberPassword"-->
<!--              label="密码">-->
<!--          </el-table-column>-->
          <el-table-column
              prop="mumberPhone"
              label="手机号">
          </el-table-column>
          <el-table-column
              prop="roleName"
              label="角色名">
            <!--                        -->
          </el-table-column>
          <el-table-column
              label="操作" width="300">
            <template slot-scope="scope">
              <el-button type="success" size="small" @click="setRole(scope.row)" class="edit set" id="editBtn">分配</el-button>
              <el-button type="success" size="small" @click="editRole(scope.row)" class="edit editBtn">修改</el-button>
              <el-button type="danger" size="small" @click="del(scope.row.id)" class="del" id="delBtn">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页区域-->
        <el-pagination
            background
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page.sync="queryInfo.pagenum"
            :page-size="queryInfo.pagesize"
            layout="prev, pager, next"
            :total="total"
            class="paginationBar">
        </el-pagination>
        <div class="clear"></div>
      </template>
    </div>
    <!--添加对话框-->
    <el-dialog title="新增角色" :append-to-body="true" :visible.sync="dialogFormVisible" width="600px">
      <el-form :model="addform">
        <el-form-item label="角色名" :label-width="formLabelWidth">
          <el-select size="small" v-model="value2" placeholder="请选择">
            <el-option
                    v-for="item in options"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
<!--        <el-form-item label="角色ID" :label-width="formLabelWidth">-->
<!--          <el-input size="small" v-model="addform.roleId" autocomplete="off"></el-input>-->
<!--        </el-form-item>-->
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input size="small" v-model="addform.mumberName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input size="small" v-model="addform.mumberPassword" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机号" :label-width="formLabelWidth">
          <el-input size="small" v-model="addform.mumberPhone" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="dialogFormVisible = false">取 消</el-button>
        <el-button size="small" type="primary" @click="addSave">确 定</el-button>
      </div>
    </el-dialog>
    <!--编辑对话框-->
    <el-dialog title="编辑角色" :append-to-body="true" :visible.sync="editdialogFormVisible" width="600px">
      <el-form :model="editform">
        <el-form-item label="ID" :label-width="formLabelWidth">
          <el-input size="small" v-model="editform.id" autocomplete="off" :disabled="true"></el-input>
        </el-form-item>
        <!--                <el-form-item label="角色名" :label-width="formLabelWidth">-->
        <!--                    <el-input v-model="editform.roleName" autocomplete="off"></el-input>-->
        <!--                </el-form-item>-->
        <el-form-item label="用户名" :label-width="formLabelWidth">
          <el-input size="small" v-model="editform.mumberName" autocomplete="off"></el-input>
        </el-form-item>
<!--        <el-form-item label="密码" :label-width="formLabelWidth">-->
<!--          <el-input size="small" v-model="editform.mumberPassword" autocomplete="off"></el-input>-->
<!--        </el-form-item>-->
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-col :span="20" style="text-align: left">
            <el-button v-if="notChangePwd" size="small" type="primary"  @click="changePwd"class="editBtn">修改密码</el-button>
            <el-input v-if="!notChangePwd" size="small" v-model="editform.mumberPassword" autocomplete="off" placeholder="请输入新密码"></el-input>
            <el-button v-if="!notChangePwd" size="small" plain @click="changePwd">取消修改</el-button>
          </el-col>
        </el-form-item>
        <el-form-item label="手机号" :label-width="formLabelWidth">
          <el-input size="small" v-model="editform.mumberPhone" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="editdialogFormVisible = false">取 消</el-button>
        <el-button size="small" type="primary" @click="save()">确 定</el-button>
      </div>
    </el-dialog>
    <!--  修改权限-->
    <!--编辑对话框-->
    <el-dialog title="修改权限" :append-to-body="true" :visible.sync="setdialogFormVisible" width="600px">
      <el-form :model="setform">
        <el-form-item label="角色名" :label-width="formLabelWidth">
          <el-select size="small" v-model="value2" placeholder="请选择">
            <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="setdialogFormVisible = false">取 消</el-button>
        <el-button size="small" type="primary" @click="saveset()">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "jurisdiction",
  data() {
    return {
      notChangePwd: true,
      RoleState: '',
      input: '',
      value2: '',
      options: [{
        value: '2',
        label: '管理员'
        },
        {
          value: '3',
          label: '操作员'
        }
      ],
      value: '',
      jurisData: [],
      queryInfo: {
        query: '',
        // 当前的页数
        pagenum: 1,
        // 当前每页显示多少条数据
        pagesize: 10
      },
      // 切换01
      // 总页数
      total: 0,
      // 添加
      dialogTableVisible: false,
      dialogFormVisible: false,
      // 编辑
      editdialogFormVisible: false,
      setdialogFormVisible: false,
      // 权限
      setform: {
        id: '',
        roleName: '',
        mid: ''
      },
      addform: {
        roleId:'' ,
        roleName: '',
        mumberName: '',
        mumberPassword: '',
        mumberPhone: ''
      },
      editform: {
        id: '',
        roleName: '',
        mumberName: '',
        mumberPassword: '',
        mumberPhone: '',
        roleJurisdiction: ''
      },
      formLabelWidth: '100px'
    }
  },
  methods: {
    changePwd() {
      this.notChangePwd = !this.notChangePwd;
      this.newPwd = ''
    },
    // 数据显示
    getTableList: function () {
      this.$axios.get(this.$api.System.DisplayjusrisAllData + '?page=' + this.queryInfo.pagenum).then((res) => {
        console.log(res)
        var result = res.data
        // console.log(result)
        let jurisData = []
        if (result.code === 200) {
          let data = result.data
          data.forEach(function (item) {
            // console.log(item.mumberRoleList)
            item.mumberRoleList.forEach(function (mumber) {
              mumber.roleName = item.roleName
              mumber.roleJurisdiction = item.roleJurisdiction
              jurisData.push(mumber)
            })
          })
          this.jurisData = jurisData
          this.total = jurisData.length
        } else {
          this.$message.error('服务出错')
        }
      })

    },
    // 新增
    addRole() {
      this.dialogFormVisible = true
    },
    addSave() {
      let data = {
        roleId: this.value2,
        mumberName: this.addform.mumberName,
        mumberPassword: this.addform.mumberPassword,
        mumberPhone: this.addform.mumberPhone,
        // roleName:this.value2
      }
      console.log(data)
      this.$axios.post(this.$api.System.newAdd,data).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.$message.success('新增成功')
          this.getTableList()
          this.addform = {}
        } else {
          this.$message.error('新增失败')
        }
      })

      this.dialogFormVisible = false
    },
    // 修改
    editRole(row) {
      // console.log(row.roleName)
      this.editform.id = row.id
      this.editform.roleName = row.roleName
      this.editform.mumberName = row.mumberName
      this.editform.mumberPassword = ''
      this.editform.mumberPhone = row.mumberPhone
      this.editdialogFormVisible = true
    },
    save() {
      let data = {
        id: this.editform.id,
        roleName: this.editform.roleName,
        mumberPassword: this.editform.mumberPassword,
        mumberName: this.editform.mumberName,
        mumberPhone: this.editform.mumberPhone,
      }
      console.log(data)
      this.$axios.post(this.$api.System.jusrisEdit, data).then((res) => {
        console.log(res)
        if (res.data.code === 200) {
          this.$message.success('修改成功')
          this.getTableList()
        } else {
          this.$message.error('服务出错')
        }
      })
      this.editdialogFormVisible = false

    },
    // 搜索
    searchBtn() {
      this.$axios.get(this.$api.System.SelectByName + '?page=' + this.queryInfo.pagenum
          + "&name=" + this.input).then((res) => {
        let result = res.data
        let jurisData = []
        console.log(result.data)
        if (result.code === 200) {
          let data = result.data
          data.forEach(function (item) {
            // console.log(item.mumberRoleList)
            item.mumberRoleList.forEach(function (mumber) {
              mumber.roleName = item.roleName
              mumber.roleJurisdiction = item.roleJurisdiction
              jurisData.push(mumber)
            })
          })
          this.jurisData = jurisData
          this.total = jurisData.length
        }
      }).catch(err => {
        console.log(err)
      })
    },
    // 删除
    del(id) {
      this.$confirm('请确认是否删除?', '提示', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: "warning"
      }).then(() => {
        let data = {id: id}
        this.$axios.post(this.$api.System.jusrisDel, this.$qs.stringify(data)).then(res => {
          let result = res.data
          if (result.code === 200) {
            this.$message({
              type: 'success',
              message: '删除成功'
            })
            this.getTableList()
          }
        })
      })

    },
    // ************权限修改区域****************
    setRole(row) {
      // console.log(row.row.roleJurisdiction)
      console.log(row)
      // this.setform.mid= row.mumberList.id
      this.setform.id = row.id
      this.setdialogFormVisible = true
      console.log(row)

    },
    saveset() {
      let data = {
        roleId: parseInt(this.value2),
        id: this.setform.id
      }
      // this.$qs.stringify(data)
      console.log(data)
      this.$axios.post(this.$api.System.setRole, this.$qs.stringify(data)).then(res => {
        console.log(res.data)
        if (res.data.code === 200) {
          this.$message.success('更新成功')
          this.getTableList()

        } else {
          this.$message.error('服务出错')
        }

      })
      this.setdialogFormVisible = false


    },

    // ******************************************

    // 监听pagesize改变的事件
    handleSizeChange(newSize) {
      console.log(newSize)
      this.queryInfo.pagesize = newSize
      this.getTableList()
    },
    // 监听页码值改变的事件
    handleCurrentChange(newPage) {
      console.log(newPage)
      this.queryInfo.pagenum = newPage
      this.getTableList()
    }

  },
  mounted() {
    this.getTableList()
  }
}
</script>

<style scoped>
@import "./../../../styles/commonStyles.css";
.editBtn{
  background-color: white;
  border: 1px solid #0DC316;
  color: #0DC316;
}
</style>
