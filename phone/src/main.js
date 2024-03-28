import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
// import 'element-ui/lib/theme-chalk/index.css';
import '../theme/index.css'
import Axios from 'axios'
import qs from 'qs'
import api from './api'
import Vant from 'vant';
import 'vant/lib/index.css';

Vue.config.productionTip = false
Vue.prototype.$axios=Axios
Vue.prototype.$qs=qs
Vue.prototype.$api=api
Vue.use(ElementUI);
Vue.use(Vant);

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
