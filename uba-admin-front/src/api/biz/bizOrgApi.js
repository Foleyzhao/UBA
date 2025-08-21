import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/biz/org/` + url, ...arg)
/**
 * 机构
 *
 * @author happynewyear
 */
export default {
	// 获取机构分页
	orgPage(data) {
		return request('page', data, 'get')
	},
	// 获取机构列表
	orgList(data) {
		return request('list', data, 'get')
	},
	// 获取机构树
	orgTree(data) {
		return request('tree', data, 'get')
	},
	// 提交表单 edit为true时为编辑，默认为新增
	submitForm(data, edit = false) {
		return request(edit ? 'edit' : 'add', data)
	},
	// 删除机构
	orgDelete(data) {
		return request('delete', data)
	},
	// 获取机构详情
	orgDetail(data) {
		return request('detail', data, 'get')
	},
	// 获取机构树选择器
	orgTreeSelector(data) {
		return request('orgTreeSelector', data, 'get')
	},
	// 获取人员选择器
	orgUserSelector(data) {
		return request('userSelector', data, 'get')
	}
}
