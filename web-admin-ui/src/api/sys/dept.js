/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

import request from '@/js/request'

export function fetchOrgTypeListPage(query) {
  return request({
    url: '/sys/orgType/listPage',
    method: 'get',
    params: query
  })
}

export function fetchOrgTypeList(data) {
  return request({
    url: '/sys/orgType/list',
    method: 'post',
    data: data
  })
}

export function addOrgType(obj) {
  return request({
    url: '/sys/orgType/add',
    method: 'post',
    data: obj
  })
}

export function delOrgType(id) {
  return request({
    url: '/sys/orgType/delete/' + id,
    method: 'post'
  })
}

export function updOrgType(obj) {
  return request({
    url: '/sys/orgType/update',
    method: 'post',
    data: obj
  })
}

export function getOrgType(id) {
  return request({
    url: '/sys/orgType/' + id,
    method: 'get',
  })
}
