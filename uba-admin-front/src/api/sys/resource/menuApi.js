import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/sys/menu/` + url, ...arg)
/**
 * 菜单
 *
 * @author happynewyear
 */
export default {
	// 获取菜单树
	menuTree(data) {
		return request('tree', data, 'get')
	},
	// 提交表单 edit为true时为编辑，默认为新增
	submitForm(data, edit = false) {
		return request(edit ? 'edit' : 'add', data)
	},
	// 更改菜单所属模块
	menuChangeModule(data) {
		return request('changeModule', data)
	},
	// 删除菜单
	menuDelete(data) {
		return request('delete', data)
	},
	// 获取菜单详情
	menuDetail(data) {
		return request('detail', data, 'get')
	},
	// 获取模块选择器
	menuModuleSelector(data) {
		return request('moduleSelector', data, 'get')
	},
	// 获取菜单树选择器
	menuTreeSelector(data) {
		return request('menuTreeSelector', data, 'get')
	}
}
