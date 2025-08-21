import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/dev/dict/` + url, ...arg)
/**
 * 字典
 *
 * @author happynewyear
 */
export default {
	// 获取字典分页
	dictPage(data) {
		return request('page', data, 'get')
	},
	// 获取字典列表
	dictList(data) {
		return request('list', data, 'get')
	},
	// 获取字典树
	dictTree(data) {
		return request('tree', data, 'get')
	},
	// 提交表单 edit为true时为编辑，默认为新增
	submitForm(data, edit = false) {
		return request(edit ? 'edit' : 'add', data)
	},
	// 删除字典
	dictDelete(data) {
		return request('delete', data)
	},
	// 获取字典详情
	dictDetail(data) {
		return request('detail', data, 'get')
	}
}
