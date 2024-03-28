<template>
  <div>
    <!--        做法详情弹出框-->
    <!-- Table Top -->
    <el-dialog title="做法" :visible.sync="dialogWayVisible" :append-to-body="true" width="55%">
      <el-table :data="wayInData" :cell-style="{textAlign:'center'}" :header-cell-style="{
                 textAlign: 'center'}">
        <el-table-column property="id" label="行号"></el-table-column>
        <el-table-column property="recipeExplain" label="做法说明"></el-table-column>
        <el-table-column property="recipeMarkup" label="加价"></el-table-column>
        <el-table-column v-if="haveAuthToOperate" label="操作" width="200px">
          <template slot-scope="scope">
            <el-button type="danger" size="small" id="delWayBtn" @click="delWayBtn(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- Table Bottom -->
      <el-table :data="wayNotInData" :cell-style="{textAlign:'center'}" :header-cell-style="{
                 textAlign: 'center'}" :show-header="false">
        <el-table-column property="id" label="行号"></el-table-column>
        <el-table-column property="recipeExplain" label="做法说明"></el-table-column>
        <el-table-column property="recipeMarkup" label="加价"></el-table-column>
        <el-table-column v-if="haveAuthToOperate" label="操作" width="200px">
          <template slot-scope="scope">
            <el-button type="success" size="small" id="editWayBtn" @click="saveWayBtn(scope.row.id)">添加</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>

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
          style="width: 100%;">
        <el-table-column type="selection">
        </el-table-column>
        <el-table-column
            prop="id"
            label="行号">
        </el-table-column>
        <el-table-column
            prop="dishesNo"
            label="品码">
        </el-table-column>
        <el-table-column
            prop="dishesName"
            label="品名">
        </el-table-column>
        <el-table-column
            prop="dishesShortCode"
            label="拼音简码">
        </el-table-column>
        <el-table-column
            prop="subcategoriesName"
            label="所属小类">
        </el-table-column>
        <el-table-column
            prop="categoriesName"
            label="所属大类">
        </el-table-column>
        <el-table-column label="操作" width="220px">
          <template slot-scope="scope">
            <el-button v-if="haveAuthToOperate" type="success" size="small" id="editBtn" @click="chooseWay(scope.row.id)">选择做法</el-button>
            <el-button v-else type="success" size="small" class="editBtn" @click="chooseWay(scope.row.id)">查看做法</el-button>
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
  name: "relevanceWay",
  data() {
    return {
      currentPage: 1,
      total: 0,
      tableData: [],
      dialogWayVisible: false,
      formLabelWidth: '120px',
      wayInData: [],
      wayNotInData: [],
      dishesId: '',
    }
  },
  created() {
    this.getList();
    this.getVegListAll();
  },
  methods: {
    //数据渲染
    getList() {
      this.$axios.get(this.$api.dishes.dishesSelectAll+"?limit=10&index=" + this.currentPage).then(res => {
        let result = res.data
        // console.log(result)
        if (result.code === 200) {
          this.tableData = result.data
          this.total = result.count
        }
      }).catch(err => {
        console.log(err);
      })
    },
    chooseWay(id) {
      this.dishesId = id
      this.dialogWayVisible = true
      this.getVegListAll();
    },
    //根据Id获取所有做法
    getVegListAll() {
      this.$axios.get(this.$api.dishes.recipeSelectByDishesId+"?id=" + this.dishesId).then(res => {
        let result = res.data
        console.log(result)
        if (result.code === 200) {
          this.wayInData = result.data
          this.wayNotInData = result.dataNot
        }
      }).catch(err => {
        console.log(err);
      })
    },
    //点击确定添加做法
    saveWayBtn(id) {
      let data = {
        dishesId: this.dishesId,
        recipeId: id
      }
      console.log(data)
      this.$axios.post(this.$api.dishes.dishesRecipeInsert, data).then(res => {
        let result = res.data
        console.log(result)
        if (result.code === 200) {
          this.getVegListAll();
        }
        this.$message({
          type: 'success',
          message: '添加成功!'
        })
        this.dialogFormEditVisible = false;
      }).catch(err => {
        console.log(err);
      })
    },
    //根据id删除
    delWayBtn(id) {
      this.$confirm('请确认是否删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let data = {
          dishesId: this.dishesId,
          recipeId: id
        }
        this.$axios.post(this.$api.dishes.dishesRecipeDeleteByDishesIdAndRecipeId, this.$qs.stringify(data)).then(res => {
          let result = res.data
          if (result.code === 200) {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getVegListAll();
          }
        })
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
