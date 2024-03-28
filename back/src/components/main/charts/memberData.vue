<template>
  <div>
    <el-row class="btnsBar" :gutter="15">
      <el-col :span="6">
        <el-date-picker
            size="small"
            v-model="values"
            type="month"
            placeholder="选择月"
            value-format='yyyy-MM'
            class="addBtn">
        </el-date-picker>
      </el-col>
      <el-col :span="3">
        <el-button type="primary" size="small" @click="aa">查询</el-button>
      </el-col>
    </el-row>

    <div class="tableBox">
      <div id="myChart2" :style="{width: '100%', height: '500px'}"></div>
    </div>
  </div>
</template>

<script>
export default {
  data(){
    return{
      values:'2021-09'
    }
  },
  name: "memberData",

    mounted(){
    this.drawLine();
  },
  methods:{
     aa:function () {
        this.drawLine()
      // console.log(document.getElementById('v').innerHTML)
       console.log(this.values)
     },
    drawLine() {
        let xdata = []
        let ydata = []
        //会员
        this.$axios({
            url: this.$api.report.RAVip+"?yearAndMouth="+this.values,
            method: 'get',
        }).then((res) => {

            ydata = res.data.registerNumList;
            xdata = res.data.dateList

         //   console.log(res.data)
            //console.log(this.edata)
        }).catch((err) => {
            console.log(err)
        })
//订单  营业额


        // 基于准备好的dom，初始化echarts实例
        // 绘制图表
        let option;

        let myChart2 = this.$echarts.init(document.getElementById('myChart2'))
        setTimeout(function () {


        option = {
            title: [{
                left: 'center',
                text: '会员增量',
                 top:'20px'
            }],
            xAxis: {
                type: 'category',
                data:xdata,
            },
            yAxis: {
                type: 'value'
            },
            series: [
                {
                    data: ydata,
                    color: '#91CC75',
                    type: 'bar',
                    showBackground: true,
                    backgroundStyle: {
                        color: 'rgba(180, 180, 180, 0.2)'
                    }
                }
            ]
        };

        option && myChart2.setOption(option);
        },500)
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
