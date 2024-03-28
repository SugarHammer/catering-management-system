/**
* 订单管理
*
* @author ChenPing
* @since 2021-09-13
* @version 1.0
*/
<template>
  <!--  订单管理-->
  <div>
    <!--    新增和查询-->
    <div class="sub">
      <el-row class="btnsBar" :gutter="15">
        <el-col :span="6">
          <div class="grid-content bg-purple">
            <el-button v-if="haveAuthToOperate" size="small" type="primary" plain @click="AddOrderVisible = true" class="addBtn">添加</el-button>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple">
            <el-input size="small" v-model="input" placeholder="请输入用户名" @input="orderNoChange"></el-input>
          </div>
        </el-col>
        <el-col :span="2">
          <div class="grid-content bg-purple">
            <el-button size="small" type="primary" @click="search">搜索</el-button>
          </div>
        </el-col>
      </el-row>
    </div>
    <!--表格详细信息-->
    <div class="tableBox">
      <el-table
          stripe
          ref="multipleTable"
          :data="OrdersData"
          tooltip-effect="dark"
          style="width: 100%"
          :header-cell-style="{
                    textAlign: 'center',
                    background: 'orange',
                    color:'white'}"
          :cell-style="{ textAlign: 'center' }"
          @selection-change="handleSelectionChange">
        <el-table-column
            type="selection">
        </el-table-column>
        <el-table-column
            width="50"
            prop="id"
            label="id">
        </el-table-column>
<!--        <el-table-column-->
<!--            prop="mumberName"-->
<!--            label="会员名">-->
<!--        </el-table-column>-->
        <el-table-column
            width="300"
            prop="ordersNo"
            label="订单编号">
        </el-table-column>
<!--        <el-table-column-->
<!--            prop="diningNumber"-->
<!--            label="餐桌号">-->
<!--        </el-table-column>-->
        <el-table-column
            prop="createDate"
            label="下单时间">
        </el-table-column>
        <el-table-column
            prop="payDate"
            label="付款时间">
        </el-table-column>
        <el-table-column
            prop="totalPrice"
            label="订单总价">
        </el-table-column>
<!--        <el-table-column-->
<!--            prop="discount"-->
<!--            label="积分优惠">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            width="104"-->
<!--            prop="info"-->
<!--            label="订单备注"-->
<!--            show-overflow-tooltip>-->
<!--        </el-table-column>-->
        <el-table-column
            width="220"
            label="操作">
          <template slot-scope="scope">
            <el-button size="small" type="primary" plain @click="details(scope.row.id)" class="Details" id="detailBtn">
              详情
            </el-button>
            <el-button v-if="haveAuthToOperate" size="small" type="success" @click="edit(scope.row)" class="edit" id="editBtn">修改</el-button>
            <el-button v-if="haveAuthToOperate" size="small" type="danger" @click="del(scope.row.id)" class="del" id="delBtn">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          background
          layout="prev, pager, next"
          :total="pageCount"
          @current-change="handleCurrentChange"
          class="paginationBar">
      </el-pagination>
      <div class="clear"></div>
    </div>

    <!--    添加订单弹框    -->
    <el-dialog title="添加订单" :visible.sync="AddOrderVisible" :append-to-body="true" width="600px">
      <el-form :label-position="labelPosition" label-width="100px" :model="AddOrderForm">
        <el-form-item label="用户名">
          <el-input size="small" v-model="AddOrderForm.mumber_id"></el-input>
        </el-form-item>
        <el-form-item label="餐桌号">
          <el-select size="small" v-model="AddOrderForm.Table_no">
            <el-option v-for="item in FreeDiningData" :label="item.diningNumber"
                       :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="订单备注">
          <el-input size="small" v-model="AddOrderForm.info"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="AddOrderVisible = false">取消</el-button>
        <el-button size="small" type="primary" @click="AddOrder">添加</el-button>
      </div>
    </el-dialog>
    <!--        修改订单弹框      -->
    <el-dialog title="修改订单" :visible.sync="EditFormVisible" :append-to-body="true" width="600px">
      <el-form :label-position="labelPosition" label-width="100px" :model="EditForm">
        <el-form-item label="订单id">
          <el-input size="small" v-model="EditForm.id" readonly="readonly"></el-input>
        </el-form-item>
        <el-form-item label="餐桌号">
          <el-select size="small" v-model="EditForm.diningId" placeholder="请选择餐桌号">
            <el-option v-for="item in DiningData" :label="item.diningNumber"
                       :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="订单备注">
          <el-input size="small" v-model="EditForm.info"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="EditFormVisible=false">取 消</el-button>
        <el-button size="small" type="primary" @click="UpdateEdit">更新</el-button>
      </div>
    </el-dialog>
    <!--    查看订单详情弹框-->
    <el-dialog title="菜品详情" :visible.sync="DetailsVisible" :append-to-body="true">
      <div class="grid-content bg-purple left">
