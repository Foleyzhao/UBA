import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/sys/user/` + url, ...arg)
/**
 * 用户接口api
 *
 * @author happynewyear
 */
export default {
	// 获取用户分页
	userPage(data) {
		return request('page', data, 'get')
	},
	// 提交表单 edit为true时为编辑，默认为新增
	submitForm(data, edit = false) {
		return request(edit ? 'edit' : 'add', data)
	},
	// 删除用户
	userDelete(data) {
		return request('delete', data)
	},
	// 获取用户详情
	userDetail(data) {
		return request('detail', data, 'get')
	},
	// 禁用用户
	userDisableUser(data) {
		return request('disableUser', data)
	},
	// 启用用户
	userEnableUser(data) {
		return request('enableUser', data)
	},
	// 重置用户密码
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
	// 获取用户选择器
	userSelector(data) {
		return request('userSelector', data, 'get')
	},
	// 用户拥有角色
	userOwnRole(data) {
		return request('ownRole', data, 'get')
	},
	// 给用户授权角色
	grantRole(data) {
		return request('grantRole', data)
	},
	// 获取用户拥有资源
	userOwnResource(data) {
		return request('ownResource', data, 'get')
	},
	// 给用户授权资源
	userGrantResource(data) {
		return request('grantResource', data)
	},
	// 获取用户拥有权限
	userOwnPermission(data) {
		return request('ownPermission', data, 'get')
	},
	// 给用户授权权限
	userGrantPermission(data) {
		return request('grantPermission', data)
	},
	// 下载用户导入模板
	userDownloadImportUserTemplate(data) {
		return request('downloadImportUserTemplate', data, 'get', {
			responseType: 'blob'
		})
	},
	// 用户导入
	userImport(data) {
		return request('import', data)
	},
	// 用户导出
	userExport(data) {
		return request('export', data, 'get', {
			responseType: 'blob'
		})
	},
	// 导出用户个人信息
	userExportUserInfo(data) {
		return request('exportUserInfo', data, 'get', {
			responseType: 'blob'
		})
	}
}
