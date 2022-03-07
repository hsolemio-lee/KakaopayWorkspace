<template>
    <div class="wrapper">
        <div>
            <h2>내 문의사항</h2>
        </div>
        <div>
            <button @click="onClickRegister" >등록</button>
        </div>
        <BaseTable 
            :items="items"
            :headers="headers"
            :tableInfo="searchInfo"
            :totalPage="searchInfo.totalPage"
            @onClickRow="onClickRow"
            @onPage="onPage"></BaseTable>
    </div>
</template>
<script>

export default {
    data () {
        return {
            items: [],
            searchInfo: {
                page: 0,
                size: 10,
                totalPage: 1,
                sort: 'createDate,desc',
            },
            headers: [
                {
                    text: 'Number',
                    value: 'id',
                    width: 1
                },
                {
                    text: 'Title',
                    value: 'title',
                    width: 1
                },
                {
                    text: 'Content',
                    value: 'content',
                    width: 3
                },
                {
                    text: 'Register ID',
                    value: 'registerId',
                    width: 2
                },
                {
                    text: 'Registration Date',
                    value: 'createDate',
                    width: 2,
                    type: 'dateTime'
                }
            ]
        }
    },
    computed: {
    },
    created() {
        this.getPageItems();
    },
    methods: {
        getPageItems() {
            this.$http.get('/rest/v1/inquiry/page', {
                params: {
                    page: this.searchInfo.page,
                    size: this.searchInfo.size,
                    sort: this.searchInfo.sort
                }
            })
            .then(res => {
                this.items = res.data.content;
                this.items.forEach(item => {
                    if(item.reply) {
                        item.reply.title = `[Re:${item.title}] ${item.reply.title}`;
                    }
                })
                this.searchInfo.totalPage = res.data.totalPages;
            });
        },
        onPage(v) {
            this.searchInfo.page = v - 1;
            this.getPageItems();
        },
        onClickRegister() {
            this.$router.push("/inquiry/register");
        },
        onClickRow(item) {
            this.$router.push({
                name: 'inquiryDetail',
                params: {
                    title: item.title, 
                    content: item.content,
                    inquiryRegisterId: item.registerId,
                    replyTitle: item.reply ? item.reply.title : null,
                    replyContent: item.reply ? item.reply.content : null,
                    replyRegisterId: item.reply ? item.reply.registerId : null,
                }
            })
        }
    }
}
</script>
<style scoped>
</style>