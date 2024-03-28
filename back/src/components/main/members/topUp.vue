<template>
  <div>
    <!--搜索新增-->
    <div class="sub">
      <div>
        <el-row class="btnsBar" :gutter="15">
          <el-col :span="4">
            <el-input size="small" placeholder="请输入内容" v-model="input" class="input-with-select"></el-input>
          </el-col>
          <el-col :span="2">
            <el-button size="small" type="primary" @click="searchMumber">搜索</el-button>
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
          :cell-style="{ textAlign: 'center' }"
          :data="tableData"
          style="width: 100%">
        <el-table-column
            label="充值记录"
            prop="id">
        </el-table-column>
        <el-table-column
            label="会员id"
            prop="mumberId">
        </el-table-column>
        <el-table-column
            label="会员姓名"
            prop="mumber.mumberName">
        </el-table-column>
        <el-table-column
            label="用户订单号"
            prop="mumberRechargeOrderNo">
        </el-table-column>
        <el-table-column
            label="会员充值金额"
            prop="mumberRechargeMoney">
        </el-table-column>
        <el-table-column
            label="会员充值时间"
            prop="mumberRechargeTime">
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
  </div>
</template>

<script>
export default {
  name: "topUp",
  data() {
    return {
      input: '',
      dialogFormVisible: false,
      total: 0,
      tableData: [],
      currentPage: 1,
      addData: {
        mumberName: '',
        mumberId: '',
        mumberRechargeMoney: '',
      },
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
    topUp() {
      this.$axios.get(this.$api.members.topUp + '?limit=10&page=' + this.currentPage).then((res) => {
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
    //搜索
    searchMumber() {
      console.log(this.input)
      this.$axios.get(this.$api.members.seaarch2 + '?limit=10&page=1&name=' + this.input).then(res => {
        let result = res.data
        console.log(result)
        if (result.code === 200) {
          this.tableData = result.data;
        }
      })
    },
    //分页
    handleCurrentChange(val) {
      this.currentPage = val;
      this.membersInfo();
    },
  },
  created() {
    this.topUp()
  }
}
</script>

<style scoped>
@import "./../../../styles/commonStyles.css";
</style>
