
<template>
  <div>
    <!--    购物车整体菜品/套餐详情-->
    <div class="head">
      <div class="delAll" style="margin: 10px 0; font-size: 16px">- 我的订单 -</div>
    </div>
    <div v-if="hasOrder">
      <div class="container">
       <span style="margin-left: 20px">店内点单</span>
       <span style="margin-right: 20px">桌号：{{diningId}}</span>
      </div>
      <!--卡片菜品-->
        <van-card
               v-for="(item,index) in getCList"
               :key="index"
               :title="item.dishesName"
               :thumb=url+item.dishesPhoto
               :price="item.dishesCount"
                :num="item.dishesNumber"/>
      <div>

      </div>
    </div>
    <!--    总价、立即结算-->
    <div v-if="!hasOrder" style="font-size: 16px;margin-top: 10%">暂无订单，请先去点单</div>
    <div class="count" v-if="hasOrder"><span>总计:</span><span>￥{{totalprice}}</span></div>
    <div class="Foot" v-if="hasOrder" style="margin: 20px 0 40px">
      <el-button size="small" plain type="primary" @click="goPay">去支付</el-button>
    </div>
<div>

</div>
  </div>
</template>

<script>
  export default {
    name: "orders",
    data() {
      return {
        totalprice:0,
        getCList:[],
        url: "/api",
        diningId: '',
        hasOrder: false
      }
    },
    created() {
      let that = this;
      this.getCartsList();
      this.$axios.get('/api/dining/selectById?id=' + localStorage.getItem('diningId')).then(res=>{
        if (res.data.code===200) {
          that.diningId = res.data.data.diningType + res.data.data.diningNumber
        }
      })
    },
    methods: {
      //计算总价
      getTotalPrice: function () {
        //循环数组
        this.getCList.forEach((item, index) => {
          this.totalprice +=item.dishesCount ;
          console.log(item.dishesCount)
        })
      },
      //获取列表
      getCartsList(){
        if (localStorage.getItem('orders_no')!==null&&localStorage.getItem('orders_no')!=='') {
          this.hasOrder = true
          this.$axios.get('/api/MumberOrders/selectByMumberId?cartId=' + localStorage.getItem('cartId')).then((res)=>{
            let result = res.data
            console.log(result.data)
            if (result.code===200){
              this.getCList = result.data
              this.getTotalPrice();
            }else{
              this.$alert('出错')
            }
          }).catch((err)=>{
            this.$alert('请求出错')
            console.log(err)
          })
        }
      },
      // 去支付
      goPay(){
        window.open('/api/pay/payOnline?cartId=' + localStorage.getItem('cartId'), '_self')
      }
    }
  }

</script>

<style scoped>
  .container{
    overflow: hidden;
    margin-top: 10px;
    margin-bottom: 10px;
    height: 50px;
    line-height: 50px;
    font-size: 10px;
    background-color: #fafafa;
  }
.container span:nth-child(1){
  float: left;
  margin-left: 5px;
}
.container span:nth-child(2){
  float: right;
  margin-right: 5px;
}
  .van-card__price{
    float: left;
    color: red !important;
  }
  .van-card__title{
    float: left;
  }
  .count{
    background-color: #fafafa;
    height: 50px;
    line-height: 50px;
    /*width:100% ;*/
    margin-top: 10px;

  }
  .count span:nth-child(1){
    font-size: 12px;
  }
  .count span:nth-child(2){
    color: red;
  }

</style>
