<template>
    <div class="app-container">
        <div class="filter-container">
            <el-input v-model="listQuery.title" :placeholder="$t('table.title')" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
            <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
                {{ $t('table.search') }}
            </el-button>
            <el-button v-permission="['sys:user:create']" class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
                {{ $t('table.add') }}
            </el-button>
            <el-button v-permission="['sys:user:delete']" class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleDelete()">
                {{ $t('table.batchDelete') }}
            </el-button>
        </div>
        <el-table
                :key="tableKey"
                v-loading="listLoading"
                :data="list"
                border
                fit
                highlight-current-row
                style="width: 100%;"
                @sort-change="sortChange"
                @selection-change="handleSelectionChange"
        >
            <el-table-column
                    type="selection"
                    width="55"
            />
            <el-table-column :label="$t('userInfo.id')" align="center" width="100">
                <template slot-scope="scope">
                    <span>{{ scope.row.id }}</span>
                </template>
            </el-table-column>
            <el-table-column :label="$t('userInfo.username')" width="100px" align="center">
                <template slot-scope="scope">
                    <span>{{ scope.row.username }}</span>
                </template>
            </el-table-column>
            <el-table-column :label="$t('userInfo.password')" min-width="200px">
                <template slot-scope="scope">
                    <span>{{ scope.row.password }}</span>
                </template>
            </el-table-column>
            <el-table-column :label="$t('userInfo.status')" width="80px" align="center">
                <template slot-scope="scope">
                    <el-tag :type="scope.row.status | statusFilter">
                        <span>{{ scope.row.status | statusMessageFilter }}</span>
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column :label="$t('userInfo.createTime')" width="150px" align="center">
                <template slot-scope="scope">
                    <span>{{ scope.row.createTime }}</span>
                </template>
            </el-table-column>
            <el-table-column :label="$t('table.actions')" align="center" width="230" class-name="small-padding fixed-width">
                <template slot-scope="{row}">
                    <el-button v-permission="['sys:user:update']" class="filter-item" style="margin-left: 10px;" type="primary" @click="handleUpdate(row)">
                        {{ $t('table.edit') }}
                    </el-button>
                    <el-button v-if="row.userStatus=='0'" v-permission="['sys:user:publish']" class="filter-item" style="margin-left: 10px;" type="success" @click="handleModifyStatus(row,'published')">
                        {{ $t('table.publish') }}
                    </el-button>
                    <el-button v-if="row.userStatus=='1'" v-permission="['sys:user:draft']" class="filter-item" style="margin-left: 10px;" @click="handleModifyStatus(row,'draft')">
                        {{ $t('table.draft') }}
                    </el-button>
                    <el-button v-permission="['sys:user:delete']" class="filter-item" style="margin-left: 10px;" type="danger" @click="handleDelete(row)">
                        {{ $t('table.delete') }}
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
        <pagination v-show="total>0" :total="total" :page.sync="listQuery.current" :limit.sync="listQuery.size" @pagination="getList" />
        <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
            <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
                <el-form-item :label="$t('userInfo.username')" prop="username">
                    <el-input v-model="temp.username" />
                </el-form-item>
                <el-form-item :label="$t('userInfo.status')" prop="status">
                    <el-input v-model="temp.status" />
                </el-form-item>
                <!-- <el-form-item :label="$t('userInfo.status')">
                  <el-select v-model="temp.status" class="filter-item" placeholder="Please select">
                    <el-option v-for="item in statusOptions" :key="item.value" :label="item.label" :value="item.value" />
                  </el-select>
                </el-form-item> -->
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">
                    {{ $t('table.cancel') }}
                </el-button>
                <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
                    {{ $t('table.confirm') }}
                </el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import permission from '@/directive/permission/index.js'
import { fetchList, create, update, remove } from '@/api/SysUser'
import waves from '@/directive/waves'
import Pagination from '@/components/Pagination'

export default {
  name: 'UserManage',
  components: { Pagination },
  directives: { waves, permission },
  filters: {
    statusFilter(status) {
      const statusMap = {
        0: 'success',
        1: 'danger'
      }
      return statusMap[status]
    },
    statusMessageFilter(status) {
      const statusMap = {
        0: '启用',
        1: '禁用'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        current: 1,
        size: 10,
        title: undefined,
        sort: '+id'
      },
      ids: [],
      temp: {
        id: null,
        username: null,
        password: null,
        status: null,
        createTime: null
      },
      dialogFormVisible: false,
      dialogStatus: 'create',
      textMap: {
        update: '编辑',
        create: '添加'
      },
      rules: {
        title: [{ required: true, message: 'title is required', trigger: 'blur' }]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.list = response.data.records
        this.total = response.data.total
        this.listLoading = false
      })
    },
    handleFilter() {
      this.listQuery.current = 1
      this.getList()
    },
    handleModifyStatus(row, status) {
      this.$message({
        message: '操作成功',
        type: 'success'
      })
      row.status = status
    },
    sortChange(data) {
      const { prop, order } = data
      if (prop === 'id') {
        this.sortByID(order)
      }
    },
    sortByID(order) {
      if (order === 'ascending') {
        this.listQuery.sort = '+id'
      } else {
        this.listQuery.sort = '-id'
      }
      this.handleFilter()
    },
    resetTemp() {
      this.temp = {
        id: null,
        username: null,
        status: null
      }
    },
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          create(this.temp).then(() => {
            this.list.unshift(this.temp)
            this.dialogFormVisible = false
            this.$notify({
              title: '成功',
              message: '创建成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleUpdate(row) {
      this.temp = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          const tempData = Object.assign({}, this.temp)
          update(tempData).then(() => {
            for (const v of this.list) {
              if (v.id === this.temp.id) {
                const index = this.list.indexOf(v)
                this.list.splice(index, 1, this.temp)
                break
              }
            }
            this.dialogFormVisible = false
            this.$notify({
              title: '成功',
              message: '更新成功',
              type: 'success',
              duration: 2000
            })
          })
        }
      })
    },
    handleDelete(row) {
      const _ids = row !== undefined ? [row.id] : this.ids.map(obj => {
        return obj.id
      })
      remove(_ids).then(() => {
        this.getList()
        this.dialogFormVisible = false
        this.$notify({
          title: '成功',
          message: '删除成功',
          type: 'success',
          duration: 2000
        })
      })
    },
    handleSelectionChange(val) {
      this.ids = val
      console.log(this.ids)
    }
  }
}
</script>
