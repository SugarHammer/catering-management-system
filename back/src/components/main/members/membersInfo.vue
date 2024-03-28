<template>
  <div>
    <!--搜索新增-->
    <div class="sub">
      <div>
        <el-row class="btnsBar" :gutter="15">
          <el-col :span="4" v-if="haveAuthToOperate">
            <div class="grid-content bg-purple">
              <el-button size="small" type="primary" plain class="sub-btn addBtn" @click="dialogFormVisible=true">新增
              </el-button>
              <el-button size="small" type="primary" plain class="sub-btn addBtn" @click="pdialogFormVisible=true">批量新增
              </el-button>
            </div>
          </el-col>
          <el-col :span="2" v-if="haveAuthToDownload">
            <div class="grid-content bg-purple">
              <el-button size="small" plain class="sub-btn" @click="Download">下载数据</el-button>
            </div>
          </el-col>
          <el-col :span="4" :offset="2">
            <div class="grid-content bg-purple">
              <el-input size="small" v-model="input" placeholder="请输入内容" class="input-with-select"></el-input>
            </div>
          </el-col>
          <el-col :span="2">
            <div class="grid-content bg-purple">
              <el-button size="small" type="primary" @click="searchMumber">搜索</el-button>
            </div>
          </el-col>
        </el-row>
      </div>

    </div>
    <!--表格-->
    <div class="tableBox">
      <el-table
          stripe
          :header-cell-style="{
                textAlign: 'center',
                background: 'orange',
                 color:'white'}"
          @selection-change="handleSelectionChange"
          :cell-style="{ textAlign: 'center' }"
          :data="tableData"
          style="width: 100%">
        <el-table-column
            type="selection">
        </el-table-column>
        <el-table-column
            width="70px"
            label="会员id"
            prop="id">
        </el-table-column>
        <el-table-column
            width="85px"
            label="会员姓名"
            prop="mumberName">
        </el-table-column>
        <el-table-column
            width="115px"
            label="会员电话"
            prop="mumberPhone">
        </el-table-column>
        <el-table-column
            width="105px"
            label="会员生日"
            prop="mumberBirthday">
        </el-table-column>
        <el-table-column
            label="会员余额"
            prop="mumberBalance">
        </el-table-column>
        <el-table-column
            width="115px"
            label="会员累计消费"
            prop="mumberTotalCost">
        </el-table-column>
        <el-table-column
            width="85px"
            label="会员积分"
            prop="mumberIntegral">
        </el-table-column>
        <el-table-column
            width="85px"
            label="会员等级"
            prop="mumberLevel.numberLevelName">
        </el-table-column>
        <el-table-column
            width="150px"
            label="会员注册时间"
            prop="mumberRergistrationTime">
        </el-table-column>
        <el-table-column v-if="haveAuthToOperate" label="操作" width="160px">
          <template slot-scope="scope">
            <el-button type="success" size="small" id="editBtn" @click="edit(scope.row)">编辑</el-button>
            <el-button type="danger" size="small" id="delBtn" @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          background
          layout="prev, pager, next"
          :total="total"
          @current-change="handleCurrentChange"
          class="paginationBar">
      </el-pagination>
      <div class="clear"></div>
    </div>
    <!--添加弹出框-->
    <el-dialog title="添加会员信息" :visible.sync="dialogFormVisible" :append-to-body="true" width="600px">
      <el-form :model="addData">
        <el-form-item label="会员姓名" :label-width="formLabelWidth">
          <el-input size="small" v-model="addData.mumberName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="会员电话" :label-width="formLabelWidth">
          <el-input size="small" v-model="addData.mumberPhone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="会员积分" :label-width="formLabelWidth">
          <el-input size="small" v-model="addData.mumberIntegral" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="会员余额" :label-width="formLabelWidth">
          <el-input size="small" v-model="addData.mumberBalance" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="dialogFormVisible = false">取消</el-button>
        <el-button size="small" type="primary" @click="add">确定</el-button>
      </div>
    </el-dialog>
    <!--批量新增弹出框-->
    <el-dialog title="新增上传会员信息" :visible.sync="pdialogFormVisible" :append-to-body="true" width="600px">
      <el-row :gutter="30">
        <el-col :span="10" :offset="4">
          <div class="grid-content bg-purple">
            <el-button size="small" @click="Downloadmodul">下载模板</el-button>
          </div>
        </el-col>
        <el-col :span="10">
          <div class="grid-content bg-purple-light">
            <form name="load" ref="upload">
