/**
* 前台购物车
*
* @author ChenPing
* @since 2021-09-19
* @version 1.0
*/
<template>
  <div>
    <!--    购物车整体菜品/套餐详情-->
    <div class="head">
      <div style="margin: 10px 0; font-size: 16px">- 我的购物车 -</div>
      <div class="delAll">
        <span @click="delAll" class="DelAll" style="font-size: 14px">
          <i class="el-icon-delete"></i>一键清空
        </span>
      </div>
    </div>
    <div class="container">
      <div class="title" style="font-size: 16px">菜品区</div>
      <div v-if='DishesList.length>0'>
        <!--      详细每条菜品信息-->
        <div class="item"
             v-for="(item,index) in DishesList"
             :key="index">
          <div class="details">
            <img :src="item.dishesPhoto">
            <div class="details-list">
                  <span class="detname">
                      {{ item.dishesName }}
                  </span>
              <div class="list-price">
                <span class="detprice">￥{{ item.dishesPrice }}/份</span>
                <div class="num">
                  <button class="del" @click='btn1(false,index)'>-</button>
                  <!--                  cartList.dishesNumber-->
                  <span class="number">{{ item.dishesNumber }}</span>
                  <button class="add" @click='btn1(true,index)'>+</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div v-else style="font-size: 16px">菜品购物车为空</div>
      <div class="title" style="font-size: 16px">套餐区</div>
      <div v-if='ComboList.length>0'>
        <!--      详细每条套餐信息-->
        <div class="item"
             v-for="(item,index) in ComboList"
             :key="index">
          <div class="details">
            <img :src="item.comboPhoto" alt="">
            <div class="details-list">
                  <span class="detname">
                      {{ item.comboName }}
                  </span>
              <div class="list-price">
                <span class="detprice">￥{{ item.comboPrice }}/份</span>
                <div class="num">
                  <button class="del" @click='btn2(false,index)'>-</button>
                  <!--                  cartList.dishesNumber-->
                  <span class="number">{{ item.comboNumber }}</span>
                  <button class="add" @click='btn2(true,index)'>+</button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div v-else style="font-size: 16px">套餐购物车为空</div>
    </div>
    <!--    总价、立即结算-->
    <div class="Foot">
      <div class="foot-left" style="margin-left: 10px">
        总计：￥
        <span class="totalprice">{{ totalprice }}</span>
      </div>
      <div class="foot-right">
        <el-button style="margin-right: 20px" size="small" plain type="primary" @click="sendorder">去下单</el-button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "orders",
  data() {
    return {
      totalprice: 0,
      DishesList: [],
      ComboList: []
    }
  },
  created() {
    this.getCartsList();
  },
  methods: {
    //计算总价
    getTotalPrice: function () {
      //循环数组
      let dishTotalprice = 0;
      let comboTotalprice = 0;
      this.DishesList.forEach((item, index) => {
        dishTotalprice += item.dishesPrice * item.dishesNumber;
      })
      this.ComboList.forEach((item, index) => {
        comboTotalprice += item.comboPrice * item.comboNumber;
      })
      this.totalprice = (dishTotalprice + comboTotalprice).toFixed(2);
    },

    //菜品数量的加减
    btn1(bool, index) {
      let dishIndex = this.DishesList[index];
      if (bool) {
        //菜品加
        dishIndex.dishesNumber++;
        let dishdata = {
          cartId: 33,
          dishesId: dishIndex.id,
          dishesNumber: dishIndex.dishesNumber,
        }
        console.log(dishdata.cartId)
        this.$axios.post('/api/cartDishesCombo/add', dishdata)
            .then(res => {
              let result = res.data;
              if (result.code == 200) {
                this.getCartsList();
              }
              this.getTotalPrice();
            })
      } else {
        //菜品减
        dishIndex.dishesNumber--;
        let Dishedata = {
          cartId: 33,
          dishesId: dishIndex.id,
          dishesNumber: dishIndex.dishesNumber,
        }
        this.$axios.post('/api/cartDishesCombo/subtract', Dishedata)
            .then(res => {
              let result = res.data;
              if (result.code == 200) {
                this.getCartsList();
              }
            })

        this.getTotalPrice();
      }
    },
    //套餐数量的加减
    btn2(bool, index) {
      let comboIndex = this.ComboList[index];
      if (bool) {
        //套餐加
        comboIndex.comboNumber++;
        let combodata = {
          cartId: 33,
          comboId: comboIndex.id,
          comboNumber: comboIndex.comboNumber,
        }
        this.$axios.post('/api/cartDishesCombo/add', combodata)
            .then(res => {
              let result = res.data;
              if (result.code == 200) {
                this.getCartsList();
              }
              this.getTotalPrice();
            })
      } else {
        //套餐减
        comboIndex.comboNumber--;
        let combodata = {
          cartId: 33,
          comboId: comboIndex.id,
          comboNumber: comboIndex.comboNumber,
        }
        this.$axios.post('/api/cartDishesCombo/subtract', combodata)
            .then(res => {
              let result = res.data;
              if (result.code == 200) {
                this.getCartsList();
              }
            })

        this.getTotalPrice();
      }
    },

    //一键清空购物车
    delAll: function () {
      let data = {
        cratId: this.cartList[0].cartId,
      }
      this.$axios.post('/api/cartDishesCombo/clearCart', this.$qs.stringify({cartId: 33}))
          .then(res => {
            let result = res.data;
            console.log(result)
            if (result.code == 200) {
              this.getCartsList();
            }
          })
      this.getCartsList();
    },

    //获取购物数据
    getCartsList: function () {
      //   cart/selectById?id=1
      this.DishesList = []
      this.ComboList = []
      this.$axios.get('/api/cart/selectById?id=' + localStorage.getItem('cartId'))
          .then((res) => {
            let result = res.data;
            console.log(result)
            if (result.code == 200) {
              this.cartList = result.data.cartDishesComboList;
              for (let k = 0; k < result.data.dishesList.length; k++) {
                if (result.data.dishesList[k].id !== null) {
                  this.DishesList.push(result.data.dishesList[k]);
                }
              }
              for (let i = 0; i < this.DishesList.length; i++) {
                this.DishesList[i].dishesPhoto = '/api' + this.DishesList[i].dishesPhoto
                for (let j = 0; j < result.data.cartDishesComboList.length; j++) {
                  if (this.DishesList[i].id === result.data.cartDishesComboList[j].dishesId) {
                    this.DishesList[i].dishesNumber = result.data.cartDishesComboList[j].dishesNumber
                  }
                }
              }
              this.getTotalPrice();
              for (let k = 0; k < result.data.comboList.length; k++) {
                if (result.data.comboList[k].id !== null) {
                  this.ComboList.push(result.data.comboList[k]);
                }
              }
              for (let i = 0; i < this.ComboList.length; i++) {
                this.ComboList[i].comboPhoto = '/api' + this.ComboList[i].comboPhoto
                for (let j = 0; j < result.data.cartDishesComboList.length; j++) {
                  if (this.ComboList[i].id === result.data.cartDishesComboList[j].comboId) {
                    this.ComboList[i].comboNumber = result.data.cartDishesComboList[j].comboNumber;
                  }
                }
              }
              this.getTotalPrice();
            } else {
              this.$alert(result.msg);
            }
          })
    },

    //提交订单
    sendorder: function () {
      //提交到接口
      let cart = {
        cartAction: 1,
        cartPrice: this.totalprice,
        id: localStorage.getItem('cartId')
      }
      this.$axios.post('/api/cart/updateById',cart).then((response)=> {
        console.log(response)
        if (response.data.code === 200) {
          this.$axios.get('/api/orders/selectByCartId?CartId=' + localStorage.getItem('cartId')).then((res) => {
            if (res.data.code === 200) {
              console.log(res)
              localStorage.setItem('orders_no', res.data.data)
              this.$message.success('下单成功！')
              this.$router.push('/orders');
            } else {
              this.$message.error('下单失败！')
            }
          })
        }
      })
    },
  },
}
</script>

