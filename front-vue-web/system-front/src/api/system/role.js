import request from '@/utils/request'

const api_name = '/admin/system/sysRole/'

export default{

    batchRemove(idList) {
        return request({
          url: `${api_name}/batchRemove`,
          method: `delete`,
          data: idList
        })
      },

    getById(id) {
        return request({
            url: `${api_name}/findRoleById/${id}`,
            method: 'post'
        })
        },

        //更新
        updateById(role) {
        return request({
            url: `${api_name}/update`,
            method: 'post',
            data: role
        })
        },
    getPageList(page, limit, searchObj){
        return request({
            // url: '/admin/system/sysRole/' + page + "/" + limit,
            url: `${api_name}/${page}/${limit}`,
            method: 'get',
            params: searchObj
        })
    },
    removeById(id) {
        return request({
          url: `${api_name}/Remove/${id}`,
          method: 'delete'
        })
      },
      save(role) {
        return request({
          url: `${api_name}/save`,
          method: 'post',
          data: role
        })
      },

    

}
