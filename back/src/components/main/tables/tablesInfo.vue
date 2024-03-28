<template>
  <div>
    <div class="sub">
      <el-row class="btnsBar" :gutter="15">
        <el-col :span="6" v-if="haveAuthToOperate">
          <div class="grid-content bg-purple">
            <el-button type="primary" size="small" plain @click="addBtn" class="addBtn">新增</el-button>
            <el-button type="primary" size="small" plain @click="addMultipleBtn" class="addBtn">批量新增</el-button>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple">
            <el-select size="small" v-model="typeSelect" placeholder="请选择餐桌类型" style="width:100%">
              <el-option label="全部" value="全部"></el-option>
              <el-option label="大厅" value="大厅"></el-option>
              <el-option label="单间" value="单间"></el-option>
              <el-option label="包间" value="包间"></el-option>
            </el-select>
          </div>
        </el-col>
        <el-col :span="3">
          <div class="grid-content bg-purple">
            <el-input size="small" v-model="numberInput" placeholder="请输入餐桌编号"></el-input>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple">
            <el-select size="small" v-model="actionSelect" placeholder="请选择餐桌状态" style="width:100%">
              <el-option label="全部" value="全部"></el-option>
              <el-option label="空闲" value="0"></el-option>
              <el-option label="用餐" value="1"></el-option>
              <el-option label="待打扫" value="2"></el-option>
            </el-select>
          </div>
        </el-col>
        <el-col :span="4">
          <div class="grid-content bg-purple">
            <el-select size="small" v-model="areaValue" placeholder="请选择餐桌区域" style="width:100%">
              <el-option label="全部" value="全部"></el-option>
              <el-option v-for="item in searchValue" :key="item.id" :label="item.diningAreaName"
                         :value="item.id"></el-option>
            </el-select>
          </div>
        </el-col>
        <el-col :span="2">
          <div class="grid-content bg-purple">
            <el-button size="small" type="primary" @click="search">搜索</el-button>
          </div>
        </el-col>
      </el-row>
    </div>

    <div class="dining tableBox">
      <el-table
          stripe
          ref="multipleTable"
          :data="tableData"
          tooltip-effect="dark"
          style="width: 100%"
          @selection-change="handleSelectionChange"
          :header-cell-style="{
          textAlign: 'center',
          background: 'orange',
          color: 'white',
        }"
          :cell-style="{ textAlign: 'center' }">
        <el-table-column type="selection"></el-table-column>
        <el-table-column prop="id" label="ID">
          <!-- <template slot-scope="scope">{{ scope.row.date }}</template> -->
        </el-table-column>
        <el-table-column prop="diningType" label="餐桌类型"></el-table-column>
        <el-table-column prop="diningNumber" label="餐桌编号"></el-table-column>
        <el-table-column prop="diningCustomers" label="人数"></el-table-column>
        <el-table-column prop="diningAction" label="餐桌状态" :formatter="action"></el-table-column>
        <el-table-column prop="diningArea.diningAreaName" label="餐桌区域"></el-table-column>

        <el-table-column label="操作" v-if="haveAuthToOperate">
          <template slot-scope="scope">
            <el-button type="success" size="small" plain id="editBtn" @click="edit(scope.row)">编辑</el-button>
            <el-button type="danger" size="small" plain id="delBtn" @click="del(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination background
                     layout="prev, pager, next"
                     @current-change="handleCurrentChange"
                     :total="total"
                     class="paginationBar">
      </el-pagination>
      <div class="clear"></div>
    </div>
    <!-- 添加弹出框 -->
    <div>
      <el-dialog title="添加餐桌"
                 :visible.sync="dialogFormAddVisible"
                 width="600px" :append-to-body="true">
        <el-form :model="addForm">
          <el-form-item label="餐桌类型" :label-width="formLabelWidth">
            <el-select size="small" v-model="addForm.diningType" placeholder="请选择餐桌类型" style="width:100%">
              <el-option label="大厅" value="大厅"></el-option>
              <el-option label="单间" value="单间"></el-option>
              <el-option label="包间" value="包间"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="餐桌编号" :label-width="formLabelWidth">
            <el-input size="small" v-model="addForm.diningNumber" autocomplete="off" placeholder="请输入餐桌编号"></el-input>
          </el-form-item>
          <el-form-item label="人数" :label-width="formLabelWidth">
            <el-input size="small" v-model="addForm.diningCustomers" autocomplete="off" placeholder="请输入人数"></el-input>
          </el-form-item>
          <el-form-item label="餐桌区域" :label-width="formLabelWidth">
            <el-select size="small" v-model="addValue" placeholder="请选择餐桌区域" style="width:100%">
              <el-option v-for="item in addForm.diningArea" :key="item.id" :label="item.diningAreaName"
                         :value="item.id"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer"
             class="dialog-footer">
          <el-button size="small" @click="dialogFormAddVisible = false">取 消</el-button>
          <el-button size="small" type="primary" @click="addDining()">确 定
          </el-button>
        </div>
      </el-dialog>
    </div>
    <!-- 批量新增弹出框 -->
    <div class="mutiple">
      <el-dialog title="批量新增"
                 :visible.sync="dialogMultipleVisible"
                 width="600px" :append-to-body="true">
        <el-row :gutter="30">
          <el-col :span="10" :offset="4">
            <div class="grid-content bg-purple">
              <el-button size="small" @click="downLoadTemplate">下载模板</el-button>
            </div>
          </el-col>
          <el-col :span="10">
            <div class="grid-content bg-purple">
              <form action="" ref="upload" name="dining">
