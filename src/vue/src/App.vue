<template>
  <div>
    <div class="logout-wrapper">
      <button v-if="isLogin" @click="logout">로그아웃</button>
      <button v-else @click="goToLoginPage">로그인</button>
    </div>
    <router-view></router-view>
  </div>
</template>
<script>
import './assets/css/style.css';
import { mapState, mapActions } from "vuex";
  export default {
    data () { 
      return {

      }
    },
    computed: {
      ...mapState(["isLogin"])
    },
    methods: {
      ...mapActions(["logout"]),
      goToLoginPage() {
        this.$router.push("/loginForm");
      }
    }
  }
</script>
<style>
</style>
