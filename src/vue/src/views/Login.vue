<template>
    <div fill-height style="max-width: 450px;">
        <div flat >
            <h2>Login</h2>
        </div>
        <div class="pa-3">
        <BaseTextField :label="'User ID'" v-model="userId"></BaseTextField>
        <BaseTextField :label="'Password'" v-model="password"></BaseTextField>               
        <button
            color="primary"
            depressed
            large
            block
            @click="login({id: userId,authCode: password})"
        >Login</button>
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
    }
}
</script>