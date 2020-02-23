route: {
  ${entity?uncap_first}Manage: '${entity}Manage'
}

${entity}: {
<#list fieldGenerationConditionList as condition>
  ${condition.propertyName}: '${condition.propertyName?cap_first}'<#if condition_has_next>,</#if>
</#list>
}
