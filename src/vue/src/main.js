import Vue from "vue";
import "./components/common"
import router from "./router";
import store from "./store";
import App from "./App.vue";
import {solAxios} from "./config/solAxios";

Vue.config.productionTip = false;
Vue.prototype.$http = solAxios;

new Vue({
  router,
  store,
  beforeCreate() {
    this.$store.dispatch('loginCheck');
  },
  render: (h) => h(App),
}).$mount("#app");
