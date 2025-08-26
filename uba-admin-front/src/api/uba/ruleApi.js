import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/uba/rule/` + url, ...arg)

/**
 * 数据清洗规则
 *
 * @author happynewyear
 */
export default {
	// 获取规则分页
	rulePage(data) {
		return request('page', data, 'get')
	},
	// 获取规则列表
	ruleList(data) {
		return request('list', data, 'get')
	},
	// 提交表单 edit为true时为编辑，默认为新增
	submitForm(data, edit = false) {
		return request(edit ? 'edit' : 'add', data)
	},
	// 删除规则
	ruleDelete(data) {
		return request('delete', data)
	},
	// 获取规则详情
	ruleDetail(data) {
		return request('detail', data, 'get')
	}
}
