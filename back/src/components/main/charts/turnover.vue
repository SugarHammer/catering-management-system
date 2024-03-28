<template>
  <div>
    <div class="sub">
      <el-row class="btnsBar" :gutter="15">
        <!--        下载按钮-->
        <el-col :span="6">
          <el-button v-if="haveAuthToDownload" size="small" plain @click="Download" class="addBtn">下载数据</el-button>
        </el-col>
        <!--        日期段选择-->
        <el-col :span="4">
          <el-date-picker
              size="small"
              v-model="values"
              type="daterange"
              range-separator="至"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
              value-format="yyyy-MM-dd">
          </el-date-picker>
        </el-col>
        <!--          搜索框-->
        <el-col :span="4" :offset="6">
          <div class="grid-content bg-purple">
            <el-input size="small" placeholder="请输入最低收入" v-model="input" clearable @clear="getTurnoverList"></el-input>
          </div>
        </el-col>
        <el-col :span="2">
          <el-button size="small" type="primary" @click="seacrchTurnover">搜索</el-button>
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
          style="width: 100%"
          @selection-change="handleSelectionChange">
        <el-table-column
            type="selection">
        </el-table-column>
        <el-table-column
            prop="id"
            label="订单ID"
            width="200">
        </el-table-column>
        <el-table-column
            prop="payDate"
            label="订单时间">
        </el-table-column>
        <el-table-column
            prop="totalPrice"
            label="收入">
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
  name: "turnover",
  data() {
    return {
      input: '',
      values: [],
      tableData: [],
      multipleSelection: [],
      ids: '',
      total: 0,
      currentPage: 1
    }
  },
  mounted() {
    this.getTurnoverList()
  },
  methods: {
    //获取数据列表
    getTurnoverList: function () {
      this.$axios.get(this.$api.report.getTurnoverList, {params: {page: this.currentPage}}).then((res) => {
        let result = res.data
        if (result.code === 200) {
          this.tableData = result.data
          this.total = res.data.count
          console.log(res.data)
        } else {
          this.$alert("获取列表失败")
        }
      }).catch(() => {
        this.$alert('请求出错')
      })
    },
    //查询
    seacrchTurnover: function () {
      let url = this.$api.report.getTurnoverList
      if (this.values.length === 0) {
        url = url + "?totalPrice=" + this.input + '&page=' + this.currentPage;
      } else {
        url = url + "?startTime=" + this.values[0] + "&endTime=" + this.values[1] + "&totalPrice=" + this.input + '&page=' + this.currentPage;
      }
      this.$axios.get(url).then((res) => {
        let result = res.data
        if (result.code === 200) {
          this.tableData = res.data.data
          this.total = res.data.count
        } else {
          this.$alert('出错')
        }
      }).catch(() => {
        this.$alert('查询出错')
      })
    },
    //下载
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(this.multipleSelection)
      //提取数组中的id
      let arr = this.multipleSelection.map(string => {
        return string.id
      })
      //数组转字符串
      this.ids = arr.join(',')
      // this.ids=arr.toString()
      console.log(arr)
      console.log(this.ids)
    },
    //下载为excel表
    Download: function () {
      window.open(this.$api.report.Download + '?ids=' + this.ids, "_self", "excelOfAccount")
    },
    //分页
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getTurnoverList();
    }
  },
  computed: {
    haveAuthToDownload() {
      return localStorage.getItem('loginRoleId')==='2'||localStorage.getItem('loginRoleId')==='5'
    }
  }
}
</script>

<style scoped>
@import "./../../../styles/commonStyles.css";
</style>
