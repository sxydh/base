<template>
  <div id="simple_store">
    <button @click="displayA">store display a</button>
    <div style="height:10px" />
    <button @click="displayB">store display b</button>
    <div style="height:50px" />
    <router-view />
  </div>
</template>

<script>
export default {
  methods: {
    displayA() {
      let routeData = this.$router.resolve({
        path: "/vue/store/dis_a",
        query: {
          /*
          data: ""
          */
        }
      });
      window.open(routeData.href, "_blank");
    },
    displayB() {
      this.$router.push({
        path: "/vue/store/dis_b"
      });
    }
  }
};
</script>