<!--        <el-button v-if="haveAuthToOperate" type="primary" size="small" plain @click="AddDetailVisible=true" class="addBtn"-->
<!--                   style="margin-bottom: 15px">添加菜品/套餐-->
<!--        </el-button>-->
      </div>
      <el-table
          :data="dishesData"
          ref="multipleTable"
          tooltip-effect="dark"
          :header-cell-style="{
                    textAlign: 'center',
                    background: 'orange',
                    color:'white'}"
          :cell-style="{ textAlign: 'center' }">
        <el-table-column
            property="dishesId"
            label="id"
            width="100">
        </el-table-column>
        <el-table-column
            property="dishesName"
            label="名称">
        </el-table-column>
        <el-table-column
            property="dishesPrice"
            label="单价">
        </el-table-column>
        <el-table-column
            property="dishesNumber"
            label="数量">
        </el-table-column>
<!--        <el-table-column-->
<!--            v-if="haveAuthToOperate"-->
<!--            label="操作">-->
<!--          <template slot-scope="scope">-->
<!--            <el-button size="small" type="success" @click="editDish(scope.row)" class="editDish editBtn">修改</el-button>-->
<!--            <el-button size="small" type="danger" @click="delDish(scope.row.id)" class="delDish delBtn">删除</el-button>-->
<!--          </template>-->
<!--        </el-table-column>-->
      </el-table>

