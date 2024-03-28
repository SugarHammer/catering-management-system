<template>
  <div style="width: 100%">
    <div>
      <el-row v-if="haveAuthToOperate" class="btnsBar" :gutter="15">
        <!--        添加按钮-->
        <el-col :span="5">
          <div class="grid-content bg-purple">
            <el-button size="small" plain type="primary" @click="dialogFormVisible=true" class="addBtn">添加</el-button>
          </div>
        </el-col>
      </el-row>

      <!--       新增弹出框-->
      <el-dialog title="新增做法" :visible.sync="dialogFormVisible" :append-to-body="true" width="600px">
        <el-form :model="addData">
          <el-form-item label="编码" :label-width="formLabelWidth">
            <el-input size="small" v-model="addData.recipeNo" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="做法说明" :label-width="formLabelWidth">
            <el-input size="small" v-model="addData.recipeExplain" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="加价" :label-width="formLabelWidth">
            <el-input size="small" v-model="addData.recipeMarkup" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="类型" :label-width="formLabelWidth">
            <el-select size="small" v-model="addData.recipeType">
              <el-option v-for="item in recipeTypes" :label="item" :value="item"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="拼音" :label-width="formLabelWidth">
            <el-input size="small" v-model="addData.recipeShortCode" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button size="small" @click="dialogFormVisible = false">取 消</el-button>
          <el-button size="small" type="primary" @click="add">确 定</el-button>
        </div>
      </el-dialog>

      <div>
        <!--       修改 编辑 弹出框-->
        <el-dialog title="修改做法" :visible.sync="dialogFormEditVisible" :append-to-body="true" width="600px">
          <el-form :model="editData">
            <el-form-item label="编码" :label-width="formLabelWidth">
              <el-input size="small" v-model="editData.recipeNo" autocomplete="off" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="做法说明" :label-width="formLabelWidth">
              <el-input size="small" v-model="editData.recipeExplain" autocomplete="off" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="加价" :label-width="formLabelWidth">
              <el-input size="small" v-model="editData.recipeMarkup" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="类型" :label-width="formLabelWidth">
              <el-select size="small" v-model="editData.recipeType">
                <el-option v-for="item in recipeTypes" :label="item" :value="item"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="拼音" :label-width="formLabelWidth">
              <el-input size="small" v-model="editData.recipeShortCode" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button size="small" @click="dialogFormEditVisible = false">取 消</el-button>
            <el-button size="small" type="primary" @click="save">确 定</el-button>
          </div>
        </el-dialog>
      </div>
    </div>
    <!--   表格table-->
    <div class="tableBox">
      <el-table
          stripe
          ref="multipleTable"
          :data="tableData"
          tooltip-effect="dark"
          :header-cell-style="{
                 textAlign: 'center',
                 background: 'orange',
                 color:'white'}"
          :cell-style="{textAlign:'center'}"
          style="width: 1150px">
        <el-table-column type="selection">
        </el-table-column>
        <el-table-column
            prop="id"
            label="行号">
        </el-table-column>
        <el-table-column
            prop="recipeNo"
            label="编码">
        </el-table-column>
        <el-table-column
            prop="recipeExplain"
            label="做法说明">
        </el-table-column>
        <el-table-column
            prop="recipeMarkup"
            label="加价">
        </el-table-column>
        <el-table-column
            prop="recipeType"
            label="类型">
        </el-table-column>
        <el-table-column
            prop="recipeShortCode"
            label="拼音">
        </el-table-column>
        <el-table-column v-if="haveAuthToOperate" label="操作" width="220px">
          <template slot-scope="scope">
            <el-button type="success" size="small" id="editBtn" @click="edit(scope.row)">编辑</el-button>
            <el-button type="danger" size="small" id="delBtn" @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          background
          layout="prev, pager, next"
          @current-change="handleCurrentChange"
          :total="total"
          class="paginationBar">
      </el-pagination>
      <div class="clear"></div>
    </div>
  </div>

</template>

