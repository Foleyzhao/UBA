import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/sys/role/` + url, ...arg)
/**
 * 角色
 *
 * @author happynewyear
 */
export default {
	// 获取角色分页
	rolePage(data) {
		return request('page', data, 'get')
	},
	// 提交表单 edit为true时为编辑，默认为新增
	submitForm(data, edit = false) {
		return request(edit ? 'edit' : 'add', data)
	},
	// 删除角色
	roleDelete(data) {
		return request('delete', data)
	},
	// 获取角色详情
	roleDetail(data) {
		return request('detail', data, 'get')
	},
	// 获取角色拥有资源
	roleOwnResource(data) {
		return request('ownResource', data, 'get')
	},
	// 给角色授权资源
	roleGrantResource(data) {
		return request('grantResource', data)
	},
	// 获取角色拥有移动端菜单
	roleOwnMobileMenu(data) {
		return request('ownMobileMenu', data, 'get')
	},
	// 给角色授权移动端菜单
	roleGrantMobileMenu(data) {
		return request('grantMobileMenu', data)
	},
	// 获取角色拥有权限
	roleOwnPermission(data) {
		return request('ownPermission', data, 'get')
	},
	// 给角色授权权限
	roleGrantPermission(data) {
		return request('grantPermission', data)
	},
	// 获取角色下的用户
	roleOwnUser(data) {
		return request('ownUser', data, 'get')
	},
	// 给角色授权用户
	roleGrantUser(data) {
		return request('grantUser', data)
	},
	// 获取机构树
	roleOrgTreeSelector(data) {
		return request('orgTreeSelector', data, 'get')
	},
	// 获取资源授权树
	roleResourceTreeSelector(data) {
		return request('resourceTreeSelector', data, 'get')
	},
	// 获取移动端菜单授权树
	roleMobileMenuTreeSelector(data) {
		return request('mobileMenuTreeSelector', data, 'get')
	},
	// 获取权限授权树
	rolePermissionTreeSelector(data) {
		return request('permissionTreeSelector', data, 'get')
	},
	// 获取角色选择器
	roleRoleSelector(data) {
		return request('roleSelector', data, 'get')
	},
	// 获取用户选择器
	roleUserSelector(data) {
		return request('userSelector', data, 'get')
	}
}
