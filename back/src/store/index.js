import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    // 存储token
    Authorization: localStorage.getItem('Authorization') ? localStorage.getItem('Authorization') : '',
    loginUsername: localStorage.getItem('loginUsername') ? localStorage.getItem('loginUsername') : '',
    loginHeadImg: localStorage.getItem('loginHeadImg') ? localStorage.getItem('loginHeadImg') : '',
    loginUserId: localStorage.getItem('loginUserId') ? localStorage.getItem('loginUserId') : ''
  },
  mutations: {
    // 修改token，并将token存入localStorage
    changeLogin(state, user) {
      state.Authorization = user.Authorization;
      state.loginUsername = user.loginUser.mumberName;
      state.loginHeadImg = "http://192.168.1.156:8080/" + user.loginUser.mumberHeadImg;
      state.loginRoleId = user.loginUser.roleId;
      localStorage.setItem('Authorization', user.Authorization);
      localStorage.setItem('loginUsername', user.loginUser.mumberName);
      localStorage.setItem('loginHeadImg', "http://192.168.1.156:8080/" + user.loginUser.mumberHeadImg);
      localStorage.setItem('loginUserId', user.loginUser.id);
      localStorage.setItem('loginRoleId', user.loginUser.roleId);
    }
  }
});

export default store;
