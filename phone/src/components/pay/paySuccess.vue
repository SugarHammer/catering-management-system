<template>
  <div>
    <ourHeader></ourHeader>
    <div style="margin:30% 0;font-size: 16px">支付成功！<br>希望用餐愉快！<br>欢迎下次光临！</div>
    <el-button size="small" type="primary" @click="$router.push('/')">再次光临</el-button>
  </div>
</template>

<script>
import ourHeader from '../header/ourHeader'
export default {
  name: "paySuccess",
  created() {
    let orderInfo = {OrdersNo: localStorage.getItem('orders_no')}
    this.$axios.post('/api/orders/updateByOrdersNo', this.$qs.stringify(orderInfo)).then(res=> {
      console.log(res)
    })
    let action = {id: localStorage.getItem('diningId'), diningAction: 0}
    this.$axios.post(this.$api.tables.updateDiningAction, action).then(res => {
      console.log(res)
    })
    localStorage.clear();
  },
  components: {
    ourHeader
  }
}
</script>

<style scoped>

</style>