<!--                <input type="file" name="file">-->
                <el-upload
                    class="upload-demo"
                    :action="this.$api.tables.diningUpload"
                    multiple
                    :on-preview="handlePreview"
                    :file-list="fileList"
                    :on-success="uploadSuccess">
                  <el-button size="small" type="primary">上传文件</el-button>
                </el-upload>
              </form>
            </div>
          </el-col>
        </el-row>
        <div slot="footer"
             class="dialog-footer">
          <el-button size="small" @click="dialogMultipleVisible=false">取 消</el-button>
          <el-button size="small" type="primary" @click="dialogMultipleVisible=false">确定</el-button>
        </div>
      </el-dialog>
    </div>
    <!--                修改弹出框-->
    <div>
      <el-dialog title="修改餐桌"
                 :visible.sync="dialogFormEditVisible"
                 width="600px" :append-to-body="true">
        <el-form :model="editForm">
          <el-form-item label="餐桌ID" :label-width="formLabelWidth">
            <el-input size="small" v-model="editForm.id" autocomplete="off" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="餐桌类型" :label-width="formLabelWidth">
            <el-select size="small" v-model="editForm.diningType" placeholder="请选择餐桌类型" style="width:100%">
              <el-option label="大厅" value="大厅"></el-option>
              <el-option label="单间" value="单间"></el-option>
              <el-option label="包间" value="包间"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="餐桌编号" :label-width="formLabelWidth">
            <el-input size="small" v-model="editForm.diningNumber" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="人数" :label-width="formLabelWidth">
            <el-input size="small" v-model="editForm.diningCustomers" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="餐桌状态" :label-width="formLabelWidth">
            <el-select size="small" v-model="editForm.diningAction" placeholder="请选择餐桌状态" style="width:100%">
              <el-option label="空闲" value="0"></el-option>
              <el-option label="用餐" value="1"></el-option>
              <el-option label="待打扫" value="2"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="餐桌区域" :label-width="formLabelWidth">
            <el-select size="small" v-model="editValue" placeholder="请选择餐桌区域" style="width:100%">
              <el-option v-for="item in editForm.diningArea" :key="item.id" :label="item.diningAreaName"
                         :value="item.id"></el-option>
            </el-select>
          </el-form-item>
        </el-form>
        <div slot="footer"
             class="dialog-footer">
          <el-button size="small" @click="dialogFormEditVisible = false">取 消</el-button>
          <el-button size="small" type="primary" @click="save">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>

