<template>
  <div>
    <div>
      <el-row class="btnsBar" :gutter="15">
        <el-col :span="6">
          <el-date-picker
              size="small"
              v-model="values"
              type="month"
              placeholder="选择分析月份"
              value-format='yyyy-MM'
              class="addBtn">
          </el-date-picker>
        </el-col>
        <el-col :span="3">
          <el-button type="primary" size="small" @click="aa">查询</el-button>
        </el-col>
      </el-row>

    </div>
    <el-row class="tableBox">
      <el-col :span="12">
        <div id="myChart" :style="{width: '100%', height: '450px',top: '50px'}"></div>
      </el-col>
      <el-col :span="12">
        <div id="myChart2" :style="{width: '100%', height: '450px',top: '50px'}"></div>
      </el-col>
    </el-row>
  </div>

</template>

<script>
export default {
  name: "businessReport",
  data() {
    return {
      values: '2022-03',
      edata: []
    }
  },
  mounted() {
    this.drawLine();
  },
  methods: {
    aa: function () {
      // console.log(this.values)
      this.drawLine()
    },
    drawLine() {
      let dateList
      let soldNumList
      let soldSumList


      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById('myChart'))
      let myChart2 = this.$echarts.init(document.getElementById('myChart2'))
      // 绘制图表

      this.$axios({
        url: this.$api.report.businessReport + "?yearAndMouth=" + this.values,
        method: 'get',
      }).then((res) => {

        console.log("----res.soldNumList-----", res.data.soldNumList)
        console.log("----res-----", res)
        dateList = res.data.dateList
        soldNumList = res.data.soldNumList
        soldSumList = res.data.soldSumList
        myChart.setOption({
          // Make gradient line here
          visualMap: [{
            show: false,
            type: 'continuous',
            seriesIndex: 0,
            min: 0,
            max: 200
          }],
          title: [{
            left: 'center',
            text: '订单量',
            // bottom:'20px'
          }],
          tooltip: {
            trigger: 'axis'
          },
          xAxis: [{
            data: dateList
          }],
          yAxis: [{
            gridIndex: 0
          }],
          grid: [{
            bottom: '35%'
          }],
          series: [{
            type: 'line',
            showSymbol: false,
            data: soldNumList,
            itemStyle: {
              normal: {
                lineStyle: {
                  color: '#ff9900'
                }
              }
            },
          }]
        })
        myChart2.setOption({
          // Make gradient line here
          visualMap: [{
            show: false,
            type: 'continuous',
            seriesIndex: 0,
            min: 0,
            max: 200
          }],
          title: [{
            left: 'center',
            text: '销售额',
            // bottom:'20px'
          }],
          tooltip: {
            trigger: 'axis'
          },
          xAxis: [{
            data: dateList
          }],
          yAxis: [{
            gridIndex: 0
          }],
          grid: [{
            bottom: '35%'
          }],
          series: [{
            type: 'line',
            showSymbol: false,
            data: soldSumList,
            itemStyle: {
              normal: {
                lineStyle: {
                  color: '#ff9900'
                }
              }
            },
          }]
        })

        //console.log(dateList)
      }).catch((err) => {
        console.log(err)
      })


      // 绘制图表
    }


  }

}
</script>

<style scoped>
@import './../../../styles/commonStyles.css';

.el-date-editor.el-input, .el-date-editor.el-input__inner {
  width: 90%;
}
</style>
