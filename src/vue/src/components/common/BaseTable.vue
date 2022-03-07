<template>
    <div class="wrapper">
        <div class="table-wrapper">

            <div v-if="items.length === 0" style="width:100%;display:flex; justify-content: center;">
                <p>데이터가 존재하지 않습니다.</p>
            </div>
            <table v-else class="basic">
                <colgroup>
                    <col :class="{'col-1': header.width === 1, 'col-2': header.width === 2, 'col-3': header.width === 3}" v-for="(header, idx) in headers" :key="idx">
                </colgroup>
                <thead>
                    <th v-for="(header, idx) in headers" :key="idx" >{{header.text}}</th>
                </thead>
                <tbody >
                    <template v-for="(item, idx) in items" >
                        <tr @click="onClickRow(item)" :key="idx">
                            <template v-for="(header, idx) in headers" >
                                <td :key="idx" v-if="header.type === 'dateTime'">
                                    {{item[header.value] | datetimeFormat}} 
                                </td>
                                <td :key="idx" v-else-if="header.type === 'button'">
                                    <button @click.stop="item.button.func(item)" :disabled="item.button.disabled">{{item.button.buttonText}}</button>
                                </td>
                                <td :key="idx" v-else>
                                    {{item[header.value]}} 
                                </td>
                            </template>
                        </tr>
                        <tr v-if="item.reply" :key="'sub_'+idx" style="background: rgb(228, 225, 225)">
                            <template v-for="(header, idx) in headers" >
                                <td :key="'sub_'+idx" v-if="header.type === 'dateTime'">
                                    {{item.reply[header.value] | datetimeFormat}} 
                                </td>
                                <td :key="idx" v-else>
                                    {{item.reply[header.value]}} 
                                </td>
                            </template>
                        </tr>
                    </template>
                </tbody>
            </table>
        </div>
        <div v-show="items.length !== 0" class="pagination-wrapper">
            <div class="pagination">
                <a href="#" @click="onClickPrev" >&laquo;</a>
                <a href="#" v-for="num in getLastPage()" :key="num" @click="onClickPage(num)">{{num}}</a>
                <a href="#" @click="onClickNext" >&raquo;</a>
            </div>
        </div>
    </div>
</template>
<script>

export default {
    props: {
        items: {
            type: Array,
            default: () => []
        },
        headers: {
            type: Array,
            default: () => []
        },
        tableInfo: {
            type: Object,
            default: () => {
                return {
                    page: 0,
                    size: 10,
                }
            }
        },
        totalPage: {
            type: Number,
            default: () => 1
        }
    },
    watch: {
        tableInfo(v) {
            this.page = v.page;
            this.size = v.size;
        }
    },
    data () {
        return {
            page: 0,
            size: 10,
        }
    },
    computed: {
    },
    created() {
    },
    methods: {
        getLastPage() {
            const currentLastPage = parseInt(this.page / 5)*5+5;
            return this.totalPage > currentLastPage ? currentLastPage : this.totalPage;
        },
        onClickPage(v) {
            this.$emit('onPage', v);
        },
        onClickNext() {
            this.$emit('onPage', this.page+1 > this.lastPage ? this.page : this.page+1);
        },
        onClickPrev() {
            this.$emit('onPage', this.page-1 <= 0 ? this.page : this.page-1);
        },
        onClickRow(item) {
            this.$emit('onClickRow', item);
        }
    }
}
</script>
<style>

</style>