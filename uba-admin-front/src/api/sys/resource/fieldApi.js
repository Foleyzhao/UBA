import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/sys/field/` + url, ...arg)
/**
 * 字段
 *
 * @author happynewyear
 */
export default {
	// 获取字段分页
	fieldPage(data) {
		return request('page', data, 'get')
	},
	// 获取字段树
	fieldTree(data) {
		return request('tree', data, 'get')
	},
	// 提交表单 edit为true时为编辑，默认为新增
	submitForm(data, edit = false) {
		return request(edit ? 'edit' : 'add', data)
	},
	// 删除字段
	fieldDelete(data) {
		return request('delete', data)
	},
	// 获取字段详情
	fieldDetail(data) {
		return request('detail', data, 'get')
	},
	// 获取菜单树选择器
	fieldMenuTreeSelector(data) {
		return request('MenuTreeSelector', data, 'get')
	}
}
