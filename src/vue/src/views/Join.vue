<template>
    <div style="max-width: 450px;">
        <div>
            <h2>Join</h2>
        </div>
        <div>
            <BaseTextField :label="'User ID'" v-model="userForm.id"></BaseTextField>
            <BaseTextField :label="'Password'" v-model="userForm.authCode" :type="'password'"></BaseTextField>
            <BaseTextField :label="'Name'" v-model="userForm.name"></BaseTextField>
        </div>
        <div>        
            <button
                @click="onClickCancel"
            >취소</button>
            <button
                @click="createUser"
            >등록</button>  
        </div> 
    </div>
</template>
<script>

export default {
    data () {
        return {
            userForm: {
                id: "",
                authCode: "",
                name: "",
                role: "MANAGER",
            },
        }
    },
    computed: {
    },
    methods: {
        onClickCancel() {
            this.$router.push("/loginForm");
        },
        createUser() {
            this.$http.post('/rest/v1/user', this.userForm)
            .then(res => {
                console.log(res);
                alert("가입 완료");
                this.$router.push('/loginForm');
            })
            .catch(err => {
                if(err.response.data.errors) {
                    const error = err.response.data.errors[0];
                    alert("등록 실패\n"+error.invalidParam+": "+error.errorMessage);                
                } else {
                    alert("등록 실패");                
                }
            });
        }
    }
}
</script>