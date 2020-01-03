<template>
<div id='simple_store_dis_a'>
    <div>simple store display a, number: {{state.store_number}}, string: {{state.store_string}}</div>
    <div style='height:10px' />
    <button @click='alterNum'>alter store number</button>
    <button @click='alterStr'>alter store string</button>
</div>
</template>

<script>
import store from './simple-store'
export default {
    data() {
        return {
            state: store.state
        }
    },
    methods: {
        alterNum() {
            store.state.store_number++;
        },
        alterStr() {
            store.state.store_string += ', alter string have worked'
        }
    }
}
</script>
