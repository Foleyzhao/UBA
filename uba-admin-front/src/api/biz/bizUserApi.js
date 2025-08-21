import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/biz/user/` + url, ...arg)
/**
 * 人员接口api
 *
 * @author happynewyear
 */
export default {
	// 获取人员分页
	userPage(data) {
		return request('page', data, 'get')
	},
	// 提交表单 edit为true时为编辑，默认为新增
	submitForm(data, edit = false) {
		return request(edit ? 'edit' : 'add', data)
	},
	// 删除人员
	userDelete(data) {
		return request('delete', data)
	},
	// 获取人员详情
	userDetail(data) {
		return request('detail', data, 'get')
	},
	// 禁用人员
	userDisableUser(data) {
		return request('disableUser', data)
	},
	// 启用人员
	userEnableUser(data) {
		return request('enableUser', data)
	},
	// 重置人员密码
	userResetPassword(data) {
		return request('resetPassword', data)
	},
	// 获取组织选择器
	userOrgTreeSelector(data) {
		return request('orgTreeSelector', data, 'get')
	},
	// 获取职位选择器
	userPositionSelector(data) {
		return request('positionSelector', data, 'get')
	},
	// 获取角色选择器
	userRoleSelector(data) {
		return request('roleSelector', data, 'get')
	},
	// 获取人员选择器
	userSelector(data) {
		return request('userSelector', data, 'get')
	},
	// 人员拥有角色
	userOwnRole(data) {
		return request('ownRole', data, 'get')
	},
	// 给人员授权角色
	grantRole(data) {
		return request('grantRole', data)
	},
	// 人员导出
	userExport(data) {
		return request('export', data, 'get', {
			responseType: 'blob'
		})
	},
	// 导出人员个人信息
	userExportUserInfo(data) {
		return request('exportUserInfo', data, 'get', {
			responseType: 'blob'
		})
	}
}
