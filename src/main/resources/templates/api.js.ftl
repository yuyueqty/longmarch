import request from '@/utils/request'

export function fetchList(data) {
  return request({
    url: '<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/search',
    method: 'post',
    data
  })
}

export function create(data) {
  return request({
    url: '<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/create',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/update',
    method: 'post',
    data
  })
}

export function remove(data) {
  return request({
    url: '<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/delete',
    method: 'post',
    data
  })
}

export function changeStatus(data) {
  return request({
    url: '<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>/changeStatus',
    method: 'post',
    data
  })
}
