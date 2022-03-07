<template>
    <div style="max-width: 450px;">
        <div >
            <h2>Login</h2>
        </div>
        <div>
            <BaseTextField :label="'User ID'" v-model="userId"></BaseTextField>
            <BaseTextField :label="'Password'" v-model="password" type="password"></BaseTextField>             
        </div>
        <div>   
            <button
                @click="login({id: userId,authCode: password})"
            >Login</button>
            <button
                @click="goToJoinPage()"
            >매니저 등록</button>
            <button
                @click="goToUserPage()"
            >고객 페이지</button>
        </div> 
    </div>
</template>
<script>
import { mapState, mapActions } from "vuex"; 

export default {
    data () {
        return {
            userId: null,
            password: null,
        }
    },
    computed: {
        ...mapState(["isLogin", "isLoginError"]),
    },
    methods: {
        ...mapActions(["login"]),
        goToJoinPage() {
            this.$router.push('/joinForm');
        },
        goToUserPage() {
            this.$router.push('/user/home');
        }
    }
}
</script>