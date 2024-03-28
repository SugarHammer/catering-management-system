<template>
  <div>
    <div>
      <ourHeader></ourHeader>
    </div>
    <div class="selectTables">
      <div class="selectTitle">
        就座餐桌
      </div>
      <el-select size="small" v-model="tablesValue" placeholder="请选择">
        <el-option
            v-for="item in tablesOption"
            :key="item.id"
            :label="item.diningType+item.diningNumber"
            :value="item.id">
        </el-option>
      </el-select>
      <div>
        <el-button type="primary" size="small" @click="sureBtn" class="sure">确定用餐</el-button>
      </div>
    </div>
  </div>
</template>
<script>
import ourHeader from '../header/ourHeader'

export default {
  data() {
    return {
      tablesValue: '',
      tablesOption: []
    }
  },
  components: {
    ourHeader
  },
  created() {
    this.getTables();
  },
  methods: {
    getTables() {
      this.$axios.get(this.$api.tables.getTables + '?diningAction=0').then(res => {
        console.log(res.data);
        this.tablesOption = res.data.data;
        console.log(this.tablesOption);
      })
    },
    sureBtn() {
      if (this.tablesValue == '') {
        this.$message({
          showClose: true,
          message: '请先选择餐桌哦！',
          type: 'warning'
        });
        this.$router.push('/tables')
      } else {
        let data = {id: this.tablesValue}
        this.$axios.post(this.$api.tables.sentDiningId, this.$qs.stringify(data)).then(res => {
          let result = res.data;
          console.log(result);
        })
        let action = {id: this.tablesValue, diningAction: 1}
        // console.log(action);
        this.$axios.post(this.$api.tables.updateDiningAction, action).then(res => {
          console.log(res)
        })
        let cart = {
          cartAction: 0,
          cartNo: 1,
          diningId: this.tablesValue,
          id: 1,
          mumberId: 0
        }
        // 根据餐桌id新增一个购物车
        this.$axios.post('/api/cart/selectByDiningIdAndAction', this.$qs.stringify(cart)).then(res=> {
          if (res.data.code === 200) {
            localStorage.setItem("cartId", res.data.data.id)
          }
        })
        // 页面跳转，储存信息
        this.$router.push('/dishes');
        localStorage.setItem("diningId", JSON.stringify(this.tablesValue))
      }
    }
  },
}
</script>
<style scoped>
.selectTitle {
  font-size: 14px;
  margin-right: 10px;
  display: inline;
}

.selectTables {
  margin-top: 40%;
}

.sure {
  margin-top: 20%;
}
</style>
