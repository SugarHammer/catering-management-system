<template>
  <div>
    <div>
      <el-row class="btnsBar" :gutter="15">
        <!--        添加按钮-->
        <el-col :span="4" v-if="haveAuthToOperate">
          <el-button size="small" plain type="primary" @click="addBtn" class="addBtn">添加</el-button>
          <el-button type="primary" size="small" plain @click="dialogMultipleVisible=true" class="addBtn">批量添加</el-button>
        </el-col>
        <!--            下载数据-->
        <el-col :span="2">
          <el-button v-if="haveAuthToDownload" size="small" @click="updataBtn" class="addBtn">下载数据</el-button>
        </el-col>

        <!--            停用选择框-->
        <el-col :span="6">
          <div>
            <el-checkbox v-model="checked" @change="showStopDishes">显示停用菜品</el-checkbox>
          </div>
        </el-col>
        <!--            搜索-->
        <el-col :span="4">
          <el-input size="small" v-model="input" placeholder="请输入菜品编码、名称、拼音简码"></el-input>
        </el-col>
        <el-col :span="3">
          <el-button size="small" type="primary" class="searchBtn" @click="search">搜索</el-button>
        </el-col>
      </el-row>

      <!--       新增弹出框-->
      <el-dialog title="添加菜品" :visible.sync="dialogFormVisible" :append-to-body="true" width="600px">
        <el-form :model="addData">
          <el-form-item label="品码" :label-width="formLabelWidth">
            <el-input size="small" v-model="addData.dishesNo" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="品名" :label-width="formLabelWidth">
            <el-input size="small" v-model="addData.dishesName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="规格" :label-width="formLabelWidth">
            <el-select size="small" v-model="addData.dishesBulk">
              <el-option label="小份" value="0"></el-option>
              <el-option label="中份" value="1"></el-option>
              <el-option label="大份" value="2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="拼音简码" :label-width="formLabelWidth">
            <el-input size="small" v-model="addData.dishesShortCode" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="所属小类" :label-width="formLabelWidth">
            <el-select size="small" v-model="valueSubName">
              <el-option :label="item.subcategoriesName" :value="item.id" v-for="item in addData.subcategoriesName"
                         :key="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="单位" :label-width="formLabelWidth">
            <el-select size="small" v-model="valueUnit">
              <el-option :label="item.unitName" :value="item.id" v-for="item in addData.unitName"
                         :key="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="售价" :label-width="formLabelWidth">
            <el-input size="small" v-model="addData.dishesPrice" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="状态" :label-width="formLabelWidth">
            <el-select size="small" v-model="addData.dishesAction">
              <el-option label="启用" value="1"></el-option>
              <el-option label="停用" value="0"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button size="small" @click="dialogFormVisible = false">取 消</el-button>
          <el-button size="small" type="primary" @click="add">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <!-- 批量新增弹出框 -->
    <div class="mutiple">
      <el-dialog title="批量新增"
                 :visible.sync="dialogMultipleVisible"
                 width="600px" :append-to-body="true">
        <el-row :gutter="30">
          <!--        下载模板-->
          <el-col :span="10" :offset="4">
            <div class="grid-content bg-purple">
              <el-button size="small" @click="uploadDataAll">下载模板</el-button>
            </div>
          </el-col>
          <!--        批量上传-->
          <el-col :span="10">
            <div class="grid-content bg-purple">
              <form action="" name="uploadData" ref="toData">
                <!--            <el-input type="file" size="small" name="file" class="addBtn"></el-input>-->
                <el-upload
                    class="upload-demo"
                    :action="this.$api.dishes.uploadDishesExcel"
                    multiple
                    :on-preview="handlePreview"
                    :on-success="uploadSuccess"
                    :file-list="fileList">
                  <el-button size="small" type="primary">上传文件</el-button>
                </el-upload>
              </form>
            </div>
          </el-col>
        </el-row>
        <div slot="footer"
             class="dialog-footer">
          <el-button size="small" @click="dialogMultipleVisible=false">取 消</el-button>
          <el-button size="small" type="primary" @click="dialogMultipleVisible=false">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <!--                修改弹出框-->
    <div>
      <el-dialog title="修改菜品" :visible.sync="dialogFormEditVisible" :append-to-body="true" width="600px">
        <el-form :model="editData">
          <el-form-item label="品码" :label-width="formLabelWidth">
            <el-input size="small" v-model="editData.dishesNo" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="品名" :label-width="formLabelWidth">
            <el-input size="small" v-model="editData.dishesName" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="规格" :label-width="formLabelWidth">
            <el-select size="small" v-model="editData.dishesBulk">
              <el-option label="小份" value="0"></el-option>
              <el-option label="中份" value="1"></el-option>
              <el-option label="大份" value="2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="拼音简码" :label-width="formLabelWidth">
            <el-input size="small" v-model="editData.dishesShortCode" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="所属小类" :label-width="formLabelWidth">
            <el-select size="small" v-model="valueSubName">
              <el-option :label="item.subcategoriesName" :value="item.id" v-for="item in editData.subcategoriesName"
                         :key="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="单位" :label-width="formLabelWidth">
            <el-select size="small" v-model="valueUnit">
              <el-option :label="item.unitName" :value="item.id" v-for="item in editData.unitName"
                         :key="item.id"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="售价" :label-width="formLabelWidth">
            <el-input size="small" v-model="editData.dishesPrice" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="状态" :label-width="formLabelWidth">
            <el-select size="small" v-model="editData.dishesAction">
              <el-option label="启用" value="1"></el-option>
              <el-option label="停用" value="0"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button size="small" @click="dialogFormEditVisible = false">取 消</el-button>
          <el-button size="small" type="primary" @click="save">确 定</el-button>
        </div>
      </el-dialog>
    </div>

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
          @cell-mouse-enter="showImg"
          ref="multipleTable"
          :data="tableData"
          @selection-change="handleSelectionChange"
          tooltip-effect="dark"
          :header-cell-style="{
                 textAlign: 'center',
                 background: 'orange',
                 color:'white'}"
          :cell-style="cellStyle"
          style="width: 100%">
        <el-table-column type="selection">
        </el-table-column>
        <el-table-column
            prop="id"
            label="行号">
        </el-table-column>
        <el-table-column
            prop="dishesNo"
            label="品码">
        </el-table-column>
        <el-table-column
            prop="dishesName"
            label="品名">
        </el-table-column>
        <el-table-column label="图片" width="110px">
          <template slot-scope="scope">
            <el-popover
                placement="right-start"
                trigger="hover"
                content="<slot></slot>">
              <el-image :src='img+url' style="width: 150px;height: 150px"></el-image>
              <el-button slot="reference" class="el-button--small" @click="pictureBtn(scope.row)" id="wacthImg">图片详情
              </el-button>
            </el-popover>
            <!--                <el-button size="small" @click="pictureBtn(scope.row)"  id="wacthImg">图片详情</el-button>-->
          </template>
        </el-table-column>

        <el-table-column
            prop="dishesBulk"
            label="规格"
            :formatter="getBig">
        </el-table-column>
        <el-table-column
            prop="dishesShortCode"
            label="拼音简码">
        </el-table-column>
        <el-table-column
            prop="categoriesName"
            label="所属大类">
        </el-table-column>
        <el-table-column
            prop="subcategoriesName"
            label="所属小类">
        </el-table-column>
        <el-table-column
            prop="unitName"
            label="单位">
        </el-table-column>
        <el-table-column
            prop="dishesPrice"
            label="售价">
        </el-table-column>
        <el-table-column
            align="center"
            prop="dishesAction"
            label="状态" :formatter="gettype">
        </el-table-column>

        <el-table-column v-if="haveAuthToOperate" label="操作" width="180px">
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
  name: "dishesInfo",
  data() {
    return {
      currentPage: 1,
      total: 0,
      input: '',
      checked: false,
      subId: '',
      url: '',
      img: '/api/',
      valueSubName: 1,
      valueUnit: 1,
      tableData: [],
      arrId: [],
      multipleSelection: [],
      formLabelWidth: '120px',
      dialogFormVisible: false,
      dialogFormEditVisible: false,
      dialogImgVisible: false,
      addData: {
        dishesNo: '',
        dishesName: '',
        dishesPhoto: '',
        dishesBulk: '',
        dishesShortCode: '',
        subcategoriesName: [],
        unitName: [],
        dishesPrice: '',
        dishesAction: ''
      },
      editData: {
        id: '',
        dishesNo: '',
        dishesName: '',
        dishesPhoto: '',
        dishesBulk: '0',
        dishesShortCode: '',
        subcategoriesName: [],
        unitName: [],
        dishesPrice: '',
        dishesAction: '0'
      },
      dialogMultipleVisible: false,
      fileList: []
    }
  },
  created() {
    this.getList()
    this.showStopDishes()
  },
  methods: {
    handleSelectionChange(val) {
      this.multipleSelection = val
      let arr = this.multipleSelection.map(checkedArr => {
        return checkedArr.id
      })
      this.arrId = arr
    },
    showImg(row) {
      // console.log(row.id);
      this.$axios.get(this.$api.dishes.dishesSelectById + '?id=' + row.id).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.url = result.data.dishesPhoto;
        }
      }).catch(err => {
        // console.log(err);
      })
    },
    submitImg() {
      let form = this.$refs.form1
      let formData = new FormData(form)
      formData.append("id", this.subId)
      this.$axios.post(this.$api.dishes.uploadDishesPhoto, formData, {
        headers: {
          'content-type': 'multipart/formdata'
        }
      }).then(res => {
        let result = res.data
        // console.log(result)
        if (result.code === 200) {
          this.url = result.data;
          this.getList()
        }
      })
    },
    pictureBtn(row) {
      this.dialogImgVisible = true
      this.subId = row.id
      if (row.dishesPhoto == null) {
        this.url = ''
      } else {
        this.url = row.dishesPhoto
      }
      // console.log(this.subId)
    },
    cellStyle(row, column) {
      if (row.row.dishesAction == "启用" && row.column.label == "状态") {
        return {color: '#0DC316'}
      } else if (row.row.dishesAction == "停用" && row.column.label == "状态") {
        return {color: 'red'}
      }
      return {textAlign: 'center'}
    },
    //数据渲染
    getList() {
      this.$axios.get(this.$api.dishes.dishesSelectAll + "?limit=10&index=" + this.currentPage).then(res => {
        let result = res.data
        if (result.code === 200) {
          // for (var i=0;i<result.data.length;i++){
          //     (result.data[i].dishesAction===1)?result.data[i].dishesAction="启用":result.data[i].dishesAction="停用"
          //     if (result.data[i].dishesBulk===0){
          //         result.data[i].dishesBulk="小份"
          //     }
          //     if (result.data[i].dishesBulk===1){
          //         result.data[i].dishesBulk="中份"
          //     }
          //     if (result.data[i].dishesBulk===2) {
          //         result.data[i].dishesBulk="大份"
          //     }
          // }
          this.tableData = result.data
          this.total = result.count
        }
      }).catch(err => {
        console.log(err);
      })
    },
    //调取菜品小类数据
    addBtn() {
      this.dialogFormVisible = true
      this.$axios.get(this.$api.dishes.subcategoriesSelectIdAndName).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.addData.subcategoriesName = result.data
        }
      })
      //调取菜品单位
      this.$axios.get(this.$api.dishes.unitSelectAll + "?limit=10&index=" + this.currentPage).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.addData.unitName = result.data
        }
      })
    },
    // 添加
    add() {
      let data = {
        dishesNo: this.addData.dishesNo,
        dishesName: this.addData.dishesName,
        dishesBulk: this.addData.dishesBulk,
        dishesShortCode: this.addData.dishesShortCode,
        subcategoriesId: this.valueSubName,
        unitId: this.valueUnit,
        dishesPrice: this.addData.dishesPrice,
        dishesAction: this.addData.dishesAction,
      }
      this.$axios.post(this.$api.dishes.dishesInsert, data).then(res => {
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
        this.$axios.post(this.$api.dishes.dishesDeleteById, this.$qs.stringify(data)).then(res => {
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
      this.$axios.get(this.$api.dishes.subcategoriesSelectIdAndName).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.editData.subcategoriesName = result.data
        }
      })
      //调取菜品单位
      this.$axios.get(this.$api.dishes.unitSelectAll + "?limit=10&index=" + this.currentPage).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.editData.unitName = result.data
        }
      })
      this.editData.id = row.id
      this.editData.dishesNo = row.dishesNo
      this.editData.dishesName = row.dishesName
      this.editData.dishesBulk = row.dishesBulk.toString()
      this.editData.dishesShortCode = row.dishesShortCode
      this.editData.dishesPrice = row.dishesPrice
      this.editData.dishesAction = row.dishesAction.toString()
      this.valueUnit = row.unitId
      this.valueSubName = row.subcategoriesId
    },
    //保存修改数据
    save() {
      let data = {
        id: this.editData.id,
        dishesNo: this.editData.dishesNo,
        dishesName: this.editData.dishesName,
        dishesBulk: this.editData.dishesBulk,
        dishesShortCode: this.editData.dishesShortCode,
        subcategoriesId: this.valueSubName,
        unitId: this.valueUnit,
        dishesPrice: this.editData.dishesPrice,
        dishesAction: this.editData.dishesAction,
      }
      this.$axios.post(this.$api.dishes.dishesUpdateById, data).then(res => {
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
      this.$axios.get(this.$api.dishes.dishesSelectAll + '?name=' + this.input + "&limit=10&index=" + this.currentPage).then(res => {
        let result = res.data
        if (result.code === 200) {
          this.tableData = result.data;
          this.total = result.count
        }
      })
    },
    //复选框选中显示停用菜品
    showStopDishes() {
      if (this.checked === true) {
        this.$axios.get(this.$api.dishes.dishesSelectAll + '?dishesAction=0&limit=10&index=' + this.currentPage).then(res => {
          let result = res.data
          // console.log(result)
          if (result.code === 200) {
            this.tableData = result.data
            this.total = result.count
          }
        })
      } else {
        this.getList()
      }
    },
    //渲染状态，小菜数字为名字
    gettype(row) {
      if (row.dishesAction == 1) {
        return '启用'
      } else {
        return '停用'
      }
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
    //当前页码
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getList();
    },
    //点击下载表数据
    updataBtn() {
      let ids = this.arrId.toString()
      window.open(this.$api.dishes.downloadDishesExcel + '?id=' + ids, "_self", "excel0fAccount")
    },
    //点击下载模板
    uploadDataAll() {
      window.open(this.$api.dishes.downloadDishesTemplate, "_self", "excel0fAccount")
    },
    // //点击向后台导入数据表
    // uploadData() {
    //   let form1 = this.$refs.toData
    //   let formdata = new FormData(form1)
    //   this.$axios.post(this.$api.dishes.downloadDownloadTemplate, formdata, {
    //     headers: {
    //       'content-type': 'multipart/formdata'
    //     }
    //   }).then((res)=> {
    //     if (res.status===200) {
    //       this.$message.success('上传成功')
    //       this.dialogMultipleVisible = false
    //       this.getDiningList()
    //     } else {
    //       this.$message.warning('上传失败')
    //     }
    //   })
    // },
    handlePreview(file) {
      console.log(file);
    },
    uploadSuccess() {
      this.$message.success('上传成功');
      this.getList();
    }
  },
  computed: {
    haveAuthToOperate() {
      return localStorage.getItem('loginRoleId')==='3' || localStorage.getItem('loginRoleId')==='2'
    },
    haveAuthToDownload() {
      return localStorage.getItem('loginRoleId')==='5' || localStorage.getItem('loginRoleId')==='2'
    }
  }
}
</script>

<style scoped>
.imgShow {
  margin-left: 250px;
}

/deep/ .el-dialog {
  margin-top: 5vh !important;
}
</style>
