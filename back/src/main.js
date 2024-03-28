import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Axios from 'axios'
import '../theme/index.css'
import ElementUI from 'element-ui'
// import 'element-ui/lib/theme-chalk/index.css'
import {postServerRequest, getServerRequest} from './utils/api'
import qs from 'qs'
import api from './api'
import * as echarts from 'echarts';

Vue.config.productionTip = false
Vue.prototype.$axios = Axios
Vue.prototype.$qs = qs
Vue.prototype.$api = api
Vue.prototype.$echarts = echarts
Vue.use(ElementUI)
Vue.prototype.postServerRequest = postServerRequest
Vue.prototype.getServerRequest = getServerRequest

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
