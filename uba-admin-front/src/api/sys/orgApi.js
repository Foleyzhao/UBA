import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/sys/org/` + url, ...arg)
/**
 * 机构
 *
 * @author happynewyear
 */
export default {
	// 获取组织分页
	orgPage(data) {
		return request('page', data, 'get')
	},
	// 获取组织列表
	orgList(data) {
		return request('list', data, 'get')
	},
	// 获取组织树
	orgTree(data) {
		return request('tree', data, 'get')
	},
	// 提交表单 edit为true时为编辑，默认为新增
	submitForm(data, edit = false) {
		return request(edit ? 'edit' : 'add', data)
	},
	// 删除组织
	orgDelete(data) {
		return request('delete', data)
	},
	// 获取组织详情
	orgDetail(data) {
		return request('detail', data, 'get')
	},
	// 获取组织树选择器
	orgOrgTreeSelector(data) {
		return request('orgTreeSelector', data, 'get')
	},
	// 获取用户选择器
	orgUserSelector(data) {
		return request('userSelector', data, 'get')
	}
}
