<template>
    <div style="max-width: 450px;">
        <div>
            <h2>Register Inquiry</h2>
        </div>
        <div>
            <BaseTextField :label="'Title'" v-model="inquiryForm.title"></BaseTextField>
            <BaseTextArea :label="'Content'" v-model="inquiryForm.content" :type="'textarea'"></BaseTextArea>
        </div>
        <div>        
            <button
                @click="onClickCancel"
            >취소</button>
            <button
                @click="createInquiry"
            >등록</button>  
        </div> 
    </div>
</template>
<script>

export default {
    data () {
        return {
            inquiryForm: {
                title: "",
                content: "",
            },
        }
    },
    computed: {
    },
    methods: {
        onClickCancel() {
            this.$router.push("/user/home");
        },
        createInquiry() {
            this.$http.post('/rest/v1/inquiry', this.inquiryForm)
            .then(res => {
                console.log(res);
                alert("등록 완료");
                this.$router.push('/user/home');
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