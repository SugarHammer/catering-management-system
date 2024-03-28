<template>
  <div>
    <div>
      <el-row class="btnsBar" :gutter="15">
        <!--        添加按钮-->
        <el-col :span="6">
          <div class="grid-content bg-purple">
            <el-button v-if="haveAuthToOperate" size="small" plain type="primary" @click="dialogFormVisible=true" class="addBtn">添加</el-button>
          </div>
        </el-col>
        <!--            搜索-->
        <el-col :span="4">
          <el-input size="small" v-model="input1" placeholder="请输入套菜品码"></el-input>
        </el-col>
        <el-col :span="4">
          <el-input size="small" v-model="input2" placeholder="请输入套菜名称"></el-input>
        </el-col>
        <el-col :span="3">
          <el-button size="small" type="primary" class="searchBtn" @click="search">搜索</el-button>
        </el-col>
      </el-row>

      <!--       新增弹出框-->
      <el-dialog title="添加套餐" :visible.sync="dialogFormVisible" :append-to-body="true" width="600px">
        <el-form :model="addData">
          <el-form-item label="品码" :label-width="formLabelWidth">
            <el-input size="small" v-model="addData.comboNo" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="套菜名称" :label-width="formLabelWidth">
            <el-input size="small" v-model="addData.comboName" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button size="small" @click="dialogFormVisible = false">取 消</el-button>
          <el-button size="small" type="primary" @click="add">确 定</el-button>
        </div>
      </el-dialog>

      <div>
        <!--       修改 弹出框-->
        <el-dialog title="修改套餐" :visible.sync="dialogFormEditVisible" :append-to-body="true" width="600px">
          <el-form :model="editData">
            <el-form-item label="品码" :label-width="formLabelWidth">
              <el-input size="small" v-model="editData.comboNo" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="套菜名称" :label-width="formLabelWidth">
              <el-input size="small" v-model="editData.comboName" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="售价" :label-width="formLabelWidth">
              <el-input size="small" v-model="editData.comboPrice" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button size="small" @click="dialogFormEditVisible = false">取 消</el-button>
            <el-button size="small" type="primary" @click="save">确 定</el-button>
          </div>
        </el-dialog>
      </div>
    </div>


    <!--        套餐详情弹出框-->
    <!-- Table -->
    <el-dialog title="套餐详情" :visible.sync="dialogComboVisible" :append-to-body="true" width="55%">
      <el-table :data="comboData" :cell-style="{textAlign:'center'}" :header-cell-style="{
                 textAlign: 'center'}">
        <el-table-column property="id" label="行号"></el-table-column>
        <el-table-column property="dishesNo" label="品码"></el-table-column>
        <el-table-column property="dishesName" label="品名"></el-table-column>
        <el-table-column property="dishesBulk" label="规格" :formatter="getBig"></el-table-column>
        <el-table-column property="unitName" label="销售单位"></el-table-column>
        <el-table-column property="dishesComboNumber" label="配置数量"></el-table-column>
        <el-table-column property="dishesPrice" label="售价"></el-table-column>
        <el-table-column v-if="haveAuthToOperate" label="操作" width="200px">
          <template slot-scope="scope">
            <el-button type="success" size="small" id="editNumBtn" @click="editComboDishes(scope.row)">编辑</el-button>
            <el-button type="danger" size="small" id="delComboDishesBtn" @click="delComboDishes(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-form v-if="haveAuthToOperate">
        <el-form-item label="选择新增菜品">
          <el-select size="small" v-model="dishesValue">
            <el-option v-for="item in dishesData" :label="item.dishesName" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="dialogComboVisible = false">关 闭</el-button>
        <el-button size="small" type="primary" @click="addDishes">确 定</el-button>
      </div>
    </el-dialog>

    <!--        修改套餐菜品数量-->
    <!--       修改弹出框-->
    <el-dialog title="修改菜品数量" :visible.sync="dialogEditDishesVisible" :append-to-body="true" width="600px">
      <el-form :model="EditDishesData">
        <el-form-item label="配置数量" :label-width="formLabelWidth">
          <el-input size="small" v-model="EditDishesData.dishesComboNumber" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button size="small" @click="dialogEditDishesVisible = false">取 消</el-button>
        <el-button size="small" type="primary" @click="addComboDishes">确 定</el-button>
      </div>
    </el-dialog>

    <!--                图片详情弹出框-->
    <div>
      <el-dialog title="图片详情" :visible.sync="dialogImgVisible" :append-to-body="true">
        <div class="imgShow">
          <el-image
                  style="width: 350px;height: 250px"
                  :src='img+url'>
          </el-image>
          <form action="" method="post" enctype="multipart/form-data" ref="form1">
            <input name="file" type="file">
          </form>
        </div>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogImgVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitImg">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <!--   表格table-->
    <div class="tableBox">
      <el-table
          stripe
          ref="multipleTable"
          :data="tableData"
          tooltip-effect="dark"
          :header-cell-style="{
                 textAlign: 'center',
                 background: 'orange',
                 color:'white'}"
          :cell-style="{textAlign:'center'}"
          style="width: 100%;">
        <el-table-column type="selection">
        </el-table-column>
        <el-table-column
            prop="id"
            label="行号">
        </el-table-column>
        <el-table-column
            prop="comboNo"
            label="品码">
        </el-table-column>
        <el-table-column
            prop="comboName"
            label="套菜名称">
        </el-table-column>
        <el-table-column label="图片" width="110px">
          <template slot-scope="scope">
            <el-button size="small" @click="pictureBtn(scope.row)"  id="wacthImg">图片详情</el-button>
          </template>
        </el-table-column>
        <el-table-column
            prop="unit.unitName"
            label="销售单位">
        </el-table-column>
        <el-table-column
            prop="comboPrice"
            label="售价">
        </el-table-column>
        <el-table-column label="套餐详情">
          <template slot-scope="scope">
            <el-button id="editComboBtn" size="small" @click="comboBtn(scope.row.id)">套餐详情</el-button>
          </template>
          <!--            editCombo(scope.row)-->
        </el-table-column>
        <el-table-column v-if="haveAuthToOperate" label="操作" width="220px">
          <template slot-scope="scope">
            <el-button type="success" size="small" id="editBtn" @click="edit(scope.row)">编辑</el-button>
            <el-button type="danger" size="small" id="delBtn" @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
          background
          layout="prev, pager, next"
          @current-change="handleCurrentChange"
          :total="total"
          class="paginationBar">
      </el-pagination>
      <div class="clear"></div>
    </div>
  </div>
