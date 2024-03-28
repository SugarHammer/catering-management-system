<template>
  <div class="tableBox">
    消费菜品排行
    <div id="myChart" :style="{width: '100%', height: '500px'}"></div>
  </div>

</template>

<script>
  export default {
    name: "businessReport",
    data(){
      return{
        edata:[]
      }
    },
    mounted(){
      this.drawLine();
    },
    methods: {
      drawLine(){
        let xdata=[]
        let ydata=[]
       this.$axios.get(this.$api.report.ranking).then((res)=>{
         let result = res.data
         if(result.code===200){
           xdata = res.data.deshesNamesList
           ydata = res.data.soldNumList
         }
       })
        let myChart = this.$echarts.init(document.getElementById('myChart'))
        // 绘制图表
        let option;
        setTimeout(function () {
          option = {
            color: 'orange',
            legend:{

            },
            tooltip:{
              trigger:'axis',
              axisPointer:{
                type:'shadow'
              }
            },
            grid: {
              left: '3%',
              right: '4%',
              bottom: '3%',
              containLabel: true
            },
            xAxis: {
              type: 'category',
              data: xdata,
              axisTick:{
                alignWithLabel:true
              }
            },
            yAxis: {
              type: 'value'
            },
            series: [{
              data: ydata,
              type: 'bar',
              barWidth:'40%'
            }]
          };
          myChart.setOption(option);
        },500);
      }
    }
  }
</script>

<style scoped>
@import './../../../styles/commonStyles.css';
</style>