<!--              <input type="file" name="file">-->
              <el-upload
                  class="upload-demo"
                  :action="this.$api.members.upload"
                  multiple
                  :on-preview="handlePreview"
                  :file-list="fileList"
                  :on-success="uploadSuccess">
                <el-button size="small" type="primary">上传文件</el-button>
              </el-upload>
            </form>
          </div>
        </el-col>
      </el-row>
      <div slot="footer"
           class="dialog-footer">
        <el-button size="small" @click="pdialogFormVisible=false">取 消</el-button>
        <el-button size="small" type="primary" @click="pdialogFormVisible=false">确定</el-button>
      </div>

    </el-dialog>
    <!--编辑弹出框-->
    <el-dialog title="编辑会员信息" :visible.sync="editFormVisible" :append-to-body="true" width="600px">
      <el-form :model="editData">
        <el-form-item label="会员姓名" :label-width="formLabelWidth">
          <el-input size="small" v-model="editData.mumberName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="会员电话" :label-width="formLabelWidth">
          <el-input size="small" v-model="editData.mumberPhone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="会员生日" :label-width="formLabelWidth">
          <el-input size="small" v-model="editData.mumberBirthday" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="会员密码" :label-width="formLabelWidth">
          <el-input size="small" v-model="editData.mumberPassword" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="会员余额" :label-width="formLabelWidth">
          <el-input size="small" v-model="editData.mumberBalance" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="会员积分" :label-width="formLabelWidth">
          <el-input size="small" v-model="editData.mumberIntegral" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="editFormVisible = false">取消</el-button>
        <el-button size="small" type="primary" @click="save">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "membersInfo",
  data() {
    return {
      input: '',
      formLabelWidth: '120px ',
      dialogFormVisible: false,
      pdialogFormVisible: false,
      tableData: [],
      editFormVisible: false,
      total: 0,
      currentPage: 1,
      multipleSelection: [],
      ids: '',
      addData: {
        mumberName: '',
        mumberPhone: '',
        mumberBalance: '',
        mumberIntegral: '',
      },
      editData: {
        id: '',
        mumberName: '',
        mumberPhone: '',
        mumberBirthday: '',
        mumberPassword: '',
        mumberIntegral: '',
        mumberBalance: ''
      },
      fileList: []
    }
  },
  methods: {
    handleEdit(index, row) {
      console.log(index, row);
    },
    handleDelete(index, row) {
      console.log(index, row);
    },
    //数据渲染
    membersInfo() {
      this.$axios.get(this.$api.members.membersInfo + '?limit=10&page=' + this.currentPage).then((res) => {
        console.log(res.data)
        let result = res.data
        if (res.data.code === 200) {
          this.tableData = result.data
          this.total = res.data.count
        } else {
          this.$alert(result.message)
        }
      }).catch(() => {
        this.$alert('请求出错')
      })
    },
    //添加
    add() {
      let data = {
        mumberName: this.addData.mumberName, mumberPhone: this.addData.mumberPhone,
        mumberBalance: this.addData.mumberBalance, mumberIntegral: this.addData.mumberIntegral,
      }
      this.$axios.post(this.$api.members.addinsert, data).then(res => {
        let result = res.data
        console.log(result)
        if (result.code === 200) {
          this.membersInfo();
          //对象
          this.addData = {};
        }
        this.$message({
          type: 'success',
          message: '添加成功!'
        })
        this.dialogFormVisible = false;
      }).catch(err => {
        console.log(err)
      })
    },
    //删除
    del(id) {
      console.log(id);
      this.$confirm('请确认是否删除？', '提示', {
        confirmButtonText: '确定',
        cancelButtonClass: '取消',
        type: 'warning'
      }).then(() => {
        let data = {id: id}
        this.$axios.post(this.$api.members.del, this.$qs.stringify(data)).then(res => {
          let result = res.data
          console.log(res)
          if (result.code === 200) {
            this.$message({
              type: 'success',
              message: '删除成功！'
            });
            this.membersInfo();
          }
        })
      })
    },
    //编辑
    edit(row) {
      console.log(row)
      this.editData.id = row.id
      this.editData.mumberName = row.mumberName
      this.editData.mumberPhone = row.mumberPhone
      this.editData.mumberBirthday = row.mumberBirthday
      this.editData.mumberPassword = row.mumberPassword
      this.editData.mumberIntegral = row.mumberIntegral
      this.editData.mumberBalance = row.mumberBalance
      this.editFormVisible = true
    },
    //修改保存
    save() {
      let data = {
        id: this.editData.id,
        mumberName: this.editData.mumberName,
        mumberPhone: this.editData.mumberPhone,
        mumberBirthday: this.editData.mumberBirthday,
        mumberPassword: this.editData.mumberPassword,
        mumberIntegral: this.editData.mumberIntegral,
        mumberBalance: this.editData.mumberBalance
      }
      this.$axios.post(this.$api.members.edit, data).then((res) => {
        let result = res.data
        console.log(result)
        if (result.code === 200) {
          this.membersInfo()
        }
        this.$message({
          type: 'success',
          message: '修改成功'
        })
        this.editFormVisible = false
      }).catch(err => {
        console.log(err)
      })
    },
    //搜索
    searchMumber() {
      console.log(this.input)
      this.$axios.get(this.$api.members.seaarch + '?limit=10&page=1&name=' + this.input).then(res => {
        let result = res.data
        console.log(result)
        if (result.code === 200) {
          this.tableData = result.data;
        }
      })
    },
    //下载
    handleSelectionChange(val) {
      console.log(111)
      console.log(val)
      this.multipleSelection = val;
      console.log(this.multipleSelection)

      let arr = this.multipleSelection.map(string => {
        return string.id
      })
      this.ids = arr.toString()
      // console.log(arr)
      // console.log(this.ids)
    },
    Download: function () {
      console.log(this.ids)
      window.open(this.$api.members.DownLoad + "?ids=" + this.ids, "_self", "excel0fAccount")
    },
    //会员模板下载
    Downloadmodul: function () {
      window.open(this.$api.members.DownLoadmodul, "_self", "excel0fAccount")
    },
    //上传
    // upload: function () {
    //   let uplond = this.$refs.upload
    //   // console.log(uplond)
    //   let form = new FormData(uplond)
    //   this.$axios.post(this.$api.members.upload, form, {
    //     headers: {
    //       'content-type': 'multipart/formdata'
    //     }
    //   }).then((res)=> {
    //     console.log(res)
    //     if (res.status === 200) {
    //       this.$message.success('上传成功')
    //       this.pdialogFormVisible  = false
    //       this.getDiningList()
    //     } else {
    //       this.$message.warning('上传失败')
    //     }
    //   })
    // },
    //分页
    handleCurrentChange(val) {
      this.currentPage = val;
      this.membersInfo();
    },
    handlePreview(file) {
      console.log(file);
    },
    uploadSuccess() {
      this.$message.success('上传成功');
      this.membersInfo();
    }
  },
  created() {
    this.membersInfo();
  },
  computed: {
    haveAuthToOperate() {
      return localStorage.getItem('loginRoleId')==='3' || localStorage.getItem('loginRoleId')==='2'
    },
    haveAuthToDownload() {
      return localStorage.getItem('loginRoleId')==='5' || localStorage.getItem('loginRoleId')==='2'
    }
  }
}


</script>

<style scoped>
@import "./../../../styles/commonStyles.css";
</style>
