<template>
  <div id="simple_store">
    <button @click="displayA">store display a</button>
    <div style="height:10px"/>
    <button @click="displayB">store display b</button>
    <div style="height:50px"/>
    <router-view/>
  </div>
</template>

<script>
export default {
  methods: {
    displayA() {
      this.$router.push({
        path: "/vue/simple_store/dis_a"
      });
    },
    displayB() {
      this.$router.push({
        path: "/vue/simple_store/dis_b"
      });
    }
  }
};
</script>
