import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      redirect: '/login'
    },
    { // 登录
      path: '/login',
      name: 'Login',
      component: () => import('@/views/login')
    },
    { // 主界面
      path: '/home',
      name: '首页',
      component: () => import('@/views/home'),
      children: [
        { // 菜品管理 => 菜品大类
          path: '/home/dishes/categories',
          name: '菜品管理/菜品大类',
          component: () => import('@/components/main/dishes/categories')
        },
        { // 菜品管理 => 菜品小类
          path: '/home/dishes/subcategories',
          name: '菜品管理/菜品小类',
          component: () => import('@/components/main/dishes/subcategories')
        },
        { // 菜品管理 => 菜品信息
          path: '/home/dishes/info',
          name: '菜品管理/菜品信息',
          component: () => import('@/components/main/dishes/dishesInfo')
        },
        { // 菜品管理 => 菜品单位
          path: '/home/dishes/unit',
          name: '菜品管理/菜品单位',
          component: () => import('@/components/main/dishes/unit')
        },
        { // 菜品管理 => 套餐
          path: '/home/dishes/combo',
          name: '菜品管理/套餐',
          component: () => import('@/components/main/dishes/combo')
        },
        { // 菜品管理 => 菜品做法
          path: '/home/dishes/practices',
          name: '菜品管理/菜品做法',
          component: () => import('@/components/main/dishes/practices')
        },
        { // 菜品管理 => 做法关联
          path: '/home/dishes/relevanceWay',
          name: '菜品管理/做法关联',
          component: () => import('@/components/main/dishes/relevanceWay')
        },
        { // 餐桌管理 => 餐桌信息
          path: '/home/tables/info',
          name: '餐桌管理/餐桌信息',
          component: () => import('@/components/main/tables/tablesInfo')
        },
        { // 餐桌管理 => 餐桌区域
          path: '/home/tables/areas',
          name: '餐桌管理/餐桌区域',
          component: () => import('@/components/main/tables/areas')
        },
        { // 订单管理
          path: '/home/orders',
          name: '订单管理',
          component: () => import('@/components/main/orders/orders')
        },
        { // 会员管理 => 会员基本信息
          path: '/home/members/info',
          name: '会员管理/会员信息',
          component: () => import('@/components/main/members/membersInfo')
        },
        { // 会员管理 => 会员积分交易记录
          path: '/home/members/integralTrading',
          name: '会员管理/积分交易',
          component: () => import('@/components/main/members/integralTrading')
        },
        { // 会员管理 => 会员充值
          path: '/home/members/topUp',
          name: '会员管理/会员充值',
          component: () => import('@/components/main/members/topUp')
        },
        { // 系统管理 => 权限设置
          path: '/home/system/jurisdiction',
          name: '系统管理/权限设置',
          component: () => import('@/components/main/system/jurisdiction')
        },
        { // 系统管理 => 系统日志
          path: '/home/system/log',
          name: '系统管理/系统日志',
          component: () => import('@/components/main/system/systemLog')
        },
        { // 系统管理 => 账号信息
          path: '/home/system/accountInfo',
          name: '系统管理/账号信息',
          component: () => import('@/components/main/system/accountInfo')
        },
        { // 报表分析 => 菜品销售查询
          path: '/home/charts/dishesSales',
          name: '报表分析/菜品销售查询',
          component: () => import('@/components/main/charts/dishesSales')
        },
        { // 报表分析 => 营业情况查询
          path: '/home/charts/businessSearch',
          name: '报表分析/营业情况查询',
          component: () => import('@/components/main/charts/businessSearch')
        },
        { // 报表分析 => 门店流水查询
          path: '/home/charts/turnover',
          name: '报表分析/门店流水查询',
          component: () => import('@/components/main/charts/turnover')
        },
        { // 报表分析 => 营业情况分析
          path: '/home/charts/businessReport',
          name: '报表分析/营业情况分析',
          component: () => import('@/components/main/charts/businessReport')
        },
        { // 报表分析 => 会员增量分析
          path: '/home/charts/memberData',
          name: '报表分析/会员增量分析',
          component: () => import('@/components/main/charts/memberData')
        },
        { // 报表分析 => 消费菜品排行
          path: '/home/charts/dishesRanking',
          name: '报表分析/消费菜品排行',
          component: () => import('@/components/main/charts/ranking')
        }
      ]
    }
  ]
})

// 导航守卫，判断用户是否登陆
router.beforeEach((to, from, next) => {
  if (to.path === '/login') {
    next();
  } else {
    let token = localStorage.getItem('Authorization');
    if (token === null || token === '') {
      next('/login');
    } else {
      next();
    }
  }
});

export default router;
