import Vue from "vue";
import VueRouter from "vue-router";
import store from "../store";

Vue.use(VueRouter);

const rejectAuthUser = (to, from, next) => {
  if(store.state.isLogin) {
    // 이미 로그인된 유저
    next("/");
  } else {
    next();
  }
}

const authUser = (to, from, next) => {
  if(store.state.isLogin) {
    next();
  } else {
    next("/loginForm");
  }
}

const routes = [
  {
    path: "/",
    redirect: "/home"
  },
  {
    path: "/home",
    name: "home",
    beforeEnter: authUser,
    component: () =>
      import("../views/Home.vue"),
  },
  {
    path: "/loginForm",
    name: "login",
    beforeEnter: rejectAuthUser,
    component: () =>
      import("../views/Login.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
