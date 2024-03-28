<template>
  <div>
    <div>
      <el-row class="btnsBar" :gutter="15">
        <!--        添加按钮-->
        <el-col :span="6">
          <div class="grid-content bg-purple">
            <el-button v-if="haveAuthToOperate" type="primary" plain size="small" @click="dialogFormVisible=true" class="addBtn">添加</el-button>
          </div>
        </el-col>
        <!--            搜索-->
        <el-col :span="4">
          <el-input v-model="input1" size="small" placeholder="请输入菜品单位名称"></el-input>
        </el-col>
        <el-col :span="4">
          <el-select size="small" v-model="input2" placeholder="请选择销售类型">
            <el-option label="全部" value="2"></el-option>
            <el-option label="整数" value="0"></el-option>
            <el-option label="小数" value="1"></el-option>
          </el-select>
        </el-col>
        <el-col :span="4">
          <el-input size="small" v-model="input3" placeholder="请输入点菜默认量"></el-input>
        </el-col>
        <el-col :span="3">
          <el-button size="small" type="primary" class="searchBtn" @click="search">搜索</el-button>
        </el-col>
      </el-row>

      <!--       新增弹出框-->
      <el-dialog title="添加菜品单位" :visible.sync="dialogFormVisible" :append-to-body="true" width="600px">
        <el-form :model="addData">
          <el-form-item label="菜品单位" :label-width="formLabelWidth">
            <el-input size="small" v-model="addData.unitName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="销售类型" :label-width="formLabelWidth">
            <el-select size="small" v-model="addData.unitType">
              <el-option label="整数" value="0"></el-option>
              <el-option label="小数" value="1"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="点菜默认量" :label-width="formLabelWidth">
            <el-input size="small" v-model="addData.unitDefaultAmount" autocomplete="off" placeholder="请输入整数"></el-input>
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
      <el-dialog title="修改菜品单位" :visible.sync="dialogFormEditVisible" :append-to-body="true" width="600px">
        <el-form :model="editData">
          <el-form-item label="菜品单位" :label-width="formLabelWidth">
            <el-input size="small" v-model="editData.unitName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="销售类型" :label-width="formLabelWidth">
            <el-select size="small" v-model="editData.unitType">
              <el-option label="整数" value="0"></el-option>
              <el-option label="小数" value="1"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="点菜默认量" :label-width="formLabelWidth">
            <el-input size="small" v-model="editData.unitDefaultAmount" autocomplete="off" placeholder="请输入整数"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button size="small" @click="dialogFormEditVisible = false">取 消</el-button>
          <el-button size="small" type="primary" @click="save">确 定</el-button>
        </div>
      </el-dialog>
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
          :cell-style="{ textAlign: 'center' }"
          style="width: 100%">
        <el-table-column type="selection">
        </el-table-column>
        <el-table-column
            prop="id"
            label="ID">
        </el-table-column>
        <el-table-column
            prop="unitName"
            label="菜品单位">
        </el-table-column>
        <el-table-column
            prop="unitType"
            label="销售类型">
        </el-table-column>
        <el-table-column
            prop="unitDefaultAmount"
            label="点菜默认量">
        </el-table-column>
        <el-table-column v-if="haveAuthToOperate" label="操作" width="400px">
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
  name: "unit",
  data() {
    return {
      currentPage: 1,
      total: 0,
      input1: '',
      input2: '',
      input3: '',
      formLabelWidth: '120px',
      dialogFormVisible: false,
      dialogFormEditVisible: false,
      tableData: [],
      addData: {
        unitName: '',
        unitType: '',
        unitDefaultAmount: ''
      },
      editData: {
        id: '',
        unitName: '',
        unitType: '',
        unitDefaultAmount: ''
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    //渲染数据
    getList() {
      this.$axios.get(this.$api.dishes.unitSelectAll+"?limit=10&index=" + this.currentPage).then(res => {
        let result = res.data
        console.log(result)
        if (result.code === 200) {
          for (var i = 0; i < result.data.length; i++) {
            (result.data[i].unitType === 0) ? result.data[i].unitType = "整数" : result.data[i].unitType = "小数"
          }
          this.tableData = result.data
          this.total = result.count
        }
      })
    },
    // 添加
    add() {
      let data = {
        unitName: this.addData.unitName,
        unitType: this.addData.unitType,
        unitDefaultAmount: this.addData.unitDefaultAmount,
      }
      console.log(data);
      this.$axios.post(this.$api.dishes.unitInsert, data).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.getList();
          this.addData = {};
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
    //根据id删除
    del(id) {
      console.log(id);
      this.$confirm('请确认是否删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let data = {id: id}
        this.$axios.post(this.$api.dishes.unitDeleteById, this.$qs.stringify(data)).then(res => {
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
    //点击编辑按钮
    edit(row) {
      this.dialogFormEditVisible = true
      this.editData.id = row.id
      this.editData.unitName = row.unitName
      this.editData.unitType = row.unitType
      this.editData.unitDefaultAmount = row.unitDefaultAmount
    },
    //保存修改数据
    save() {
      let data = {
        id: this.editData.id,
        unitName: this.editData.unitName,
        unitType: this.editData.unitType,
        unitDefaultAmount: this.editData.unitDefaultAmount,
      }
      console.log(data);
      this.$axios.post(this.$api.dishes.unitUpdateById, data).then(res => {
        let result = res.data
        console.log(result)
        if (result.code === 200) {
          this.getList();
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
    //搜索
    search() {
      console.log('unitName=' + this.input1 + '&&unitType=' + this.input2 + '&unitDefaultAmount=' + this.input3)
      this.$axios.get(this.$api.dishes.unitSelectAll+'?unitName=' + this.input1 +
          '&unitType=' + this.input2 +
          '&unitDefaultAmount=' + this.input3 + "&limit=10&index=" + this.currentPage).then(res => {
        let result = res.data
        console.log(result)
        if (result.code === 200) {
          for (var i = 0; i < result.data.length; i++) {
            (result.data[i].unitType === 0) ? result.data[i].unitType = "整数" : result.data[i].unitType = "小数"
          }
          this.tableData = result.data;
          this.total = result.count
        }
      }).catch(err => {
        console.log(err);
      })
    },
    //当前页码
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getList();
    }
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