export default {
  name: 'tables',
  data() {
    return {
      typeSelect: '',
      numberInput: '',
      actionSelect: '',
      areaValue: '',
      searchValue: [],
      total: 0,//数据总数
      currentPage: 1, //默认显示页数
      tableData: [],
      addValue: '',
      editValue: 1,
      isClick: -1,

      addForm: {
        diningType: '',
        diningNumber: '',
        diningCustomers: '',
        // diningAreaId:''
        diningArea: [],

      },
      editForm: {
        id: '',
        diningType: '',
        diningNumber: '',
        diningCustomers: '',
        diningAction: '0',
        diningArea: [],
      },
      formLabelWidth: '120px',
      dialogFormAddVisible: false,
      dialogMultipleVisible: false,
      dialogFormEditVisible: false,
      multipleSelection: [],
      fileList: []
    };
  },
  created() {
    this.getDiningList();
  },
  methods: {
    toggleSelection(rows) {
      if (rows) {
        rows.forEach((row) => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val;
    },
    getDiningList() {
      this.isClick = 1;
      this.$axios.get(this.$api.tables.dingingAreaAll).then(res => {
        // console.log(res.data);
        this.searchValue = res.data.data
      })

      this.$axios.get(this.$api.tables.diningListByPage, {params: {page: this.currentPage}})
          .then((res) => {
            if (res.data.code == 200) {
              this.tableData = res.data.data;
              console.log(res.data);
              this.total = res.data.count;
            }

          })
          .catch((err) => {
            console.log(err);
          });

    },
    addDining() {
      let data = {
        diningType: this.addForm.diningType, diningNumber: this.addForm.diningNumber,
        diningCustomers: this.addForm.diningCustomers, diningAreaId: this.addValue
      }
      console.log(data);
      this.$axios.post(this.$api.tables.addDining, data).then(res => {
        if (res.data.code == 200) {
          console.log(res.data);
          this.getDiningList();
          this.$message({
            type: 'success',
            message: '新增成功!'
          });
        }
      }).catch((err) => {
        console.log(err);
      });

      this.addForm = {};
      this.dialogFormAddVisible = false
    },
    addMultipleBtn() {
      this.dialogMultipleVisible = true;
    },
    // 下载餐桌信息模板
    downLoadTemplate() {
      console.log(111);
      window.open(this.$api.tables.diningDownloadTemplate,"_self", "excel0fAccount")
    },
    // // 上传文件
    // upload() {
    //   let diningUpload = this.$refs.upload;
    //   // console.log(diningUpload)
    //   let form = new FormData(diningUpload);
    //   // console.log(form.get('file'))
    //   this.$axios.post(this.$api.tables.diningUpload,form,{headers:{
    //       'content-type': 'multipart/formdata'
    //     }}).then((res)=> {
    //     console.log(res)
    //       if (res.status===200) {
    //         this.$message.success('上传成功')
    //         this.dialogMultipleVisible = false
    //         this.getDiningList()
    //       } else {
    //         this.$message.warning('上传失败')
    //       }
    //   })
    // },
    edit(row) {
      this.$axios.get(this.$api.tables.dingingAreaAll).then(res => {
        // console.log(res.data.data);
        this.editForm.diningArea = res.data.data
      })
      this.dialogFormEditVisible = true;
      this.editForm.id = row.id;
      this.editForm.diningType = row.diningType;
      this.editForm.diningNumber = row.diningNumber;
      this.editForm.diningCustomers = row.diningCustomers;
      this.editForm.diningAction = row.diningAction.toString();
      console.log(row.diningAction);
      this.editValue = row.diningAreaId

    },
    save() {
      let data = {
        id: this.editForm.id, diningType: this.editForm.diningType,
        diningNumber: this.editForm.diningNumber, diningCustomers: this.editForm.diningCustomers,
        diningAction: this.editForm.diningAction, diningAreaId: this.editValue
      }
      //  console.log(data);
      this.$axios.post(this.$api.tables.editDiningById, data).then(res => {
        if (res.data.code == 200) {
          this.getDiningList();
          this.$message({
            type: 'success',
            message: '修改成功!'
          });

        }
      }).catch((err) => {
        console.log(err);
      });
      this.dialogFormEditVisible = false;

    },
    del(id) {
      this.$confirm('此操作将永久删除该条数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let data = {id: id};
        // console.log(id);
        this.$axios
            .post(this.$api.tables.delDiningById, this.$qs.stringify(data))
            .then((res) => {
              console.log(res.data);
              this.getDiningList();
            });
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });

    },
    addBtn() {
      this.dialogFormAddVisible = true;
      this.$axios.get(this.$api.tables.dingingAreaAll).then(res => {
        if (res.data.code == 200) {
          console.log(res.data.data);
          this.addForm.diningArea = res.data.data
        }
      })
    },
    search() {
      this.isClick = -1;
      this.$axios.get(this.$api.tables.searchDiningList, {
            params: {
              diningType: this.typeSelect,
              diningNumber: this.numberInput, diningAction: this.actionSelect,
              diningAreaId: this.areaValue, page: this.currentPage
            }
          }
      )
          .then(res => {
            console.log(res.data);
            if (res.data.code == 200) {
              this.tableData = res.data.data;
              this.total = res.data.count;
              this.$message({
                type: 'success',
                message: '查询成功!'
              });
            }
          })
    },

    //当前页码
    handleCurrentChange(val) {
      this.currentPage = val;
      if (this.isClick == -1) {
        this.search();
      } else {
        this.getDiningList()
      }
    },
    action(row) {
      if (row.diningAction == 0) {
        return '空闲'
      } else if (row.diningAction == 1) {
        return '用餐'
      } else {
        return '待打扫'
      }
    },
    handlePreview(file) {
      console.log(file);
    },
    uploadSuccess() {
      this.$message.success('上传成功');
      this.getDiningList();
    }
  },
  computed: {
    haveAuthToOperate() {
      return localStorage.getItem('loginRoleId')==='3' || localStorage.getItem('loginRoleId')==='2'
    }
  }
};
</script>

<style scoped>
@import "./../../../styles/commonStyles.css";


/* 页码背景颜色 */
::v-deep .el-pagination.is-background .el-pager li:not(.disabled).active {
  background-color: orange;
}
</style>

