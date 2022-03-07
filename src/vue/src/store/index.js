import Vue from "vue";
import Vuex from "vuex";
import router from "../router";
import {solAxios} from "../config/solAxios";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    userInfo: null,
    isLogin: false,
    isLoginError: false,
  },
  mutations: {
    // 로그인 성공
    loginSuccess(state, userInfo) {
      state.isLogin = true;
      state.isLoginError = false;
      state.userInfo = userInfo;
    },
    // 로그인 실패
    loginError(state) {
      state.isLogin = false;
      state.isLoginError = true;
    },
    logout(state) {
      state.isLogin = false;
      state.userInfo = null;
    },
    showProgressBar(state, arg) {
      state.progressBar = arg;
    }
  },
  actions: {
    // 로그인 시도
    login({ dispatch, commit }, signObj) {
      solAxios.post("/login", signObj)
      .then(res => {
        const loginInfo = res.data;
        localStorage.setItem("authToken", loginInfo.authCode);
        alert("Login Success...");  
        dispatch("loginCheck");
      })
      .catch(error => {
        console.log(error);
        alert("Login fail...");
        commit('loginError');
      });
    },
    loginCheck({commit}) {
      let authToken = localStorage.getItem("authToken");
      solAxios.defaults.headers.common['Authorization'] = authToken;
      solAxios.get("/rest/v1/login/check").
      then(res => {
        const loginInfo = res.data;
        let userInfo = {
          userId: loginInfo.userId,
          role: loginInfo.role,
        };
        commit('loginSuccess', userInfo);
        if(userInfo.role === 'USER') {
          router.push({name: 'userHome'});
        } else if (userInfo.role === 'MANAGER') {
          router.push({name: 'managerHome'})
        }
      })
      .catch(error => {
        console.log(error);
        commit('logout');
        router.push({name: 'login'});
      })
    },
    logout({commit}) {
      commit('logout');
      router.push({name: 'login'});
    },
  },
  modules: {},
});
