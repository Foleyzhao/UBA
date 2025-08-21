import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/sys/userCenter/` + url, ...arg)
/**
 * 用户个人控制器
 *
 * @author happynewyear
 */
export default {
	// 获取图片验证码
	userGetPicCaptcha(data) {
		return request('getPicCaptcha', data, 'get')
	},
	// 找回密码获取手机验证码
	userFindPasswordGetPhoneValidCode(data) {
		return request('findPasswordGetPhoneValidCode', data, 'get')
	},
	// 找回密码获取邮箱验证码
	userFindPasswordGetEmailValidCode(data) {
		return request('findPasswordGetEmailValidCode', data, 'get')
	},
	// 通过手机号找回用户密码
	userFindPasswordByPhone(data) {
		return request('findPasswordByPhone', data)
	},
	// 通过邮箱找回用户密码
	userFindPasswordByEmail(data) {
		return request('findPasswordByEmail', data)
	},
	// 修改密码获取手机验证码
	userUpdatePasswordGetPhoneValidCode(data) {
		return request('updatePasswordGetPhoneValidCode', data, 'get')
	},
	// 修改密码获取邮箱验证码
	userUpdatePasswordGetEmailValidCode(data) {
		return request('updatePasswordGetEmailValidCode', data, 'get')
	},
	// 通过验证旧密码修改用户密码
	userUpdatePasswordByOld(data) {
		return request('updatePasswordByOld', data)
	},
	// 通过验证手机号修改用户密码
	userUpdatePasswordByPhone(data) {
		return request('updatePasswordByPhone', data)
	},
	// 通过验证邮箱修改用户密码
	userUpdatePasswordByEmail(data) {
		return request('updatePasswordByEmail', data)
	},
	// 绑定手机号获取手机验证码
	userBindPhoneGetPhoneValidCode(data, phone) {
		// 如果有手机号，则修改获取、否则首次绑定
		return request(phone ? 'updateBindPhoneGetPhoneValidCode' : 'bindPhoneGetPhoneValidCode', data, 'get')
	},
	// 绑定手机号
	userBindPhone(data) {
		return request('bindPhone', data)
	},
	// 绑定邮箱获取邮箱验证码
	userBindEmailGetEmailValidCode(data, email) {
		// 如果有邮箱号，则修改获取、否则首次绑定
		return request(email ? 'updateBindEmailGetEmailValidCode' : 'bindEmailGetEmailValidCode', data, 'get')
	},
	// 绑定邮箱
	userBindEmail(data) {
		return request('bindEmail', data)
	},
	// 修改用户头像
	userUpdateAvatar(data) {
		return request('updateAvatar', data)
	},
	// 修改用户签名图片
	userUpdateSignature(data) {
		return request('updateSignature', data)
	},
	// 获取登录用户的菜单
	userLoginMenu(data) {
		return request('loginMenu', data, 'get')
	},
	// 获取登录用户组织树
	userLoginOrgTree(data) {
		return request('loginOrgTree', data, 'get')
	},
	// 获取登录用户的职位信息
	userLoginPositionInfo(data) {
		return request('loginPositionInfo', data, 'get')
	},
	// 编辑个人信息
	userUpdateUserInfo(data) {
		return request('updateUserInfo', data)
	},
	// 编辑个人工作台
	userUpdateUserWorkbench(data) {
		return request('updateUserWorkbench', data)
	},
	// 获取登录用户的工作台
	userLoginWorkbench(data) {
		return request('loginWorkbench', data, 'get')
	},
	// 获取登录用户的站内信分页
	userLoginUnreadMessagePage(data) {
		return request('loginUnreadMessagePage', data, 'get')
	},
	// 读取登录用户站内信详情
	userLoginUnreadMessageDetail(data) {
		return request('loginUnreadMessageDetail', data, 'get')
	},
	// 根据id集合获取组织集合
	userCenterGetOrgListByIdList(data) {
		return request('getOrgListByIdList', data)
	},
	// 根据id集合获取用户集合
	userCenterGetUserListByIdList(data) {
		return request('getUserListByIdList', data)
	},
	// 根据id集合获取职位集合
	userCenterGetPositionListByIdList(data) {
		return request('getPositionListByIdList', data)
	},
	// 根据id集合获取角色集合
	userCenterGetRoleListByIdList(data) {
		return request('getRoleListByIdList', data)
	},
	// 根据id集合获取用户组集合
	userCenterGetGroupListByIdList(data) {
		return request('getGroupListByIdList', data)
	},
	// 根据id获取头像
	userCenterGtAvatarById(data) {
		return request('getAvatarById', data, 'get')
	},
	// 判断当前用户是否需要绑定手机号
	userCenterIsUserNeedBindPhone(data) {
		return request('isUserNeedBindPhone', data, 'get')
	},
	// 判断当前用户是否需要绑定邮箱
	userCenterIsUserNeedBindEmail(data) {
		return request('isUserNeedBindEmail', data, 'get')
	},
	// 判断当前用户密码是否过期
	userCenterIsUserPasswordExpired(data) {
		return request('isUserPasswordExpired', data, 'get')
	},
	// 获取修改密码验证方式及配置
	userGetUpdatePasswordValidConfig(data) {
		return request('getUpdatePasswordValidConfig', data, 'get')
	}
}