<script>
export default {
  name: "vegtableWay",
  props: {
    activeName: {
      type: String,
      default: '全部',
    },
    recipeTypes: Array
  },
  data() {
    return {
      currentPage: 1,
      total: 0,
      formLabelWidth: '120px',
      dialogFormVisible: false,
      dialogFormEditVisible: false,
      tableData: [],
      addData: {
        recipeNo: '',
        recipeExplain: '',
        recipeMarkup: '',
        recipeType: '',
        recipeShortCode: ''
      },
      editData: {
        id: '',
        recipeNo: '',
        recipeExplain: '',
        recipeMarkup: '',
        recipeType: '',
        recipeShortCode: ''
      }
    }
  },
  created() {
    this.getVegListAll()
  },
  methods: {
    getVegListAll() {
      this.$axios.get(this.$api.dishes.recipeSelectAll+"?limit=10&index=" + this.currentPage).then(res => {
        let result = res.data
        console.log(result)
        if (result.code === 200) {
          this.tableData = result.data
          this.total = result.count
        }
      }).catch(err => {
        console.log(err);
      })
      this.addData.recipeType = this.activeName
    },
    getVegList() {
      this.$axios.get(this.$api.dishes.recipeSelectAll+"?recipeType=" + this.activeName + "&limit=10&index=" + this.currentPage).then(res => {
        let result = res.data
        console.log(result)
        if (result.code === 200) {
          this.tableData = result.data
          this.total = result.count
        }
      }).catch(err => {
        console.log(err);
      })
      this.addData.recipeType = this.activeName
    },
    // 添加
    add() {
      let data = {
        recipeType: this.addData.recipeType,
        recipeNo: this.addData.recipeNo,
        recipeExplain: this.addData.recipeExplain,
        recipeMarkup: this.addData.recipeMarkup,
        recipeShortCode: this.addData.recipeShortCode,
      }
      this.$axios.post(this.$api.dishes.recipeInsert, data).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.addData = {};
          if (this.activeName == '全部') {
            this.disabled = false
            this.getVegListAll()
          } else {
            this.getVegList()
          }

        }
        this.$message({
          type: 'success',
          message: '添加成功!'
        })
        this.dialogFormVisible = false;
      }).catch(err => {
        console.log(err);
      })
    },
    //点击编辑按钮
    edit(row) {
      this.dialogFormEditVisible = true
      this.editData.id = row.id
      this.editData.recipeNo = row.recipeNo
      this.editData.recipeExplain = row.recipeExplain
      this.editData.recipeType = row.recipeType
      this.editData.recipeMarkup = row.recipeMarkup
      this.editData.recipeShortCode = row.recipeShortCode
    },
    //保存修改数据
    save() {
      let data = {
        id: this.editData.id,
        recipeType: this.editData.recipeType,
        recipeMarkup: this.editData.recipeMarkup,
        recipeShortCode: this.editData.recipeShortCode,
      }
      console.log(data);
      this.$axios.post(this.$api.dishes.recipeUpdateById, data).then(res => {
        let result = res.data
        if (result.code === 200) {
          if (this.activeName == '全部') {
            this.disabled = false
            this.getVegListAll()
          } else {
            this.disabled = true
            this.getVegList()
          }
        }
        this.$message({
          type: 'success',
          message: '修改成功!'
        })
        this.dialogFormEditVisible = false;
      }).catch(err => {
        console.log(err);
      })
    },
    //根据id删除
    del(id) {
      console.log(id);
      this.$confirm('请确认是否删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let data = {id: id}
        this.$axios.post(this.$api.dishes.recipeDeleteById, this.$qs.stringify(data)).then(res => {
          let result = res.data
          if (result.code === 200) {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            if (this.activeName == '全部') {
              this.getVegListAll()
            } else {
              this.getVegList()
            }
          }
        })
      })
    },
    //当前页码
    handleCurrentChange(val) {
      this.currentPage = val;
      if (this.activeName == '全部') {
        this.getVegListAll();
      } else {
        this.getVegList();
      }
    },
  },
  watch: {
    activeName(val) {
      this.getVegList()
      if (this.activeName == '全部') {
        this.getVegListAll()
      }
    },
    deep: true,
  },
  computed: {
    haveAuthToOperate() {
      return localStorage.getItem('loginRoleId')==='3' || localStorage.getItem('loginRoleId')==='2'
    }
  }
}
</script>

<style scoped>

</style>
