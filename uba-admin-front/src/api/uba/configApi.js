import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/uba/config/` + url, ...arg)

/**
 * 配置
 *
 * @author happynewyear
 */
export default {
	// 获取配置分页
	configPage(data) {
		return request('page', data, 'get')
	},
	// 获取配置列表
	configList(data) {
		return request('list', data, 'get')
	},
	// 提交表单 edit为true时为编辑，默认为新增
	submitForm(data, edit = false) {
		return request(edit ? 'edit' : 'add', data)
	},
	// 删除配置
	configDelete(data) {
		return request('delete', data)
	},
	// 获取配置详情
	configDetail(data) {
		return request('detail', data, 'get')
	},
	// 配置批量更新
	configEditForm(data) {
		return request('editBatch', data)
	},
	// 获取系统基础配置
	configSysBaseList(data) {
		return request('ubaDefineList', data, 'get')
	}
}
