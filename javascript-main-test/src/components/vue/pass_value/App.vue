<template>
<div id='parent_component'>
    <div>(parent component scope)</div>
    <div style='height:10px' />
    <sub_component :sub_msg='parent_msg'></sub_component>
</div>
</template>

<script>
import sub_component from './SubComponent.vue';
export default {
    data() {
        return {
            parent_msg: 'parent msg'
        };
    },
    components: {
        sub_component
    }
};
</script>