</template>

<script>
export default {
  name: "combo",
  data() {
    return {
      currentPage: 1,
      total: 0,
      dishesValue: '',
      tableData: [],
      input1: '',
      input2: '',
      comboId: '',
      url: '',
      img: '/api/',
      formLabelWidth: '120px',
      dialogFormVisible: false,
      dialogFormEditVisible: false,
      dialogComboVisible: false,
      dialogEditDishesVisible: false,
      dialogImgVisible:false,
      addData: {
        comboNo: '',
        comboName: '',
        comboPrice: ''
      },
      editData: {
        id: '',
        comboNo: '',
        comboName: '',
        comboPrice: ''
      },
      EditDishesData: {
        id: '',
        dishesComboNumber: ''
      },
      comboData: [],
      dishesData: []
    }
  },
  created() {
    this.getList();
  },
  methods: {
    submitImg() {
      let form = this.$refs.form1
      let formData = new FormData(form)
      formData.append("id", this.comboId)
      this.$axios.post(this.$api.dishes.uploadDishesComboPhoto, formData, {
        headers: {
          'content-type': 'multipart/formdata'
        }
      }).then(res => {
        let result = res.data
        console.log(result)
        this.url = result.data;
        this.getList()
      })
    },
    //套餐图片详情按钮
    pictureBtn(row) {
      this.dialogImgVisible = true
      this.comboId = row.id
      if (row.comboPhoto == null) {
        this.url = ''
      } else {
        this.url = row.comboPhoto
      }
    },
    getList() {
      this.$axios.get(this.$api.dishes.comboSelectAll+"?limit=10&index=" + this.currentPage).then(res => {
        let result = res.data
        // console.log(result)
        if (result.code === 200) {
          this.tableData = result.data
          this.total = result.count
        }
      }).catch(err => {
        console.log(err);
      })
    },
    // 添加
    add() {
      let data = {
        comboNo: this.addData.comboNo,
        comboName: this.addData.comboName,
      }
      this.$axios.post(this.$api.dishes.comboInsert, data).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.getList();
          this.addData = {};
        }
        this.$message({
          type: 'success',
          message: '添加成功!'
        })
        this.dialogFormVisible = false;
      }).catch(err => {
        console.log(err);
      })
    },
    //根据id删除
    del(id) {
      // console.log(id);
      this.$confirm('请确认是否删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let data = {id: id}
        this.$axios.post(this.$api.dishes.comboDeleteById, this.$qs.stringify(data)).then(res => {
          let result = res.data
          if (result.code === 200) {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getList();
          }
        })
      })
    },
    edit(row) {
      this.dialogFormEditVisible = true
      this.editData.id = row.id
      this.editData.comboNo = row.comboNo
      this.editData.comboName = row.comboName
      this.editData.comboPrice = row.comboPrice
    },
    //保存修改数据
    save() {
      let data = {
        id: this.editData.id,
        comboNo: this.editData.comboNo,
        comboName: this.editData.comboName,
        comboPrice: this.editData.comboPrice,
      }
      // console.log(data);
      this.$axios.post(this.$api.dishes.comboUpdateById, data).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.getList();
        }
        this.$message({
          type: 'success',
          message: '修改成功!'
        })
        this.dialogFormEditVisible = false;
      }).catch(err => {
        console.log(err);
      })
    },
    //搜索
    search() {
      this.$axios.get(this.$api.dishes.comboSelectAll+'?comboNo=' + this.input1 + '&comboName=' + this.input2 + "&limit=10&index=" + this.currentPage).then(res => {
        let result = res.data
        // console.log(result)
        if (result.code === 200) {
          this.tableData = result.data;
          this.total = result.count
        }
      })
    },
    //获取套餐菜品内容
    getComboList(id) {
      this.comboId = id;
      this.$axios.get(this.$api.dishes.dishesSelectByCombo+"?id=" + id).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.comboData = result.data
          console.log(this.comboData)
        }
      }).catch(err => {
        console.log(err);
      })

      //发起请求，获取菜品信息 渲染到select下拉框
      this.$axios.get(this.$api.dishes.dishesSelectByNotCombo+"?id=" + id).then(res => {
        let result = res.data
        // console.log(result)
        if (result.code === 200) {
          this.dishesData = result.data
        }
      }).catch(err => {
        console.log(err);
      })
    },
    // 套餐详情按钮
    comboBtn(id) {
      this.dialogComboVisible = true
      this.getComboList(id);
    },
    getBig(row) {
      if (row.dishesBulk == 0) {
        return '小份'
      } else if (row.dishesBulk == 1) {
        return '中份'
      } else {
        return '大份'
      }
    },
    //点击确定按钮
    addDishes() {
      this.dialogComboVisible = true
      //发起请求 向套餐添加菜品
      let data = {
        comboId: this.comboId,
        dishesId: this.dishesValue
      }
      this.$axios.post(this.$api.dishes.dishesComboInsert, data).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.getComboList(this.comboId)
          this.dishesValue = ''
        }
      }).catch(err => {
        console.log(err);
      })
    },
    //删除套餐里的菜品
    delComboDishes(id) {
      console.log(id);
      this.$confirm('请确认是否删除?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let data = {
          dishesId: id,
          comboId: this.comboId
        }
        this.$axios.post(this.$api.dishes.dishesComboDeleteByDishesIdAndComboId, this.$qs.stringify(data)).then(res => {
          let result = res.data
          // console.log(result)
          if (result.code === 200) {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            this.getComboList(this.comboId)
          }
        })
      })
    },
    //修改套餐菜品数量
    editComboDishes(row) {
      this.dialogEditDishesVisible = true
      this.EditDishesData.id = row.id
      this.EditDishesData.dishesComboNumber = row.dishesComboNumber
    },
    //向后台发起菜品数量编辑请求
    addComboDishes() {
      let data = {
        dishesId: this.EditDishesData.id,
        comboId: this.comboId,
        dishesComboNumber: this.EditDishesData.dishesComboNumber
      }
      this.$axios.post(this.$api.dishes.dishesComboUpdateNumberByDishesIdAndComboId, data).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.dialogEditDishesVisible = false
          this.getComboList(this.comboId)
        }
      }).catch(err => {
        console.log(err)
      })
    },
    //当前页码
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getList();
    }
  },
  computed: {
    haveAuthToOperate() {
      return localStorage.getItem('loginRoleId')==='3'||localStorage.getItem('loginRoleId')==='2'
    }
  }
}
</script>

<style scoped>
.el-form-item {
  margin-top: 20px;
}
  .el-image {
    margin-left: 250px;
  }
  form input {
    margin-left: 250px;
  }
</style>
