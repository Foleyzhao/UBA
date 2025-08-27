import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/uba/ruleItem/` + url, ...arg)

/**
 * 数据清洗规则项
 *
 * @author happynewyear
 */
export default {
	// 获取规则分页
	ruleItemPage(data) {
		return request('page', data, 'get')
	},
	// 获取规则列表
	ruleItemList(data) {
		return request('list', data, 'get')
	},
	// 提交表单 edit为true时为编辑，默认为新增
	submitForm(data, edit = false) {
		return request(edit ? 'edit' : 'add', data)
	},
	// 删除规则
	ruleItemDelete(data) {
		return request('delete', data)
	},
	// 获取规则详情
	ruleItemDetail(data) {
		return request('detail', data, 'get')
	},
	// 禁用数据清洗规则项
	ruleItemDisable(data) {
		return request('disable', data)
	},
	// 启用数据清洗规则项
	ruleItemEnable(data) {
		return request('enable', data)
	},

}