<style scoped>
.item {
  padding: 5px;
}

.container {
  margin-top: 5px;
  position: relative;
}

.details {
  background-color: #f5f5f5;
  padding: 15px;
  display: flex;
}

.details img {
  width: 60px;
  height: 60px;
}

.details-list {
  padding: 10px;
  width: 100%;
  /*border: 1px solid red;*/
  position: relative;
}

.list-price {
  display: flex;
  justify-content: space-between;
  position: absolute;
  left: 10px;
  top: 35px;
  width: 90%;
  height: 20px;
  line-height: 20px;
}

.detname {
  font-size: 15px;
  position: absolute;
  top: 5px;
  left: 10px;
}

.detprice {
  color: red;
  font-size: 12px;
}

.del {
  width: 20px;
  height: 20px;
  background-color: white;
  border: 1px solid rgb(255, 165, 44);
  border-radius: 3px;
}

.add {
  border: none;
  width: 20px;
  height: 20px;
  background-color: rgb(255, 165, 44);
  border-radius: 3px;
}

.number {
  margin: 0 5px;
}

.Foot {
  display: flex;
  justify-content: space-between;
  padding: 10px;
  font-size: 20px;
  height: 100px;
  line-height: 100px;
  margin-top: 5px;
}

.foot-left {
  font-size: 15px;
}

.totalprice {
  color: red;
  font-size: 15px;
}

.title {
  color: rgb(255, 165, 44);
  margin: 5px 5px 0 5px;
  font-size: 20px;
}

.delAll {
  color: rgb(255, 165, 44);
  font-size: 15px;
  height: 25px;
  line-height: 25px;
  padding: 0 0 5px;
  text-align: right;
  width: 90%;
  margin: 0 auto;
}

.delAll img {
  width: 25px;
  height: 25px;
  /*border: 1px solid red;*/
  float: left;
}

.select {
  border: 1px solid grey;
  width: 120px;
  height: 50px;
  padding: 5px;
  border-radius: 5px;
  position: absolute;
  top: 250px;
  left: 100px;
  color: red;
  display: none;
}

.select .Btn .button1 {
  margin: 5px;
  width: 50px;
  background-color: rgb(255, 165, 44);
  border: none;
  border-radius: 5px;
}

.select .Btn .button2 {
  margin: 5px;
  width: 50px;
  background-color: rgb(242, 242, 242);
  border: none;
  border-radius: 5px;
}
</style>
