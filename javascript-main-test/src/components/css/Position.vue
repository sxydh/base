<template>
  <div id="css_position">
    <!-- --- -->
    <div id="static">
      <div class="static"></div>
      <div
        class="static float"
        style="float:left;margin-left:100px;"
        title="
this element is out of document flow;
margin-left, first, ignore without (margin and float) elements, then, relative to the previous float element border, if there isn't one, it will be in the new line;
other position relative to the previous element that isn't out of document flow"
      ></div>
      <div class="static float" style="float:left"></div>
      <div class="static"></div>
      <div
        class="static"
        style="z-index:-1;margin-top:5%;margin-left:5%;"
        title="
z-index not working;
margin top percent relative to the width of the containing block, other percent relative to the containing block size correspondingly"
      ></div>
      <div class="static float" style="float:left"></div>
      <div
        class="static"
        style="width:auto;"
        title="
if parent element has size style, its width equals parent width"
      ></div>
      <div
        class="static float"
        style="float:left;width:auto;margin-top:50px;"
        title="
it is out of document flow, so auto width doesn't work"
      >&nbsp;</div>
      <div class="static float" style="float:left"></div>
      <div class="static" style="margin-top:150px;width:500px;display:flex;padding:5px;">
        <div
          class="static"
          style="height:100%;margin:0px;margin-right:5px;background-color:#fdc8c8;"
          title="
parent display flex"
        ></div>
        <div
          class="static"
          style="height:100%;margin:0px;margin-left:5px;background-color:#C1C1C1;width:100%;"
        ></div>
      </div>
      <div class="static" style="height:150px;width:200px;">
        <div class="static" style="height:50px;background-color:#C1C1C1;margin-bottom:5px;"></div>
        <div class="static" style="height:100%;background-color:#C1C1C1;"></div>
      </div>
    </div>
    <!-- --- -->
    <div id="relative">
      <div class="relative"></div>
      <div
        class="relative float"
        style="margin-left:100px;float:left"
        title="
this element is out of document flow;
margin-left, first, ignore without (margin and float) elements, then, relative to the previous float element border, if there isn't one, it will be in the new line;
other position relative to the previous element that isn't out of document flow"
      ></div>
      <div class="relative float" style="float:left"></div>
      <div class="relative"></div>
      <div
        class="relative"
        style="z-index:17;margin-top:10%;margin-left:10%;"
        title="
z-index working;
margin top percent relative to the width of the containing block, other percent relative to the containing block size correspondingly"
      ></div>
      <div
        class="relative"
        style="top:10%;"
        title="
top percent relative to the height of containing block"
      ></div>
      <div
        class="relative"
        style="width:auto;margin-top:100px;"
        title="
if parent element has size style, its width equals parent width"
      ></div>
      <div class="relative float" style="float:left"></div>
    </div>
    <!-- --- -->
    <div id="absolute">
      <div class="absolute"></div>
      <div
        class="absolute float"
        style="margin-left:200px;float:left"
        title="
margin-left relative to the containing block boder;
other position relative to the containing block border;
absolute element is out of document flow and float not working here"
      ></div>
      <div class="absolute float" style="margin-left:400px;float:left"></div>
      <div
        class="absolute"
        style="z-index:17;margin-top:10%;"
        title="
z-index working;
margin top percent relative to the width of containing block, other percent relative to the containing block size correspondingly"
      ></div>
      <div class="absolute" style="top:50%;"></div>
      <div class="absolute" style="width:auto;top:400px;" title="
width auto not working">&nbsp;</div>
    </div>
    <!-- --- -->
    <div id="fixed">
      <div
        class="fixed"
        style="color:#ff530f;top:50%;left:calc(100% - 120px);z-index:17;"
        title="
z-index working;
margin not working;
position relative to the application window border;
percent relative to the application window size correspondingly"
      ></div>
    </div>
    <!-- --- -->
    <div id="mixed">
      <div class="mixed" style></div>
      <div class="mixed" style="float:left;margin-top:5%;margin-left:10%;"></div>
      <div class="mixed" style="float:left;"></div>
      <div class="mixed" style="position:relative;"></div>
      <div class="mixed" style="position:relative;float:left;"></div>
      <div class="mixed" style="position:relative;float:left;"></div>
    </div>
  </div>
</template>

<script>
export default {
  mounted: function() {
    var i = 1;
    var array = $.merge($("#static div"), $("#relative div"));
    array = $.merge(array, $("#absolute div"));
    array = $.merge(array, $("#fixed div"));
    array = $.merge(array, $("#mixed div"));
    $(array).each(function() {
      var style =
        $(this).attr("style") == null ? "default" : $(this).attr("style");
      var title = $(this).attr("title") == null ? "" : $(this).attr("title");
      var value = style + "\n\n" + title;
      $(this).attr("title", i + "\n" + value);
      i++;
    });
  }
};
</script>


<style>
.static {
  background-color: #c0c1fc;
  margin: 2px;
  width: 120px;
  height: 50px;
}

#static .float {
  background-color: #fdc8c8;
}

.relative {
  background-color: #8e90fc;
  position: relative;
  margin: 2px;
  width: 120px;
  height: 50px;
}

#relative .float {
  background-color: #fc9595;
}

.absolute {
  background-color: #4245ff;
  position: absolute;
  margin: 2px;
  width: 120px;
  height: 50px;
}

#absolute .float {
  background-color: #ff5959;
}

.fixed {
  background-color: #2819fd;
  position: fixed;
  margin: 2px;
  width: 120px;
  height: 50px;
}

.mixed {
  background-color: #161aff;
  margin: 2px;
  width: 120px;
  height: 50px;
}

#mixed .float {
  background-color: #ff2424;
}

#css_position {
  position: absolute;
  background-color: #dbdbdb;
  min-width: 1900px;
  height: 2800px;
  font-size: 5px;
  word-wrap: break-word;
}

#static,
#relative,
#absolute,
#fixed,
#mixed {
  position: absolute;
  width: 100%;
  height: 20%;
  overflow-y: scroll;
}

#static {
  background-color: #ffffff;
  top: 0%;
}

#relative {
  background-color: #c4c4c4;
  top: 20%;
}

#absolute {
  background-color: #8d8d8d;
  top: 40%;
}

#fixed {
  background-color: #636262;
  top: 60%;
}

#mixed {
  background-color: #222222;
  top: 80%;
}
</style>

