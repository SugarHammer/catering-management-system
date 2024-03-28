<template>
  <div>
    <div class="sub">
      <el-row class="btnsBar" :gutter="15">
        <el-col :span="6">
          <div class="grid-content bg-purple">
            <el-input size="small" placeholder="请输入关键字搜索" v-model="input" clearable @clear="getDishesList"></el-input>
          </div>
        </el-col>
        <el-col :span="2">
          <el-button size="small" type="primary" @click="DishSales">搜索</el-button>
        </el-col>
      </el-row>
    </div>
    <!--    表格-->
    <div class="tableBox">
      <el-table
          :header-cell-style="{textAlign:'center',
            background:'orange',color:'white'}"
          :cell-style="{textAlign: 'center'}"
          :data="tableData"
          stripe
          style="width: 100%">
        <el-table-column
            type="selection">
        </el-table-column>
        <el-table-column
            prop="id"
            label="ID"
            width="200">
        </el-table-column>
        <el-table-column
            prop="dishesNameBat"
            label="菜品名称">
        </el-table-column>
        <el-table-column
            prop="dishesincombo"
            label="套菜卖出量">
        </el-table-column>
        <el-table-column
            prop="dishesindishes"
            label="单品卖出量">
        </el-table-column>
        <el-table-column
            prop="soldNum"
            label="销售总数量">
        </el-table-column>
        <el-table-column
            prop="soldSum"
            label="销售总额">
        </el-table-column>
      </el-table>
      <!--    分页-->
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
  name: "dishesSales",
  data() {
    return {
      input: '',
      tableData: [],
      total: 0,
      currentPage: 1
    }
  },
  mounted() {
    this.getDishesList();
  },
  methods: {
    //获取数据列表
    getDishesList: function () {
      this.$axios.get(this.$api.report.dishesSales + '?page=' + this.currentPage, {params: {page: this.currentPage}}).then((res) => {
        let result = res.data
        if (result.code === 200) {
          this.tableData = res.data.data
          this.total = res.data.count
        } else {
          this.$alert('获取列表出错啦')
        }
      }).catch(() => {
        this.$alert('请求出错')
      })
    },
    //分页
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getDishesList()
    },
    //查询
    DishSales: function () {
      this.$axios.get(this.$api.report.dishesSales + '?dishesName=' + this.input + '&page=' + this.currentPage).then((res) => {
        let result = res.data
        console.log(this.input)
        if (result.code === 200) {
          this.tableData = res.data.data
          this.total = res.data.count
        } else {
          this.$alert('出错')
        }
      }).catch(() => {
        this.$alert('查询出错')
      })
    }
  }
}
</script>

<style scoped>
@import "./../../../styles/commonStyles.css";
</style>
