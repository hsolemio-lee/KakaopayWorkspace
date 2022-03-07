<template>
    <div style="max-width: 450px;">
        <div>
            <h2>Register Reply</h2>
        </div>
        <div>
            <BaseTextItem :label="'Title'" :value="inquiry.title"/>
            <BaseTextItem :label="'Content'" :value="inquiry.content"/>
        </div>
        <div>
            <BaseTextField :label="'Reply Title'" v-model="replyForm.title"></BaseTextField>
            <BaseTextArea :label="'Reply Content'" v-model="replyForm.content" :type="'textarea'"></BaseTextArea>
        </div>
        <div>        
            <button
                @click="onClickCancel"
            >취소</button>
            <button
                @click="createReply"
            >등록</button>  
        </div> 
    </div>
</template>
<script>

export default {
    data () {
        return {
            inquiry: {
                id: null,
                title: '',
                content: '',
            },
            replyForm: {
                title: "",
                content: "",
                inquiryId: "",
            },
        }
    },
    created() {
        this.replyForm.inquiryId = this.$route.params.id;
        this.inquiry.title = this.$route.params.title;
        this.inquiry.content = this.$route.params.content;
    },
    computed: {
    },
    methods: {
        onClickCancel() {
            this.$router.push("/manager/home");
        },
        createReply() {
            
            this.$http.post('/rest/v1/inquiry/reply', this.replyForm)
            .then(res => {
                console.log(res);
                alert("등록 완료");
                this.$router.push('/manager/home');
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