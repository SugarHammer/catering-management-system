<template>
  <div class="dishesWay">
    <div class="tabs">
      <el-tabs
          :tab-position="tabPosition"
          style="height: 100%;"
          v-model="activeName"
          @tab-click="handleclick"
          ref="tabs">
        <el-tab-pane v-for="recipe in recipeTypes" :label=recipe :name=recipe></el-tab-pane>
<!--        <el-tab-pane label="蔬菜做法" name="蔬菜做法"></el-tab-pane>-->
<!--        <el-tab-pane label="海鲜做法" name="海鲜做法"></el-tab-pane>-->
<!--        <el-tab-pane label="民俗要求" name="民俗要求"></el-tab-pane>-->
<!--        <el-tab-pane label="口味要求" name="口味要求"></el-tab-pane>-->
<!--        <el-tab-pane label="蒸" name="蒸"></el-tab-pane>-->
<!--        <el-tab-pane label="焗" name="焗"></el-tab-pane>-->
<!--        <el-tab-pane label="炒" name="炒"></el-tab-pane>-->
      </el-tabs>
      <div class="tableInfo">
        <vegtableWay :activeName="activeName" :recipeTypes="recipeTypes"></vegtableWay>
      </div>
    </div>
  </div>

</template>

<script>

import vegtableWay from "./parcticesCompoents/vegtableWay";

export default {
  data() {
    return {
      recipeTypes: [],
      tabPosition: 'left',
      activeName: '全部',
      tabIndex: 0
    };
  },
  components: {
    vegtableWay
  },
  created() {
    this.getServerRequest(this.$api.dishes.recipeSelectAll+'?index=1&limit=10').then((res) => {
      this.recipeTypes = [...new Set(
          res.data.data.map(function (item) {
            return item.recipeType
          })
      )]
      this.recipeTypes.push("全部")
      this.recipeTypes.reverse()
    })
  },
  methods: {
    handleclick(tab) {
      this.activeName = tab.name
      this.tabIndex = tab.index
    }
  },
  watch: {
    tabIndex: function (val) {
      let lineEl = document.getElementsByClassName('el-tabs__active-bar')[0];
      setTimeout(function () {
        lineEl.style.transform = 'translateY('+ val*50 + 'px)';
        // console.log(lineEl.style.transform);
        // console.log(lineEl);
      })
    }
  }
};
</script>

<style scoped>
>>> .el-tabs__item {
  margin-right: 15px;
  margin-bottom: 10px;
  background-color: white;
  font-weight: bold;
}

>>> .el-tabs__item:hover {
  /*margin: 15px;*/
  background-color: #ff9900;
  color: white;
}

.dishesWay div {
  float: left;
}

>>> .dishesWay {
  width: 100%;
  overflow: hidden;
}

.tabs {
  float: left;
  width: 100%;
  height: 100%;
}

.el-tabs {
  width: 12%;
}

.tableInfo {
  width: 88%;
}

/deep/.el-tabs--left .el-tabs__item.is-left {
  text-align: center !important;
}
</style>
