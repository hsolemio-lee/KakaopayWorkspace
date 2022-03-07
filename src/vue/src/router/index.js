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
  const userInfo = store.state.userInfo;
  
  if(store.state.isLogin) {
    if(to === 'userHome' && userInfo.role !== 'USER') {
      next("/403");
    } else if(to === 'managerHome' && userInfo.role !== 'MANAGER'){
      next("/403");
    } else {
      next();
    }
  } else {
    next("/loginForm");
  }
}

const routes = [
  {
    path: "/",
    redirect: "/user/home"
  },
  {
    path: "/user/home",
    name: "userHome",
    beforeEnter: authUser,
    component: () =>
      import("../views/UserHome.vue"),
  },
  {
    path: "/manager/home",
    name: "managerHome",
    beforeEnter: authUser,
    component: () =>
      import("../views/ManagerHome.vue"),
  },
  {
    path: "/inquiry/register",
    name: "registerInquiry",
    beforeEnter: authUser,
    component: () =>
      import("../views/RegisterInquiry.vue"),
  },
  {
    path: "/reply/register",
    name: "registerReply",
    beforeEnter: authUser,
    component: () =>
      import("../views/RegisterReply.vue"),
    props: true,
  },
  {
    path: "/inquiry/detail",
    name: "inquiryDetail",
    beforeEnter: authUser,
    component: () =>
      import("../views/InquiryDetail.vue"),
    props: true,
  },
  {
    path: "/loginForm",
    name: "login",
    beforeEnter: rejectAuthUser,
    component: () =>
      import("../views/Login.vue"),
  },
  {
    path: "/joinForm",
    name: "join",
    component: () =>
      import("../views/Join.vue"),
  },
  {
    path: "/403",
    name: "403",
    component: () =>
      import("../views/403.vue"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
