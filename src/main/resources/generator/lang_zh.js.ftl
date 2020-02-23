route: {
  ${entity?uncap_first}Manage: '${table.comment}管理'
}

${entity}: {
<#list fieldGenerationConditionList as condition>
  ${condition.propertyName}: '${condition.remark}'<#if condition_has_next>,</#if>
</#list>
}
