import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/mobile/button/` + url, ...arg)
/**
 * 按钮
 *
 * @author happynewyear
 */
export default {
	// 获取按钮分页
	mobileButtonPage(data) {
		return request('page', data, 'get')
	},
	// 提交表单 edit为true时为编辑，默认为新增
	mobileButtonSubmitForm(data, edit = false) {
		return request(edit ? 'edit' : 'add', data)
	},
	// 删除按钮
	mobileButtonDelete(data) {
		return request('delete', data)
	},
	// 获取按钮详情
	mobileButtonDetail(data) {
		return request('detail', data, 'get')
	}
}
