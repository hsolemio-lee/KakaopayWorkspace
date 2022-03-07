<template>
<div class="text-field-wrapper">
    <p>{{label}}</p>
    <input
        type="text"
        v-model="value"
        label="ID"
        @change="change"
    /> 
</div>
</template>

<script>
export default {
    props: {
        label: {
            type: String,
            default: () => ""
        }
    },
    data() {
        return {
            value: "",
        }
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
    max-height: 30px;
    align-items: center;
}
.text-field-wrapper > p {
    margin: 10px 10px 10px 10px;
    width: 100px;
}
</style>