import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/mobile/module/` + url, ...arg)
/**
 * 类别
 *
 * @author happynewyear
 */
export default {
	// 获取类别分页
	modulePage(data) {
		return request('page', data, 'get')
	},
	// 提交表单 edit为true时为编辑，默认为新增
	submitForm(data, edit = false) {
		return request(edit ? 'edit' : 'add', data)
	},
	// 删除类别
	moduleDelete(data) {
		return request('delete', data)
	},
	// 获取类别详情
	moduleDetail(data) {
		return request('detail', data, 'get')
	}
}
