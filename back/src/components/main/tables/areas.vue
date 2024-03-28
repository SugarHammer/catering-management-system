<template>
  <div>
    <div class="sub" v-if="haveAuthToOperate">
      <el-row class="btnsBar" :gutter="15">
        <el-col :span="6">
          <div class="grid-content bg-purple">
            <el-button size="small" type="primary" plain @click="dialogFormAddVisible = true" class="addBtn">新增</el-button>
          </div>
        </el-col>
      </el-row>
    </div>

    <div class="diningArea tableBox">
      <el-table ref="multipleTable"
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
        <el-table-column prop="id"
                         label="ID">
          <!-- <template slot-scope="scope">{{ scope.row.date }}</template> -->
        </el-table-column>
        <el-table-column prop="diningAreaCoding"
                         label="区域编码">
        </el-table-column>
        <el-table-column prop="diningAreaName"
                         label="区域名称"></el-table-column>

        <el-table-column v-if="haveAuthToOperate" label="操作">
          <template slot-scope="scope">
            <el-button type="success" size="small" plain class="edit" id="editBtn" @click="edit(scope.row)">编辑</el-button>
            <el-button type="danger" size="small" plain class="del" id="delBtn" @click="del(scope.row.id)">删除</el-button>
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
    <!-- 新增弹出框 -->
    <div>
      <el-dialog title="添加餐桌区域"
                 :visible.sync="dialogFormAddVisible"
                 width="600px" :append-to-body="true">
        <el-form :model="addForm">
          <el-form-item label="区域编码" :label-width="formLabelWidth">
            <el-input size="small" v-model="addForm.diningAreaCoding" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="区域名称" :label-width="formLabelWidth">
            <el-input size="small" v-model="addForm.diningAreaName" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button size="small" @click="dialogFormAddVisible = false">取 消</el-button>
          <el-button size="small" type="primary" @click="addArea">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <!--                修改弹出框-->
    <div>
      <el-dialog title="修改餐桌区域"
                 :visible.sync="dialogFormEditVisible"
                 width="600px" :append-to-body="true">
        <el-form :model="editForm">
          <el-form-item label="区域ID" :label-width="formLabelWidth">
            <el-input size="small" v-model="editForm.id" autocomplete="off" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="区域编码" :label-width="formLabelWidth">
            <el-input size="small" v-model="editForm.diningAreaCoding" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="区域名称" :label-width="formLabelWidth">
            <el-input size="small" v-model="editForm.diningAreaName" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
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
      input1: '',
      input2: '',
      total: 0,
      currentPage: 1, //默认显示页数
      tableData: [],
      addForm: {
        diningAreaCoding: '',
        diningAreaName: '',

      },
      editForm: {
        id: '',
        diningAreaCoding: '',
        diningAreaName: '',
      },
      formLabelWidth: '120px',
      dialogFormAddVisible: false,
      dialogFormEditVisible: false,
    };
  },
  created() {
    this.getAreaList();
    //   this.total=this.tableData.length
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
    getAreaList() {
      this.$axios
          .get(this.$api.tables.dingingAreaList, {params: {page: this.currentPage}})
          .then((res) => {
            if (res.data.code == 200) {
              console.log(res.data);
              this.tableData = res.data.data;
              this.total = res.data.count;
            }

          })
          .catch((err) => {
            console.log(err);
          });
    },
    addArea() {
      let data = {diningAreaCoding: this.addForm.diningAreaCoding, diningAreaName: this.addForm.diningAreaName}
      console.log(data);
      this.$axios.post(this.$api.tables.addDiningArea, data).then(res => {
        if (res.data.code == 200) {
          console.log(res.data);
          this.getAreaList();
          this.dialogFormAddVisible = false
          this.$message({
            type: 'success',
            message: '新增成功!'
          });
        }
      })
      this.addForm = {};

    },
    edit(row) {
      this.dialogFormEditVisible = true;
      this.editForm.id = row.id;
      this.editForm.diningAreaCoding = row.diningAreaCoding;
      this.editForm.diningAreaName = row.diningAreaName;

    },
    save() {
      let data = {
        id: this.editForm.id,
        diningAreaCoding: this.editForm.diningAreaCoding,
        diningAreaName: this.editForm.diningAreaName
      }
      this.$axios.post(this.$api.tables.editDiningAreaById, data).then(res => {
        if (res.data.code == 200) {
          this.getAreaList();
          this.$message({
            type: 'success',
            message: '修改成功!'
          });
        }

      })
      this.dialogFormEditVisible = false;

    },
    del(id) {
      this.$confirm('此操作将永久删除该条数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let data = {id: id};
        // console.log(data);
        this.$axios
            .post(this.$api.tables.delDiningArea, this.$qs.stringify(data))
            .then((res) => {
              // console.log(res.data);
              this.getAreaList();
            });
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
      })
    },
    //当前页码
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getAreaList();
    },
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
</style>

