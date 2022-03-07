<template>
<div class="text-field-wrapper">
    <p>{{label}}</p>
    <select
        class="select-box"
        v-model="selected"
        @change="change"
    >
        <option v-for="(item, index) in items" :key="index" :value="item.value">{{item.text}}</option>
    </select> 
</div>
</template>

<script>
export default {
    props: {
        label: {
            type: String,
            default: () => ""
        },
        items: {
            type: Array,
            default: () => []
        }
    },
    data() {
        return {
            selected: "",
        }
    },
    methods: {
        change(e) {
            this.$emit('input', e.target.value);
        }
    }
}
</script>

<style scoped>
.text-field-wrapper {
    display: flex;
    max-height: 30px;
    align-items: center;
    margin: 10px;
}
.text-field-wrapper > p {
    margin: 10px;
    width: 100px;
}
</style>