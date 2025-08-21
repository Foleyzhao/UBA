import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/client/user/` + url, ...arg)
/**
 * 前台用户接口api
 *
 * @author happynewyear
 */
export default {
	// 获取用户分页
	userPage(data) {
		return request('page', data, 'get')
	},
	// 提交表单 edit为true时为编辑，默认为新增
	submitForm(data, edit = false) {
		return request(edit ? 'edit' : 'add', data)
	},
	// 删除用户
	userDelete(data) {
		return request('delete', data)
	},
	// 获取用户详情
	userDetail(data) {
		return request('detail', data, 'get')
	}
}
