import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home,
    redirect: '/tables',
    children: [
      {
        path: '/dishes',
        name: '点餐',
        component: () => import( '../components/main/dishes/dishes.vue'),
      },
      {
        path: '/orders',
        name: '订单',
        component: () => import( '../components/main/orders/orders.vue')
      },
      {
        path: '/my',
        name: '我的',
        redirect: '/notLogin',
        component: () => import( '../components/main/my/my.vue'),
        children: [
          {
            path: '/notLogin',
            name: '未登录',
            component: () => import( '../components/main/my/vip/notLogin')
          },
          {
            path: '/vip',
            name: '会员中心',
            component: () => import( '../components/main/my/vip/vip.vue')
          },
          {
            path: '/loginByPwd',
            name: 'loginByPwd',
            component: () => import( '../components/main/my/login/loginByPwd.vue')
          },
          {
            path: '/loginByPhone',
            name: 'loginByPhone',
            component: () => import( '../components/main/my/login/loginByPhone.vue')
          },
          {
            path: '/PhoneCode',
            name: 'PhoneCode',
            component: () => import( '../components/main/my/login/PhoneCode.vue')
          },
          {
            path: '/getBackPwd',
            name: 'getBackPwd',
            component: () => import( '../components/main/my/login/getBackPwd.vue')
          },
        ]
      },
      {
        path: '/cart',
        name: '购物车',
        component: () => import( '../components/main/cart/cart.vue')
      },
    ]
  },
  {
    path: '/tables',
    name: '选择餐桌',
    component: () => import( '../components/tables/tables.vue')
  },
  {
    path: '/paySuccess',
    name: '支付成功',
    component: () => import('../components/pay/paySuccess.vue')
  }
]
const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})
const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(to) {
  return VueRouterPush.call(this, to).catch(err => err)
}
// 导航守卫，判断用户是否登陆
router.beforeEach((to, from, next) => {
  let isLogin = localStorage.getItem("personInfo");
  if (to.path === '/notLogin') {
    if (isLogin === null || isLogin === '') {
      next();
    } else {
      next('/vip');
    }
  } else if (to.path === '/loginByPwd' || to.path === '/loginByPhone') {
    if (isLogin === null || isLogin === '') {
      next();
    } else {
      next('/vip');
    }
  } else {
    next();
  }
});

export default router
