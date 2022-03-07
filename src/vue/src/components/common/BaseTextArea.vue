<template>
<div class="text-field-wrapper">
    <p>{{label}}</p>
    <textarea
        v-model="value"
        @change="change"
        :readonly="readOnly"
    /> 
</div>
</template>

<script>
export default {
    props: {
        label: {
            type: String,
            default: () => ""
        },
        readOnly: {
            type: Boolean,
            default: () => false
        },
        val: {
            type: String,
            default: () => ""
        }
    },
    data() {
        return {
            value: "",
        }
    },
    created() {
        this.value = this.val;
    },
    methods: {
        change(e) {
            this.$emit('input', e.target._value);
        }
    }
}
</script>

<style scoped>
.text-field-wrapper {
    display: flex;
    max-height: 100px;
    margin: 10px;
}
.text-field-wrapper > p {
    margin: 10px 10px 10px 10px;
    width: 100px;
}
textarea {
    resize: none;
    width: 200px;
    height: 100px;
}
</style>