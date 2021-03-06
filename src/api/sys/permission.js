/**
* @author: zhazhjie
* @email: zhazhjie@vip.qq.com
* @date: 2018-11-05 13:58:23
* @version: 1.0
*/

import request from '../../config/request'

export function listPermission(data) {
  return request({
    url: '/sys/permission/list',
    method: 'get',
    params: data
  })
}
export function listUserPermission() {
  return request({
    url: '/sys/permission/listUserPermission',
    method: 'get'
  })
}

export function addObj(obj) {
  return request({
    url: '/sys/permission/add',
    method: 'put',
    data: obj
  })
}

export function getObj(id) {
  return request({
    url: '/sys/permission/info/' + id,
    method: 'get'
  })
}

export function delObj(id) {
  return request({
    url: '/sys/permission/delete/' + id,
    method: 'delete'
  })
}

export function udpObj(obj) {
  return request({
    url: '/sys/permission/update',
    method: 'post',
    data: obj
  })
}
