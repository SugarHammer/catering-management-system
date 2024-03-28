<template>
  <div>
    <div>
      <div class="sub">
        <el-row class="btnsBar" :gutter="20">
          <el-col :span="6">
            <div class="grid-content bg-purple">
              <el-button size="small" type="danger" plain class="addBtn" @click="delAll" :disabled="dis">批量删除</el-button>
            </div>
          </el-col>
        </el-row>
      </div>
    </div>
    <div class="tableBox">
      <template>
        <el-table
           @selection-change="handleSelectionChange"
            :data="jurisData"
            stripe
            style="width: 100%"
            :header-cell-style="{
                textAlign: 'center',
                background: 'orange',
                color:'white'}"
            :cell-style="{ textAlign: 'center' }">

          <el-table-column
              type="selection"
              width="50">
          </el-table-column>
          <el-table-column
              prop="rolename"
              label="管理员">
          </el-table-column>
          <el-table-column
              prop="process"
              label="进程">
          </el-table-column>
          <el-table-column
              label="操作">
            <template slot-scope="scope">
              <el-button size="small" @click="del(scope.row)" class="del" id="delBtn">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <!-- 分页区域-->
        <el-pagination
            background
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page.sync="queryInfo.pagenum"
            :page-size="queryInfo.pagesize"
            layout="prev, pager, next"
            :total="total"
            class="paginationBar">
        </el-pagination>
        <div class="clear"></div>
      </template>
    </div>
    <!-- 删除提示框 -->
    <el-dialog title="提示" :visible.sync="delVisible" width="300px" center :append-to-body="true">
      <div class="del-dialog-cnt">删除不可恢复，是否确定删除？</div>
      <span slot="footer" class="dialog-footer">
      <el-button @click="delVisible = false">取 消</el-button>
      <el-button type="primary" @click="deleteRow" >确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "systemLog",
  data() {
    return {
      dis:true,
      delVisible:false,//删除提示弹框的状态
            msg:"",//记录每一条的信息，便于取id
            delarr:[],//存放删除的数据
            multipleSelection:[],//多选的数据
            jurisData:[
        {id:0,rolename: '超级管理员', process: '修改权限'},
        {id:1,rolename: '管理员', process: '删除角色'},
        {id:2,rolename: '管理员', process: '增加角色'}
      ],
      queryInfo: {
        query: '',
        // 当前的页数
        pagenum: 1,
        // 当前每页显示多少条数据
        pagesize: 4
      },
      // 总页数
      total: 0,
    }
  },
  methods: {

    // 监听pagesize改变的事件
    handleSizeChange(newSize) {
      console.log(newSize)
      this.queryInfo.pagesize = newSize
    },
    // 监听页码值改变的事件
    handleCurrentChange(newPage) {
      console.log(newPage)
      this.queryInfo.pagenum = newPage
    },
    del(row) {
      var issure = false
      this.$confirm('请确认是否删除?', '提示', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: "warning",
      }).then(() => {
        this.$message({
          type: 'success',
          message: '删除成功!'
        });
        this.jurisData.splice(row,1)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });



    },

//操作多选

    handleSelectionChange(val) {
      this.multipleSelection = val;
      let isdisable = this.multipleSelection.length
      if(isdisable>0){
        this.dis = false
      }else {
        this.dis = true
      }
      console.log(this.multipleSelection)

    },
    // 批量删除

delAll() {
  this.delVisible = true;//显示删除弹框
  const length = this.multipleSelection.length;
  for (let i = 0; i < length; i++) {
    console.log(length)

  }

},
    deleteRow(){
      this.delVisible = false
      for(let i = 0;i<this.multipleSelection.length;i++){
        this.delarr[i] = this.multipleSelection[i]

      }
      console.log(this.delarr[0].id)
      console.log(this.jurisData[0].id)
     for(var i = 0;i < this.jurisData.length;i++){
       for (var j = 0;j < this.delarr.length;j++){
         if(this.jurisData[i].id == this.delarr[j].id){
           this.jurisData.splice(i,1)
         }
       }
     }



      this.$message.success('删除成功')
    }
  }
}
</script>

<style scoped>
@import "./../../../styles/commonStyles.css";
</style>
