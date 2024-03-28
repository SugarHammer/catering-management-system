<template>
  <div style="height:100%">
    <el-container style="height:100%">
      <el-aside style="border-right:1px solid #f1f1f1;width:auto;height:100%;text-align:center">

        <el-menu default-active="combo"
                 class="el-menu-vertical-demo"
                 @open="handleOpen"
                 @close="handleClose"
                 @select="handleSelect">

          <el-menu-item index="combo">
            <!--                        <i class="el-icon-fork-spoon"></i>-->
            <span slot="title">套餐</span>
            <div class="icon" v-if="number!==0">{{ number }}</div>
            <div v-else></div>
          </el-menu-item>

          <el-menu-item v-for="item in menuData" :index="(item.id).toString()">
            <!--                        <i class="el-icon-food"></i>-->
            <span slot="title">{{ item.categoriesName }}</span>
            <div class="icon" v-if="item.dishesCount!==0" style="font-size: 12px">{{ item.dishesCount }}</div>
            <div v-else></div>
          </el-menu-item>

        </el-menu>

      </el-aside>
      <el-main>
        <div class="grid-content bg-purple">
          <!--    子组件-->
          <dishesInfo :menuId="index" @numberChanged="cartEvent"></dishesInfo>
        </div>
        <div class="cart" @click='goCart' style="background-color: #f9f9f9">
          <i class="el-icon-shopping-cart-2"></i>
          <div v-if="number!==0" class="jiaoBiao">{{ carttotal + number }}</div>
          <div v-else class="jiaoBiao">{{ carttotal }}</div>
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import dishesInfo from "./dishesInfo";

export default {
  data() {
    return {
      menuData: [],
      index: 'combo',
      taoCan: [],
      number: 0,
      total: 0
    }
  },
  components: {
    dishesInfo
  },
  created() {
    this.getMenulist()
    this.gettaocan()
  },
  //
  // watch: {
  //   index(val) {
  //     // console.log(val)
  //   }
  // },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    //导航栏回调
    handleSelect: function (index) {
      this.index = index;
    },
    //子传父事件
    cartEvent() {
      this.getMenulist()
      this.gettaocan()
    },
    //跳转到购物车
    goCart() {
      this.$router.push('/cart')
    },
    //请求数据列表
    getMenulist() {
      this.$axios.get('/api/categories/selectAllCategories?cartId=' + localStorage.getItem('cartId')).then((res) => {
        let result = res.data
        let axios = this.$axios
        // console.log(res)
        if (result.code === 200) {
          let menuData = result.data
          //循环点餐数量并将dishesCount融入menuData 并判断两个侧边栏Id与菜品小类Id相等
          menuData.forEach(function (item) {
            item.dishesCount = 0
            result.dishesCount.forEach(function (item2) {
              if (item.id === item2.id) {
                item.dishesCount = item2.dishesCount
              }
            })
          })
          menuData.forEach(function (item) {
            if (item.dishesCount !== 0) {
              item.dishesCount = 0
              axios.get('/api/dishes/selectByCategories?categoriesId=' + item.id + '&cartId=' + localStorage.getItem('cartId')).then((res) => {
                let dishesInfo = res.data.data
                console.log(dishesInfo)
                dishesInfo.forEach(function (dish) {
                  if (dish.dishesNumber) {
                    item.dishesCount += dish.dishesNumber
                  }
                })
              })
            }
          })
          this.menuData = menuData
        } else {
          // this.$alert(this.$message)
        }
      }).catch((err) => {
        console.log(err)
        // this.$alert('请求出错')
      })
    },
    //获取套餐数量
    gettaocan() {
      this.$axios.get('/api/combo/searchAll?cartId=' + localStorage.getItem('cartId')).then((res) => {
        let result = res.data
        if (result.code === 200) {
          this.number = 0
          this.taoCan = result.data
          this.taoCan.forEach(item => {
            if (item.dishesNumber) {
              //套餐数量循环相加
              this.number += item.dishesNumber
            }
          })
        } else {
          // this.$alert(this.$message)
        }
      }).catch(err => {
        console.log(err)
      })
    },

  },
  computed: {
    //文朝明
    //获取购物车总数
    carttotal() {
      let sub2 = 0
      this.menuData.forEach(function (item) {
        sub2 += item.dishesCount
      })
      return sub2
    }
  }

};
</script>
<style scoped>

a {
  text-decoration: none;
}

.cart {
  font-size: 30px;
  color: orange;
  width: 50px;
  height: 50px;
  line-height: 50px;
  border-radius: 50%;
  border: 1px solid orange;
  position: fixed;
  bottom: 80px;
  right: 10%;

}

.cart i {
  transform: translate(-2px, 2px);
}

.el-menu {
  border-right: none !important;
  width: 100px;
}

.el-menu-item:hover,
.el-menu-item.is-active,
/deep/ .el-submenu__title:hover {
  color: #ff9900;
  background-color: white;
}

.el-submenu__title:hover i,
.el-menu-item:hover i {
  color: #ff9900 !important;
}

.el-submenu__title i,
.el-menu-item i {
  transition: all 0.3s;
}

.el-submenu__title i,
.el-menu-item i {
  margin-right: 10px;
}

.el-menu-item-group {
  background-color: #f1f1f1;
}

/deep/ .el-menu-item > span {
  margin-right: 10px;
}

/deep/ .el-submenu__title {
  padding: 0 50px;
}

>>> .van-badge--fixed {
  top: 0;
  right: 0;
}

>>> .van-badge {
  background-color: orange;
}

>>> .icon {
  position: absolute;
  top: 9px;
  right: 5px;
  font-size: 16px;
  color: white;
  width: 18px;
  height: 18px;
  border-radius: 50%;
  line-height: 18px;
  background-color: orange;
}

>>> .jiaoBiao {
  color: white;
  top: -60px;
  right: -32px;
  position: relative;
  background-color: orange;
  border-radius: 50%;
  font-size: 12px;
  width: 22px;
  height: 22px;
  line-height: 22px;
}
</style>
