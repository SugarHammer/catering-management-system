<template>
  <div>
    <div id="sub">
      <el-row class="btnsBar" :gutter="15">
        <!--        添加-->
        <el-col :span="6">
          <div class="grid-content bg-purple">
            <el-button v-if="haveAuthToOperate" size="small" plain type="primary" @click="dialogFormVisible = true" class="addBtn">添加</el-button>
          </div>
        </el-col>
        <!--        搜索-->
        <el-col :span="3">
          <span class="search">
             <el-input class="searchInput" size="small" v-model="inputNo" placeholder="请输入编号"></el-input>
          </span>
        </el-col>
        <el-col :span="3">
          <el-input class="searchInput" size="small" v-model="inputSubName" placeholder="请输入菜品小类"></el-input>
        </el-col>
        <el-col :span="3">
          <el-input class="searchInput" size="small" v-model="inputCateName" placeholder="请输入所属大类"></el-input>
        </el-col>
        <el-col :span="3">
          <el-input class="searchInput" size="small" v-model="inputColor" placeholder="请输入菜品颜色"></el-input>
        </el-col>
        <el-col :span="3">
          <el-input class="searchInput" size="small" v-model="inputOrder" placeholder="请输入上菜顺序"></el-input>
        </el-col>
        <el-col :span="2">
          <el-button size="small" type="primary" class="searchBtn" @click="search">搜索</el-button>
        </el-col>
      </el-row>
      <!--       新增弹出框-->
      <el-dialog title="添加菜品小类" :visible.sync="dialogFormVisible" :append-to-body="true" width="600px">
        <el-form :model="addData">
          <el-form-item label="编号" :label-width="formLabelWidth">
            <el-input size="small" v-model="addData.subcategoriesNo" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="菜品小类" :label-width="formLabelWidth">
            <el-input size="small" v-model="addData.subcategoriesName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="所属大类" :label-width="formLabelWidth">
            <el-select size="small" v-model="bigValue" placeholder="请选择所属大类">
              <el-option v-for="item in categoriesData" :label="item.categoriesName"
                         :value="item.categoriesName"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="菜品颜色" :label-width="formLabelWidth">
            <el-select size="small" v-model="addData.subcategoriesColor" placeholder="请选择颜色">
              <el-option label="颜色1" value="颜色1"></el-option>
              <el-option label="颜色2" value="颜色2"></el-option>
              <el-option label="颜色3" value="颜色3"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="上菜顺序" :label-width="formLabelWidth">
            <el-select size="small" v-model="addData.subcategoriesOrder" placeholder="请选择活动区域">
              <el-option label="1" value="1"></el-option>
              <el-option label="2" value="2"></el-option>
              <el-option label="3" value="3"></el-option>
              <el-option label="4" value="4"></el-option>
              <el-option label="5" value="5"></el-option>
            </el-select>
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
      <el-dialog title="修改菜品小类" :visible.sync="dialogFormEditVisible" :append-to-body="true" width="600px">
        <el-form :model="editData">
          <el-form-item label="编号" :label-width="formLabelWidth">
            <el-input size="small" v-model="editData.subcategoriesNo" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="菜品小类" :label-width="formLabelWidth">
            <el-input size="small" v-model="editData.subcategoriesName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="所属大类" :label-width="formLabelWidth">
            <el-select size="small" v-model="bigValue" placeholder="请选择所属大类">
              <el-option v-for="item in categoriesData" :label="item.categoriesName"
                         :value="item.categoriesName"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="菜品颜色" :label-width="formLabelWidth">
            <el-select size="small" v-model="editData.subcategoriesColor" placeholder="请选择颜色">
              <el-option label="颜色1" value="颜色1"></el-option>
              <el-option label="颜色2" value="颜色2"></el-option>
              <el-option label="颜色3" value="颜色3"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="上菜顺序" :label-width="formLabelWidth">
            <el-select size="small" v-model="editData.subcategoriesOrder" placeholder="请选择活动区域">
              <el-option label="1" value="1"></el-option>
              <el-option label="2" value="2"></el-option>
              <el-option label="3" value="3"></el-option>
              <el-option label="4" value="4"></el-option>
              <el-option label="5" value="5"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button size="small" @click="dialogFormEditVisible = false">取 消</el-button>
          <el-button size="small" type="primary" @click="save()">确 定</el-button>
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
            prop="subcategoriesNo"
            label="编码">
        </el-table-column>
        <el-table-column
            prop="subcategoriesName"
            label="菜品小类">
        </el-table-column>
        <el-table-column
            prop="categoriesName"
            label="所属大类">
        </el-table-column>
        <el-table-column
            prop="subcategoriesColor"
            label="菜品颜色">
        </el-table-column>
        <el-table-column
            prop="subcategoriesOrder"
            label="上菜顺序">
        </el-table-column>
        <el-table-column v-if="haveAuthToOperate" label="操作" width="400px">
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
  name: "subcategories",
  data() {
    return {
      bigValue: '',
      currentPage: 1,
      total: 0,
      tableData: [],
      categoriesData: [],
      inputNo: '',
      inputSubName: '',
      inputCateName: '',
      inputColor: '',
      inputOrder: '',
      addData: {
        id: '',
        subcategoriesNo: '',
        subcategoriesName: '',
        subcategoriesColor: '',
        subcategoriesOrder: '',
        categoriesName: ''
      },
      editData: {
        id: '',
        subcategoriesNo: '',
        subcategoriesName: '',
        subcategoriesColor: '',
        subcategoriesOrder: '',
        categoriesName: ''
      },
      multipleSelection: [],
      formLabelWidth: '120px',
      dialogFormVisible: false,
      dialogFormEditVisible: false
    }
  },
  created() {
    this.getList()
    this.getCateList()
  },
  methods: {
    //数据渲染
    getList() {
      this.$axios.get(this.$api.dishes.subcategoriesSelectAll+"?limit=10&index=" + this.currentPage).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.tableData = result.data
          this.total = result.count
        }
      })
    },

    //调取大类接口，获取大类数据
    getCateList() {
      this.$axios.get(this.$api.dishes.categoriesSelectAll+"?limit=10&index=" + this.currentPage).then(res => {
        let result = res.data
        console.log(result)
        if (result.code === 200) {
          this.categoriesData = result.data
          console.log(this.categoriesData)
        }
      })
    },
    // 添加
    add() {
      let data = {
        subcategoriesNo: this.addData.subcategoriesNo,
        subcategoriesName: this.addData.subcategoriesName,
        categoriesName: this.bigValue,
        subcategoriesColor: this.addData.subcategoriesColor,
        subcategoriesOrder: this.addData.subcategoriesOrder
      }
      console.log(data);
      this.$axios.post(this.$api.dishes.subcategoriesInsert, data).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.getList();
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
        this.$axios.post(this.$api.dishes.subcategoriesDeleteById, this.$qs.stringify(data)).then(res => {
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
    //编辑
    edit(row) {
      this.dialogFormEditVisible = true
      this.editData.id = row.id
      this.editData.subcategoriesNo = row.subcategoriesNo
      this.editData.subcategoriesName = row.subcategoriesName
      this.editData.subcategoriesColor = row.subcategoriesColor
      this.editData.subcategoriesOrder = row.subcategoriesOrder
      this.bigValue = row.categoriesName
    },
    //保存修改数据
    save() {
      let data = {
        id: this.editData.id,
        subcategoriesNo: this.editData.subcategoriesNo,
        subcategoriesName: this.editData.subcategoriesName,
        subcategoriesColor: this.editData.subcategoriesColor,
        subcategoriesOrder: this.editData.subcategoriesOrder,
        categoriesName: this.bigValue
      }
      console.log(data);
      this.$axios.post(this.$api.dishes.subcategoriesUpdateById, data).then(res => {
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
    //联合搜索
    search() {
      let data = {
        subcategoriesNo: this.inputNo,
        subcategoriesName: this.inputSubName,
        categoriesName: this.inputCateName,
        subcategoriesColor: this.inputColor,
        subcategoriesOrder: this.inputOrder
      }
      this.$axios.get(this.$api.dishes.subcategoriesSelectAll+'?subcategoriesNo='
          + this.inputNo + '&subcategoriesName=' + this.inputSubName + '&categoriesName=' + this.inputCateName +
          '&subcategoriesColor=' + this.inputColor + '&subcategoriesOrder=' + this.inputOrder + "&limit=10&index=" + this.currentPage
      ).then(res => {
        let result = res.data
        console.log(result)
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
@import './../../../styles/commonStyles.css';
</style>
