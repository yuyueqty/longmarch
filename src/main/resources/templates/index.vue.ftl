<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="filter-container clearfix">
        <el-form :inline="true" :model="listQuery" class="demo-form-inline">
          <el-form-item class="postInfo-container-item">
            <el-input v-model="listQuery.fuzzySearch" clearable :placeholder="$t('table.fuzzySearch')" style="width: 200px;" class="filter-item" @keyup.enter.native="handleFilter" />
          </el-form-item>
          <el-button v-waves class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">
            {{ $t('table.search') }}
          </el-button>
        </el-form>
        <el-button v-permission="['${package.ModuleName}:${table.name}:create']" class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate">
          {{ $t('table.add') }}
        </el-button>
        <el-button v-permission="['${package.ModuleName}:${table.name}:delete']" :disabled="batchDeleteButtonStatus" class="filter-item" style="margin-left: 10px;" type="danger" icon="el-icon-delete" @click="deleteData()">
          {{ $t('table.batchDelete') }}
        </el-button>
      </div>
      <el-table
        :key="tableKey"
        v-loading="listLoading"
        :data="list"
        highlight-current-row
        style="width: 100%;"
        @sort-change="sortChange"
        @selection-change="handleSelectionChange"
      >
        <el-table-column
          type="selection"
          width="55"
        />
        <#list fieldGenerationConditionList as condition>
        <#if condition.listShow>
        <el-table-column<#if condition.orderBy> prop="${condition.propertyName}" sortable="custom"</#if> :label="$t('${entity}.${condition.propertyName}')" align="center">
          <#if condition.dictCode??>
          <template slot-scope="scope">
            <el-tag :type="scope.row.${condition.propertyName} | dictFirst(dictionary.style_dict)">
              <span>{{ scope.row.${condition.propertyName} | dictFirst(dictionary.${condition.dictCode}) }}</span>
            </el-tag>
          </template>
          <#else>
          <template slot-scope="scope">
            <span>{{ scope.row.${condition.propertyName} }}</span>
          </template>
          </#if>
        </el-table-column>
      </#if>
      </#list>
        <el-table-column v-if="checkPermission(['${package.ModuleName}:${table.name}:update', '${package.ModuleName}:${table.name}:delete'])" fixed="right" :label="$t('table.actions')" width="200px" align="center" class-name="small-padding fixed-width">
          <template slot-scope="{row}">
            <el-button v-permission="['${package.ModuleName}:${table.name}:update']" class="filter-item" style="margin-left: 10px;" type="primary" @click="handleUpdate(row)">
              {{ $t('table.edit') }}
            </el-button>
            <el-button v-permission="['${package.ModuleName}:${table.name}:delete']" class="filter-item" style="margin-left: 10px;" type="danger" @click="deleteData(row)">
              {{ $t('table.delete') }}
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <pagination v-show="total>0" :total="total" :page.sync="listQuery.current" :limit.sync="listQuery.size" @pagination="getList" />
    </el-card>
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="right" label-width="80px" style="width: 500px; margin-left:50px;">
        <#list fieldGenerationConditionList as condition>
        <#if condition.formShow>
        <el-form-item :label="$t('${entity}.${condition.propertyName}')">
          <#if condition.dictCode??>
          <el-radio-group v-model="temp.${condition.propertyName}">
            <el-radio-button v-for="item in dictionary.${condition.dictCode}" :key="item.value" :label="item.value">{{ item.label }}</el-radio-button>
          </el-radio-group>
          <#else>
          <#if condition.formType == "input">
          <el-input v-model="temp.${condition.propertyName}" />
          <#elseif condition.formType == "textarea">
          <el-input v-model="temp.${condition.propertyName}" type="textarea" :rows="2" placeholder="请输入内容" />
          <#elseif condition.formType == "radio">
          <el-radio v-model="temp.${condition.propertyName}" label="1">备选项1</el-radio>
          <el-radio v-model="temp.${condition.propertyName}" label="2">备选项2</el-radio>
          <#elseif condition.formType == "checkbox">

          <#elseif condition.formType == "date">
          <el-date-picker
            v-model="temp.${condition.propertyName}"
            type="date"
            placeholder="选择日期"
            :picker-options="pickerOptions"
          />
          <#else>
          </#if>
          </#if>
        </el-form-item>
        </#if>
        </#list>
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
import checkPermission from '@/utils/permission'
import { fetchList, create, update, remove, changeStatus } from '@/api/${table.entityName}'
import waves from '@/directive/waves'
import Pagination from '@/components/Pagination'
import { mapGetters } from 'vuex'

