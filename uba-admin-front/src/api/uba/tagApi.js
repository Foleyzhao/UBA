import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/uba/tag/` + url, ...arg)

/**
 * 用户标签
 *
 * @author happynewyear
 */
export default {
	// 获取标签分页
	tagPage(data) {
		return request('page', data, 'get')
	},
	// 获取标签列表
	tagList(data) {
		return request('list', data, 'get')
	},
	// 获取标签树
	tagTree(data) {
		return request('tree', data, 'get')
	},
	// 提交表单 edit为true时为编辑，默认为新增
	submitForm(data, edit = false) {
		return request(edit ? 'edit' : 'add', data)
	},
	// 删除标签
	tagDelete(data) {
		return request('delete', data)
	},
	// 获取标签详情
	tagDetail(data) {
		return request('detail', data, 'get')
	}
}
