<template>
    <div style="max-width: 450px;">
        <div>
            <h2>Inquiry Detail</h2>
        </div>
        <div>
            <BaseTextItem :label="'Title'" :value="inquiry.title"/>
            <BaseTextItem :label="'Register ID'" :value="inquiry.registerId"/>
            <BaseTextArea :label="'Content'" :val="inquiry.content" :readOnly="true"/>
        </div>
        <div v-if="reply.title">
            <BaseTextItem :label="'Reply Title'" :value="reply.title"/>
            <BaseTextItem :label="'Register ID'" :value="reply.registerId"/>
            <BaseTextArea :label="'Reply Content'" :val="reply.content" :readOnly="true"/>
        </div>
        <div>        
            <button
                @click="onClickList"
            >목록</button>
        </div> 
    </div>
</template>
<script>
import { mapState } from 'vuex';

export default {
    computed: {
        ...mapState(['userInfo'])
    },
    data () {
        return {
            inquiry: {
                id: null,
                title: '',
                content: '',
                registerId: "",
            },
            reply: {
                title: "",
                content: "",
                registerId: "",
            },
        }
    },
    created() {
        this.inquiry.title = this.$route.params.title;
        this.inquiry.content = this.$route.params.content;
        this.inquiry.registerId = this.$route.params.inquiryRegisterId;
        this.reply.title = this.$route.params.replyTitle;
        this.reply.content = this.$route.params.replyContent;
        this.reply.registerId = this.$route.params.replyRegisterId;
    },
    methods: {
        onClickList() {
            console.log(this.userInfo);
            if(this.userInfo.role === "USER") {
                this.$router.push("/user/home");
            } else {
                this.$router.push("/manager/home");
            }
        },
    }
}
</script>