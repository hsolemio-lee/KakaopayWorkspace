<template>
    <div class="wrapper">
        <div>
            <h2>문의사항 리스트</h2>
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
import { mapState } from 'vuex';

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
                    width: 10,
                },
                {
                    text: 'Title',
                    value: 'title',
                    width: 10,
                },
                {
                    text: 'Content',
                    value: 'content',
                    width: 30,
                },
                {
                    text: 'Register ID',
                    value: 'registerId',
                    width: 10,
                },
                {
                    text: 'Manager ID',
                    value: 'managerId',
                    width: 10,
                },
                {
                    text: 'Registration Date',
                    value: 'createDate',
                    type: 'dateTime',
                    width: 10,
                },
                {
                    text: 'Action',
                    value: 'button',
                    type: 'button',
                    width: 10,
                }
            ]
        }
    },
    computed: {
        ...mapState(['userInfo'])
    },
    created() {
        this.getPageItems();
    },
    methods: {
        getPageItems() {
            this.$http.get('/rest/v1/inquiry/manager/page', {
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
                    if(item.status === 'NOT_ASSIGNED') {
                        item.button = {
                            buttonText: 'Assign to me',
                            func: this.onClickAssign
                        }
                    } else if (item.status === 'ASSIGNED') {
                        item.button = {
                            buttonText: 'Reply',
                            func: this.onClickReply,
                            disabled: this.userInfo.userId !== item.managerId
                        }
                    } else {
                        item.button = {
                            buttonText: 'Complete',
                            func: () => {},
                            disabled: true,
                        }
                    }
                })
                this.searchInfo.totalPage = res.data.totalPages;
            });
        },
        onPage(v) {
            this.searchInfo.page = v-1;
            this.getPageItems();
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
        },
        onClickAssign(item) {
            this.$http.post(`/rest/v1/inquiry/${item.id}/manager`)
            .then(res => {
                console.log(res.data);
                this.getPageItems();
            })
        },
        onClickReply(item) {
            this.$router.push({
                name: "registerReply",
                params: {id: item.id, title: item.title, content: item.content},
            })
        }
    }
}
</script>
<style scoped>
.wrapper {
    width: 100%;
}
</style>