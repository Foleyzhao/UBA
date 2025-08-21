import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/uba/group/` + url, ...arg)

/**
 * 运营用户组Api接口管理器
 *
 * @author happynewyear
 */
export default {
	// 获取运营用户组分页
	groupPage(data) {
		return request('page', data, 'get')
	},
	// 提交用户组表单 edit为true时为编辑，默认为新增
	groupSubmitForm(data, edit = false) {
		return request(edit ? 'edit' : 'add', data)
	},
	// 删除运营用户组
	groupDelete(data) {
		return request('delete', data)
	},
	// 获取运营用户组详情
	groupDetail(data) {
		return request('detail', data, 'get')
	}
}
