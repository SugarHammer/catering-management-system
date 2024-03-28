<template>
  <div>
    <div id="sub">
      <el-row class="btnsBar" :gutter="15">
        <!--        添加-->
        <el-col :span="6">
          <div class="grid-content bg-purple">
            <el-button v-if="haveAuthToOperate" type="primary" size="small" plain @click="dialogFormVisible = true" class="addBtn">添加</el-button>
          </div>
        </el-col>
        <!--        搜索-->
        <el-col :span="6">
          <span class="search">
            <el-input v-model="input" size="small" placeholder="请输入菜品大类名"></el-input>
          </span>
        </el-col>
        <el-col :span="3">
          <el-button type="primary" size="small" class="searchBtn" @click="searchByName">搜索</el-button>
        </el-col>
      </el-row>
      <!--        弹出框-->
      <el-dialog title="添加菜品大类" :visible.sync="dialogFormVisible" :append-to-body="true" width="600px">
        <el-form :model="addData">
          <el-form-item label="菜品大类名" :label-width="formLabelWidth">
            <el-input size="small" v-model="addData.categoriesName" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button size="small" @click="dialogFormVisible = false">取 消</el-button>
          <el-button size="small" type="primary" @click="add">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <!--                修改弹出框-->
    <div>
      <el-dialog title="修改菜品大类" :visible.sync="dialogFormEditVisible" :append-to-body="true" width="600px">
        <el-form :model="editForm">
          <el-form-item label="菜品大类名" :label-width="formLabelWidth">
            <el-input size="small" v-model="editForm.categoriesName" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button size="small" @click="dialogFormEditVisible=false">取 消</el-button>
          <el-button size="small" type="primary" @click="save()">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <!--                 表格-->
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
          :cell-style="{ textAlign: 'center' }"
          style="width: 100%">
        <el-table-column type="selection">
        </el-table-column>
        <el-table-column
            prop="id"
            label="ID">
        </el-table-column>
        <el-table-column
            prop="categoriesName"
            label="菜品大类">
        </el-table-column>
        <el-table-column label="操作" width="400px" v-if="haveAuthToOperate">
          <template slot-scope="scope">
            <el-button type="success" id="editBtn" size="small" @click="edit(scope.row)">编辑</el-button>
            <el-button type="danger" id="delBtn" size="small" @click="del(scope.row.id)">删除</el-button>
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
  name: "categories",
  data() {
    return {
      currentPage: 1,
      total: 0,
      input: '',
      tableData: [],
      addData: {
        id: '',
        categoriesName: ''
      },
      editForm: {
        id: '',
        categoriesName: ''
      },
      multipleSelection: [],
      formLabelWidth: '120px',
      dialogFormVisible: false,
      dialogFormEditVisible: false
    }
  },
  created() {
    this.getList();
  },
  methods: {
    //数据渲染
    getList() {
      this.$axios.get(this.$api.dishes.categoriesSelectAll+"?limit=10&index=" + this.currentPage).then(res => {
        let result = res.data
        // console.log(result)
        if (result.code === 200) {
          this.tableData = result.data.reverse()
          this.total = result.count;
          // console.log(this.total)
        }
      })
    },
    //根据id删除
    del(id) {
      console.log(id);
      let data = {id: id}
      this.$confirm('请确认是否删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post(this.$api.dishes.categoriesDeleteById, this.$qs.stringify(data)).then(res => {
          let result = res.data
          if (result.code === 200) {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getList();
          }
        })
      })
    },
    // 添加
    add() {
      let data = {categoriesName: this.addData.categoriesName}
      this.$axios.post(this.$api.dishes.categoriesInsert, data).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.getList();
          this.addData = {}
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
    //编辑
    edit(row) {
      this.dialogFormEditVisible = true
      this.editForm.id = row.id
      this.editForm.categoriesName = row.categoriesName
    },
    //提交修改表单
    save() {
      let data = {id: this.editForm.id, categoriesName: this.editForm.categoriesName}
      this.$axios.post(this.$api.dishes.categoriesUpdateById, data).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.getList();
        }
        this.$message({
          type: 'success',
          message: '修改成功!'
        })
        this.dialogFormEditVisible = false;
      })
    },
    //通过菜单大类名搜索
    searchByName() {
      let data = {categoriesName: this.input}
      // console.log(data);
      this.$axios.get(this.$api.dishes.categoriesSelectAll+'?categoriesName=' + this.input + "&limit=10&index=" + this.currentPage).then(res => {
        let result = res.data
        // console.log(result);
        if (result.code === 200) {
          this.tableData = result.data;
          this.total = result.count
        }
      })
    },
    //当前页码
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getList();
    },
  },
  computed: {
    haveAuthToOperate() {
      return localStorage.getItem('loginRoleId')==='3' || localStorage.getItem('loginRoleId')==='2'
    }
  }
}
</script>

<style scoped>
@import './../../../styles/commonStyles.css';

.search {
  width: 200px;
}

.searchBtn {
  margin-left: 15px;
}
</style>
