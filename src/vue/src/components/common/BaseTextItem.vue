<template>
<div class="text-field-wrapper">
    <p>{{label}}</p>
    <p>{{value}}<p/> 
</div>
</template>

<script>
export default {
    props: {
        label: {
            type: String,
            default: () => ""
        },
        value: {
            type: String,
            default: () => ''
        }
    },
    data() {
        return {
        }
    },
    methods: {
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