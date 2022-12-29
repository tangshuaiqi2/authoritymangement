<template>

    
    <div class="app-container">

        <el-dialog title="添加/修改" :visible.sync="dialogVisible" width="40%" >
      <el-form ref="dataForm" :model="sysRole" label-width="150px" size="small" style="padding-right: 40px;">
        <el-form-item label="角色名称">
          <el-input v-model="sysRole.roleName"/>
        </el-form-item>
        <el-form-item label="角色编码">
          <el-input v-model="sysRole.roleCode"/>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false" size="small" icon="el-icon-refresh-right">取 消</el-button>
        <el-button type="primary" icon="el-icon-check" @click="saveOrUpdate()" size="small">确 定</el-button>
      </span>
</el-dialog>
<div class="tools-div">
  <el-button type="success" icon="el-icon-plus" size="mini" @click="add">添 加</el-button>
</div>
<!-- 表格 -->
        <el-table
        v-loading="listLoading"
        :data="list"
        stripe
        border
        style="width: 100%;margin-top: 10px;"
        >
        
            <el-table-column
                label="序号"
                width="70"
                align="center">
                <template slot-scope="scope">
                {{ (page - 1) * limit + scope.$index + 1 }}
                </template>
            </el-table-column>

            <el-table-column prop="roleName" label="角色名称" />
            <el-table-column prop="roleCode" label="角色编码" />
            <el-table-column prop="createTime" label="创建时间" width="160"/>
            <el-table-column label="操作" width="200" align="center">
                <template slot-scope="scope">
                <el-button type="primary" icon="el-icon-edit" size="mini" @click="edit(scope.row.id)" title="修改"/>
                <el-button type="danger" icon="el-icon-delete" size="mini" @click="removeDataById(scope.row.id)" title="删除"/>
                </template>
            </el-table-column>
        </el-table>

        

        <!-- 分页组件 -->
        <el-pagination
            :current-page="page"
            :total="total"
            :page-size="limit"
            style="padding: 30px 0; text-align: center;"
            layout="total, prev, pager, next, jumper"
            @current-change="fetchData"
        />
    </div>
</template>

<script>

import api from '@/api/system/role';

export default{
    data(){
        return{
            listLoading:true,
            list:[],
            total:0,
            page:1,
            limit:3,
            searchObj:{},//条件查询封装的对象
            sysRole:{},
            dialogVisible: false,
            multipleSelection: [],
        }
    },

    created(){
        this.fetchData(1);
    },

    methods:{
    //     handleSelectionChange(selection) {
    //     console.log(selection)
    //     this.multipleSelection = selection
    //   },
      // 批量删除
    //   batchRemove() {
    //     if (this.multipleSelection.length === 0) {
    //       this.$message.warning('请选择要删除的记录！')
    //       return
    //     }
    //     this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
    //       confirmButtonText: '确定',
    //       cancelButtonText: '取消',
    //       type: 'warning'
    //     }).then(() => {
    //       // 点击确定，远程调用ajax
    //       // 遍历selection，将id取出放入id列表
    //       var idList = []
    //       this.multipleSelection.forEach(item => {
    //         idList.push(item.id)
    //       })
    //       // 调用api
    //       return api.batchRemove(idList)
    //     }).then((response) => {
    //       this.fetchData()
    //       this.$message.success(response.message)
    //     }).catch(error => {
    //       if (error === 'cancel') {
    //         this.$message.info('取消删除')
    //       }
    //     })
    //   },
        edit(id) {
            this.dialogVisible = true
            api.getById(id).then(response => {
                this.sysRole = response.data
            })
        }  ,
        update() {
        api.updateById(this.sysRole).then(response => {
            this.$message.success(response.message || '操作成功')
            this.dialogVisible = false
            this.fetchData(this.page)
        })
        },
          //弹出添加的表单
        add(){
            this.dialogVisible = true
            this.sysRole = {}
        },
        //添加或更新
        saveOrUpdate() {
        if (!this.sysRole.id) {
            this.save()
        } else {
            this.update()
        }
        },
        

        //添加
        save() {
        api.save(this.sysRole).then(response => {
            this.$message.success(response.message || '操作成功')
            this.dialogVisible = false
            this.fetchData(this.page)
        })
        },

        removeDataById(id) {
         // debugger
            this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => { // promise
                // 点击确定，远程调用ajax
                return api.removeById(id)
            }).then((response) => {
                this.fetchData(this.page)
                this.$message.success(response.message || '删除成功')
            }).catch(() => {
                this.$message.info('取消删除')
            })
        },

        resetData() {
            console.log('重置查询表单')
            this.searchObj = {}
            this.fetchData()
        },

        // pageNum
        fetchData(pageNum){
            this.page = pageNum;

            api.getPageList(this.page, this.limit, this.searchObj)
            .then(response => {
                // console.log(response)
                this.listLoading = false;
                this.list = response.data.records
                this.total = response.data.total
            });
        }
    }
}
</script>

<style scoped>

</style>