<template>
  <div>
    <div>
      <el-row class="btnsBar" :gutter="15">
        <el-col :span="10">
          <div class="y addBtn">
            <span class="bt">请选择日期形式:</span>
            <el-radio-group size="small" @change="getTurnoverList" v-model="signal">
              <el-radio-button label="1">日</el-radio-button>
              <el-radio-button label="2">月</el-radio-button>
              <el-radio-button label="3">年</el-radio-button>
            </el-radio-group>
          </div>
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
            prop="bywhen"
            :label=date>
        </el-table-column>
        <el-table-column
            prop="ordersNum"
            label="订单量">
        </el-table-column>
        <el-table-column
            prop="ordersSum"
            label="营业额">
        </el-table-column>
      </el-table>
      <!--    分页-->
      <el-pagination
          background
          layout="prev, pager, next"
          @current-change="handleCurrentChange"
          :total=total
          class="paginationBar">
      </el-pagination>
      <div class="clear"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "turnover",
  data() {
    return {
      total: 0,
      currentPage: 1,
      date: "日期",
      input: '',
      tableData: [],
      //日月年传参 1-2-3
      signal: 1
    }
  },
  created() {
    this.getTurnoverList()
  },
  methods: {
    //分页
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getTurnoverList()
    },
    getTurnoverList: function () {
      this.$axios.get(this.$api.report.businessSearch + "?page=" + this.currentPage + "&signal=" + this.signal).then((res) => {
        let result = res.data
        if (result.code === 200) {
          this.tableData = result.data
          this.total = res.data.count;
          console.log(result.data)
        } else {
          this.$alert(result.message)
        }
      }).catch(() => {
        this.$alert('请求出错')
      })
    },
  },

}

</script>
<style scoped>
@import "./../../../styles/commonStyles.css";
.bt {
  color: dimgrey;
  margin-right: 20px;
  font-size: 14px;
}
</style>
