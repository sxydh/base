<template>
  <div id="sxydh_store">
    <button style="width:100px;">input</button>
    <input v-model="inputNum">
    <input v-model="inputStr">
    <input v-model="inputBlean">
    <div></div>
    <button style="width:100px;">store</button>
    <input v-model="num">
    <input v-model="str">
    <input v-model="blean">
    <div></div>
    <button @click="mutationUpdate" style="width:200px;">use mutation to update</button>
    <div></div>
    <button @click="actionUpdate" style="width:200px;">use action to update</button>
    <div></div>
    <button @click="updateFromStore" style="width:200px;">update data from store</button>
  </div>
</template> 

<script>
import { mapState, mapMutations, mapGetters, mapActions } from "vuex";
export default {
  data() {
    return {
      inputNum: 0,
      inputStr: "empty",
      inputBlean: true,
      num: null,
      str: null,
      blean: null
    };
  },
  methods: {
    ...mapGetters(["getNum", "getStr", "getBlean"]),
    ...mapMutations(["setNum", "setStr", "setBlean"]),
    ...mapMutations({
      updateNum: "setNum",
      updateStr: "setStr",
      updateBlean: "setBlean"
    }),
    updateFromStore() {
      this.num = this.$store.getters.getNum;
      this.str = this.getStr();
      this.blean = this.getBlean();
    },
    mutationUpdate() {
      this.$store.commit("update", {
        newNum: this.inputNum,
        newStr: this.inputStr,
        newBlean: this.inputBlean
      });
    },
    actionUpdate() {
      this.$store.dispatch("update", {
        newNum: this.inputNum,
        newStr: this.inputStr,
        newBlean: this.inputBlean
      });
    }
  }
};
</script>