export default {
  name: '${table.entityName}Manage',
  components: { Pagination },
  directives: { waves, permission },
  filters: {
    dictFirst(status, dict) {
      const statusMap = {}
      dict.forEach(item => {
        statusMap[item.value] = item.label
      })
      return statusMap[status]
    }
  },
  data() {
    return {
      tableKey: 0,
      list: [],
      ids: [],
      total: 0,
      listLoading: true,
      listQuery: {
        current: 1,
        size: 10,
        fuzzySearch: null
      },
      temp: {
      <#list fieldGenerationConditionList as condition>
      <#if condition.parameter>
        ${condition.propertyName}: ${condition.defaultValue}<#if condition_has_next>,</#if>
      </#if>
      </#list>
      },
      dialogFormVisible: false,
      dialogStatus: 'create',
      textMap: {
        update: '编辑${table.comment}',
        create: '添加${table.comment}'
      },
      <#list fieldGenerationConditionList as condition>
      <#if condition.formShow && condition.formType == "date">
      pickerOptions: {
        disabledDate(time) {
          return time.getTime() > Date.now()
        },
        shortcuts: [{
          text: '今天',
          onClick(picker) {
            picker.$emit('pick', new Date())
          }
        }, {
          text: '昨天',
          onClick(picker) {
            const date = new Date()
            date.setTime(date.getTime() - 3600 * 1000 * 24)
            picker.$emit('pick', date)
          }
        }, {
          text: '一周前',
          onClick(picker) {
            const date = new Date()
            date.setTime(date.getTime() - 3600 * 1000 * 24 * 7)
            picker.$emit('pick', date)
          }
        }]
      },
      <#break>
      </#if>
      </#list>
      rules: {
		  <#list fieldGenerationConditionList as condition>
			  <#if condition.notNull>
        ${condition.propertyName}: [{ required: true, message: '${condition.remark}不能为空', trigger: 'blur' }]<#if condition_has_next>,</#if>
			  </#if>
	    </#list>
      }
    }
  },
  computed: {
    ...mapGetters(['dictionary']),
    batchDeleteButtonStatus() {
      return this.ids.length <= 0
    }
  },
  created() {
    this.getList()
  },
  methods: {
    /** 检查权限 **/
    checkPermission,
    /** 获取列表 **/
    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.list = response.data.records
        this.total = response.data.total
        this.listLoading = false
      })
    },
    /** 过滤器 **/
    handleFilter() {
      this.listQuery.current = 1
      this.getList()
    },
    /** 排序 **/
    sortChange(data) {
      const { prop, order } = data
      if (prop !== null && order !== null) {
        this.listQuery.prop = prop
        this.listQuery.order = order
        this.handleFilter()
      }
    },
    /** 初始化属性值 **/
    resetTemp() {
      this.temp = {
      <#list fieldGenerationConditionList as condition>
       <#if condition.parameter>
        ${condition.propertyName}: null<#if condition_has_next>,</#if>
       </#if>
       </#list>
      }
    },
    /** 创建前置处理 **/
    handleCreate() {
      this.resetTemp()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    /** 创建逻辑 **/
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
    /** 更新前置处理 **/
    handleUpdate(row) {
      this.temp = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    /** 更新逻辑 **/
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
    /** 删除逻辑 **/
    deleteData(row) {
      const h = this.$createElement
      this.$msgbox({
        title: '提示',
        message: h('p', null, [
          h('span', null, '【删除字典】操作，是否继续? ')
        ]),
        showCancelButton: true,
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        beforeClose: (action, instance, done) => {
          if (action === 'confirm') {
            instance.confirmButtonLoading = true
            instance.confirmButtonText = '执行中...'
            const _ids = row !== undefined ? [row.id] : this.ids.map(obj => {
              return obj.id
            })
            remove(_ids).then(() => {
              done()
              instance.confirmButtonLoading = false
            })
          } else {
            done()
          }
        }
      }).then(action => {
        this.getList()
        this.$message({
          type: 'success',
          message: '操作完成'
        })
      })
    },
    /** 多选触发操作 **/
    handleSelectionChange(selectionIds) {
      this.ids = selectionIds
    },
    /** 开关式触发操作 **/
    changeSwitch($event, id) {
      const o = { id: id, status: $event }
      this.$confirm('【修改权限状态】操作，是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        changeStatus(o).then(() => {
          this.forLoop(id, o.status)
          this.$message({
            type: 'success',
            message: '修改成功!'
          })
        })
      }).catch(() => {
        this.forLoop(id, o.status === 1 ? 0 : 1)
        this.$message({
          type: 'info',
          message: '已取消修改'
        })
      })
    },
    /** 循环无刷新改变数据 **/
    forLoop(id, status) {
      for (const v of this.list) {
        if (v.id === id) {
          v.status = status
          break
        }
      }
    }
  }
}
</script>
