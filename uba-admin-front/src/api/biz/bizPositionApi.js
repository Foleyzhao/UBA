import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/biz/position/` + url, ...arg)
/**
 * 岗位
 *
 * @author happynewyear
 */
export default {
	// 获取岗位分页
	positionPage(data) {
		return request('page', data, 'get')
	},
	// 获取岗位列表
	positionList(data) {
		return request('list', data, 'get')
	},
	// 提交表单 edit为true时为编辑，默认为新增
	submitForm(data, edit = false) {
		return request(edit ? 'edit' : 'add', data)
	},
	// 删除岗位
	positionDelete(data) {
		return request('delete', data)
	},
	// 获取岗位详情
	positionDetail(data) {
		return request('detail', data, 'get')
	},
	// 获取机构树选择器
	positionOrgTreeSelector(data) {
		return request('orgTreeSelector', data, 'get')
	},
	// 获取岗位选择器
	positionPositionSelector(data) {
		return request('positionSelector', data, 'get')
	}
}
