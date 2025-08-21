import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/sys/position/` + url, ...arg)
/**
 * 职位
 *
 * @author happynewyear
 */
export default {
	// 获取职位分页
	positionPage(data) {
		return request('page', data, 'get')
	},
	// 获取职位列表
	positionList(data) {
		return request('list', data, 'get')
	},
	// 提交表单 edit为true时为编辑，默认为新增
	submitForm(data, edit = false) {
		return request(edit ? 'edit' : 'add', data)
	},
	// 删除职位
	positionDelete(data) {
		return request('delete', data)
	},
	// 获取职位详情
	positionDetail(data) {
		return request('detail', data, 'get')
	},
	// 获取组织树选择器
	positionOrgTreeSelector(data) {
		return request('orgTreeSelector', data, 'get')
	},
	// 获取职位选择器
	positionPositionSelector(data) {
		return request('positionSelector', data, 'get')
	}
}