<!--      <el-table-->
<!--          :data="comboData"-->
<!--          ref="multipleTable"-->
<!--          tooltip-effect="dark"-->
<!--          :header-cell-style="{-->
<!--                    textAlign: 'center',-->
<!--                    background: 'orange',-->
<!--                    color:'white'}"-->
<!--          :cell-style="{ textAlign: 'center' }">-->
<!--        <el-table-column-->
<!--            property="id"-->
<!--            label="套餐id"-->
<!--            width="100">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            property="comboName"-->
<!--            label="套餐名称">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            property="comboPrice"-->
<!--            label="单价">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            property="comboNum"-->
<!--            label="数量">-->
<!--        </el-table-column>-->
<!--        <el-table-column-->
<!--            v-if="haveAuthToOperate"-->
<!--            label="操作">-->
<!--          <template slot-scope="scope">-->
<!--            <el-button size="small" type="success" @click="editCom(scope.row)" class="editCom editBtn">修改</el-button>-->
<!--            <el-button size="small" type="danger" @click="delCom(scope.row.id)" class="delCom delBtn">删除</el-button>-->
<!--          </template>-->
<!--        </el-table-column>-->
<!--      </el-table>-->
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="DetailsVisible = false">关 闭</el-button>
      </div>
    </el-dialog>
    <!--    添加菜品/套餐弹框    -->
    <el-dialog title="添加菜品/套餐" :visible.sync="AddDetailVisible" :append-to-body="true" width="600px">
      <el-form :label-position="labelPosition" label-width="130px" :model="AddDetailForm">
        <el-form-item label="菜品 / 套餐名称">
          <el-select size="small" v-model="AddDetailForm.CT_id">
            <el-option label="炝炒时蔬" value="0"></el-option>
            <el-option label="蒜蓉时蔬" value="1"></el-option>
            <el-option label="A套餐" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="菜品 / 套餐数量">
          <el-input size="small" v-model="AddDetailForm.number"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="AddDetailVisible = false">取消</el-button>
        <el-button size="small" type="primary" @click="AddDetail">确定</el-button>
      </div>
    </el-dialog>
    <!--     修改套餐-->
    <el-dialog title="修改套餐" :visible.sync="EditComboVisible" :append-to-body="true" width="600px">
      <el-form :label-position="labelPosition" label-width="120px" :model="EditComboForm">
        <el-form-item label="套餐id">
          <el-input size="small" v-model="EditComboForm.id" disabled></el-input>
        </el-form-item>
        <el-form-item label="数量">
          <el-input size="small" v-model="EditComboForm.number"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="EditComboVisible=false">取 消</el-button>
        <el-button size="small" type="primary" @click="UpdateCombo">更新</el-button>
      </div>
    </el-dialog>
    <!--     修改菜品-->
    <el-dialog title="修改菜品" :visible.sync="EditDishesVisible" :append-to-body="true" width="600px">
      <el-form :label-position="labelPosition" label-width="100px" :model="EditDishForm">
        <el-form-item label="菜品id">
          <el-input size="small" v-model="EditDishForm.id" disabled></el-input>
        </el-form-item>
        <el-form-item label="数量">
          <el-input size="small" v-model="EditDishForm.number"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="EditDishesVisible=false">取 消</el-button>
        <el-button size="small" type="primary" @click="UpdateDishes">更新</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "orders",
  data() {
    return {
      //所有数据
      input: '',
      total: 0,
      FreeDiningData: [],
      DiningData: [],
      OrdersData: [],
      comboData: [],
      dishesData: [],
      ids: '',
      pageCount: 0,
      currentPage: 1,
      multipleSelection: [],
      labelPosition: 'right',
      AddOrderVisible: false,
      EditFormVisible: false,
      DetailsVisible: false,
      AddDetailVisible: false,
      EditComboVisible: false,
      EditDishesVisible: false,
      AddOrderForm: {
        id: '',
        diningNumber: '',
        info: ''
      },
      EditForm: {
        id: '',
        diningId: '',
        info: ''
      },
      EditComboForm: {
        id: '',
        number: '',
      },
      EditDishForm: {
        id: '',
        number: '',
      },
      AddDetailForm: {},
      selectedOrderId: ''
    }
  },
  created: function () {
    this.getOrderList();
    this.getFreeDining();
  },
  methods: {
    orderNoChange() {
      this.$forceUpdate();
    },
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
      console.log(this.multipleSelection)
      console.log(val);
      let arr = this.multipleSelection.map(string => {
        return string.id;
      })
      this.ids = arr.join(',');
      console.log(this.ids)
    },
    //请求餐桌号
    getDining: function () {
      this.$axios.get(this.$api.orders.TableNo).then((res) => {
        let result = res.data;
        // console.log(result)
        if (result.code == 200) {
          this.DiningData = result.data;
          console.log(this.DiningData)
        } else {
          this.$alert(result.msg);
        }
      })
    },
    //请求空闲餐桌号
    getFreeDining: function () {
      let countpage;
      this.$axios.get(this.$api.orders.FreeTableNo, {params: {page: 1}}).then((res) => {
        let result = res.data;
        // console.log(result)
        if (result.code == 200) {
          countpage = Math.ceil(result.count / 10);
        } else {
          this.$alert(result.msg);
        }
        for (let i = 1; i <= countpage; i++) {
          this.$axios.get(this.$api.orders.FreeTableNo, {params: {page: i, diningAction: 0}}).then((res) => {
            let result = res.data;
            if (result.code == 200) {
              this.FreeDiningData = this.FreeDiningData.concat(result.data);
              console.log(this.FreeDiningData)
            } else {
              this.$alert(result.msg);
            }
          })
        }
      })
    },
    //获取订单数据列表
    getOrderList: function () {
      //发起服务器去请求数据
      this.$axios.get(this.$api.orders.GetOrders, {params: {page: this.currentPage}}).then((res) => {
        let result = res.data;
        // console.log(result)
        if (result.code == 200) {
          this.OrdersData = result.data;
          this.pageCount = res.data.count;
          // console.log(this.OrdersData)
        } else {
          this.$alert(result.msg);
        }
      })
    },
    //一、订单首页功能按钮
    // 1.确认添加订单
    AddOrder: function () {
      this.getFreeDining();
      let data = {
        mumber_id: this.AddOrderForm.mumber_id,
        Table_no: this.AddOrderForm.Table_no,
        info: this.AddOrderForm.info,
      }
      // console.log(data);
      this.$axios.post(this.$api.orders.AddOrder, this.$qs.stringify(data))
          .then(res => {
            let result = res.data;
            if (result.code == 200) {
              this.getOrderList();
              this.OrdersData = '';
            }
            this.$message({
              type: "success",
              message: '订单添加成功',
            })
            this.AddOrderVisible = false;
          })
    },
    //2.打开修改弹框,获取当前订单信息
    edit: function (obj) {
      console.log(obj);
      this.getDining();
      let diningId = -1;
      this.OrdersData.forEach(function (item) {
        if (item.id === obj.id) {
          console.log(item)
          diningId = item.diningId;
        }
      })
      console.log(diningId)
      this.EditFormVisible = true;
      this.EditForm.id = obj.id;
      this.EditForm.diningId = diningId;
      this.EditForm.info = obj.info;
    },
    //3. 确认修改订单
    UpdateEdit: function () {
      let data = {
        id: this.EditForm.id,
        diningid: this.EditForm.diningId,
        info: this.EditForm.info,
      }
      console.log(data);
      this.$axios.post(this.$api.orders.Edit, this.$qs.stringify(data))
          .then(res => {
            let result = res.data;
            // console.log(result);
            if (result.code == 200) {
              this.getOrderList();
            }
            this.$message({
              type: 'success',
              message: '修改成功！',
            })
            this.EditFormVisible = false;
          }).catch(err => {
        console.log(err);
      })
    },
    //4.删除订单，获取订单id，进行删除
    del: function (id) {
      // console.log(id);
      this.$confirm('请确认是否删除? ', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let data = {id: id}
        this.$axios.post(this.$api.orders.Del, this.$qs.stringify(data))
            .then(res => {
              let result = res.data;
              if (result.code == 200) {
                this.OrdersData = result.data;
                this.getOrderList();
              } else {
                this.$alert(result.msg);
              }
            })
      })
    },
    // 5.查询订单，根据会员名模糊查询，input框边输入边查询
    search: function () {
      let data = {
        name: this.input,
      }
      this.$axios.get(this.$api.orders.GetOrders, {params: {page: 1, name: this.input}})
          .then((res) => {
            let result = res.data;
            // console.log(result);
            if (result.code == 200) {
              this.OrdersData = result.data
            } else {
              this.$alert(result.msg);
            }
          });
    },
    // 6.点击打开根据订单id获取数据列表并加载
    details: function (id) {
      this.DetailsVisible = true;
      this.selectedOrderId = id;
      //发起服务器去请求数据
      this.$axios.get(this.$api.orders.GetDetails, {params: {OrdersID: id}})
          .then((res) => {
            let result = res.data;
            console.log(result)
            if (result.code === 200) {
              this.dishesData = result.data;
              for (let i=0;i<this.dishesData.length;i++) {
                this.dishesData[i].dishesPrice = this.dishesData[i].dishesCount/this.dishesData[i].dishesNumber
              }
            } else {
              this.$alert(result.msg);
            }
          })
    },
    //二、订单项功能按钮
    //1.添加菜品、套餐、数量
    AddDetail: function () {
    },
    //2.修改数量
    //2.1.1打开菜品修改弹框,获取当前订单信息
    editDish: function (obj) {
      console.log(obj);
      this.EditDishesVisible = true
      this.EditDishForm.id = obj.id;
      this.EditDishForm.number = obj.dishesNum;
    },
    //2.1.2菜品修改确认
    UpdateDishes: function () {
      let data = {
        id: this.EditDishForm.id,
        num: this.EditDishForm.number,
      }
      console.log(data);
      this.$axios.post(this.$api.orders.EditDish, this.$qs.stringify(data))
          .then(res => {
            let result = res.data;
            // console.log(result);
            if (result.code == 200) {
              //更新详情对话框
              this.details(this.selectedOrderId);
            }
            this.$message({
              type: 'success',
              message: '修改成功！',
            })
            this.EditDishesVisible = false;
          }).catch(err => {
        console.log(err);
      })
    },
    //2.2.1打开套餐修改弹框,获取当前订单信息
    editCom: function (obj) {
      console.log(obj);
      this.EditComboVisible = true
      this.EditComboForm.id = obj.id;
      this.EditComboForm.number = obj.comboNum;
    },
    //2.2.2套餐修改确认
    UpdateCombo: function () {
      let data = {
        id: this.EditComboForm.id,
        num: this.EditComboForm.number,
      }
      console.log(data);
      this.$axios.post(this.$api.orders.EditCombo, this.$qs.stringify(data))
          .then(res => {
            let result = res.data;
            // console.log(result);
            if (result.code == 200) {
              //更新详情对话框
              this.details(this.selectedOrderId);
            }
            this.$message({
              type: 'success',
              message: '修改成功！',
            })
            this.EditComboVisible = false;
          }).catch(err => {
        console.log(err);
      })
    },
    //3.1删除菜品
    delDish: function (id) {
      // console.log(id);
      this.$confirm('请确认是否删除? ', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let data = {
          id: id
        }
        this.$axios.post(this.$api.orders.DelDish, this.$qs.stringify(data))
            .then(res => {
              let result = res.data;
              if (result.code == 200) {
                this.dishesData = result.data;
                this.details(this.selectedOrderId);
              } else {
                this.$alert(result.msg);
              }
            })
      })
    },
    //3.2删除套餐
    delCom: function (id) {
      // console.log(id);
      this.$confirm('请确认是否删除? ', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let data = {
          id: id
        }
        this.$axios.post(this.$api.orders.DelCombo, this.$qs.stringify(data))
            .then(res => {
              let result = res.data;
              if (result.code == 200) {
                this.comboData = result.data;
                this.details(this.selectedOrderId);
              } else {
                this.$alert(result.msg);
              }
            })
      })
    },
    //分页
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getOrderList();
    },
    //批量删除
    DelAny: function () {
      this.$confirm('请确认是否删除? ', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios.post(this.$api.orders.DelAny, {params: {ids: this.ids}})
            .then(res => {
              let result = res.data;
              console.log(result)
              if (result.code == 200) {
                this.OrdersData = result.data;
                this.getOrderList();
              } else {
                this.$alert(result.msg);
              }
            })
      })
    }
  },
  computed: {
    haveAuthToOperate() {
      return localStorage.getItem('loginRoleId')==='3' || localStorage.getItem('loginRoleId')==='2'
    }
  }
}
</script>

<style scoped>
@import "./../../../styles/commonStyles.css";
</style>

