<template>
  <div>

    <!--卡片-->
    <van-card
        v-for="(item,index) in list"
        :key="index"
        :price="item.Price"
        :title="item.Name"
        :thumb="item.Photo">

      <template #footer>
        <van-stepper v-model="item.dishesNumber" @minus="s(item)" @plus="a(item)" :id='ownClass(item)'
                     default-value="0" min="0" theme="round" button-size="22" button-background="orange"
                     disable-input/>
      </template>
    </van-card>
  </div>
</template>

<script>
export default {
  props: {
    menuId: {
      type: String,
      default: 'combo'
    }
  },
  data() {
    return {
      list: [],
      total: 0
    }
  },
  created() {
    this.getDishesList()
  },
  methods: {
    getDishesList() {
      let menuId = this.menuId;
      let url = '/api'
      if (this.menuId === 'combo') {
        url = url + '/combo/searchAll?cartId=' + localStorage.getItem('cartId')
      } else {
        url = url + '/dishes/selectByCategories?categoriesId=' + this.menuId + '&cartId=' + localStorage.getItem('cartId')
      }
      this.$axios.get(url).then((res) => {
        let result = res.data
        console.log(result)
        if (result.code === 200) {
          this.list = result.data
          console.log(this.list)
        } else {
          // this.$alert(this.$message)
        }
        this.list.forEach(function (item) {
          if (menuId === 'combo') {
            //给服务器传过来的转义图片添加地址-端口
            item.Photo = '/api' + item.comboPhoto
            //重命名套餐
            item.Name = item.comboName
            item.Price = item.comboPrice
          } else {
            item.Photo = '/api' + item.dishesPhoto
            //菜品小类
            item.Price = item.dishesPrice
            item.Name = item.dishesName
          }
        })
      }).catch((err) => {
        console.log(err)
        // this.$alert('请求出错')
      })
    },


    //文朝明
    //循环出进步器id==商品id
    ownClass(item) {
      // return `card${item.id}`
      var cid = item.id
      //console.log(cid)
      return item.id
    },
    //获取ID  加
    a(item) {
      let goodsId = item.id
      console.log('商品ID' + goodsId)//.splice(item,1);
      this.add(goodsId)//调用计数加
      this.addDishes(goodsId)
      this.$emit('numberChanged')
    },
    //减
    s(item) {
      let goodsId = item.id
      console.log('商品ID' + goodsId)//.splice(item,1);
      this.sub(goodsId)//调用计数加
      this.subDishes(goodsId)
      this.$emit('numberChanged')
    },

    // b(item){
    //     this.list.splice(item,1);
    // },
    //加 获取步进器值
    add: function (id) {
      let goodsNum = Number(document.getElementById(id).childNodes[1].getAttribute('aria-valuenow')) + 1
      console.log('商品数量' + goodsNum)
    },
    //减 获取步进器值
    sub: function (id) {
      let c = Number(document.getElementById(id).childNodes[1].getAttribute('aria-valuenow')) - 1
      console.log('商品数量' + c)
    },


    //加菜到购物车
    addDishes(id) {
      let xid
      if (this.menuId === 'combo') {
        xid = {
          cartId: localStorage.getItem('cartId'),
          operation: 'add',
          comboId: id
        }
      } else {
        xid = {
          cartId: localStorage.getItem('cartId'),
          operation: 'add',
          dishesId: id
        }
      }
      console.log(xid)
      this.$axios({
        url: '/api/cartDishesCombo/add',
        method: 'post',
        headers: {
          'Content-Type': 'application/json'
        },
        data: xid
      }).then((res) => {
        console.log(res)
      }).catch((err) => {
        console.log(err)
      })
    },
    //减菜到购物车,id:菜品Id;
    subDishes(id) {
      let xid
      if (this.menuId === 'combo') {
        xid = {
          cartId: localStorage.getItem('cartId'),
          operation: 'sub',
          comboId: id
        }
      } else {
        xid = {
          cartId: localStorage.getItem('cartId'),
          operation: 'sub',
          dishesId: id
        }
      }
      console.log(xid)
      this.$axios({
        url: '/api/cartDishesCombo/subtract',
        method: 'post',
        headers: {
          'Content-Type': 'application/json'
        },
        data: xid
      }).then((res) => {

        console.log(res)
      }).catch((err) => {
        this.$alert(err.data.msg)
        console.log(err)
      })
    }
  },
  //监听数值变化
  watch: {
    menuId(val) {
      // console.log(val)
      //每次改变调用一次获取列表
      this.getDishesList()
    }
  }
}


</script>
<style scoped>
.van-card__content button {
  /*padding-bottom:80px !important;*/
  background-color: orange;
}

.van-card__img {
  width: 55% !important;
  height: 55% !important;
}

.van-card__thumb {
  margin-right: -44px !important;
}

.van-stepper {
  width: 80px !important;
  float: right;
  margin-top: -55px;
}

>>> .van-stepper--round .van-stepper__plus {
  color: #fff;
  background-color: orange;
}

>>> .van-stepper--round .van-stepper__minus {
  color: orange;
  background-color: #fff;
  border: 1px solid orange;
}

</style>
