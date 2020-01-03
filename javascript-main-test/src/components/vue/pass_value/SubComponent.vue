<template>
  <div id="sub_component">(in custom sub component) parent scope msg: {{sub_msg}}</div>
</template>

<script>
export default {
  props: ["sub_msg"],
  data() {
    return {};
  }
};
</script>
