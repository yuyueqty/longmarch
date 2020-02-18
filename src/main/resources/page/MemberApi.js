import request from '@/utils/request'

export function fetchList(data) {
  return request({
    url: '/test/member/search',
    method: 'post',
    data
  })
}

export function create(data) {
  return request({
    url: '/test/member/create',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/test/member/update',
    method: 'post',
    data
  })
}

export function remove(data) {
  return request({
    url: '/test/member/delete',
    method: 'post',
    data
  })
}

export function changeStatus(data) {
  return request({
    url: '/test/member/changeStatus',
    method: 'post',
    data
  })
}
