<template>
  <div>
    <div class="logout-wrapper">
      <button v-show="isLogin" @click="logout">로그아웃</button>
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
    }
  }
</script>
<style>
</style>
