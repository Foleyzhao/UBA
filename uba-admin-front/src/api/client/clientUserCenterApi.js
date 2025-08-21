import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/client/userCenter/` + url, ...arg)
/**
 * C端用户个人控制器
 *
 * @author happynewyear
 */
export default {
	// 获取图片验证码
	clientUserGetPicCaptcha(data) {
		return request('getPicCaptcha', data, 'get')
	},
	// 找回密码获取手机验证码
	clientUserFindPasswordGetPhoneValidCode(data) {
		return request('findPasswordGetPhoneValidCode', data, 'get')
	},
	// 找回密码获取邮箱验证码
	clientUserFindPasswordGetEmailValidCode(data) {
		return request('findPasswordGetEmailValidCode', data, 'get')
	},
	// 通过手机号找回用户密码
	clientUserFindPasswordByPhone(data) {
		return request('findPasswordByPhone', data)
	},
	// 通过邮箱找回用户密码
	clientUserFindPasswordByEmail(data) {
		return request('findPasswordByEmail', data)
	},
	// 修改密码获取手机验证码
	clientUserUpdatePasswordGetPhoneValidCode(data) {
		return request('updatePasswordGetPhoneValidCode', data, 'get')
	},
	// 修改密码获取邮箱验证码
	clientUserUpdatePasswordGetEmailValidCode(data) {
		return request('updatePasswordGetEmailValidCode', data, 'get')
	},
	// 通过验证旧密码修改用户密码
	clientUserUpdatePasswordByOld(data) {
		return request('updatePasswordByOld', data)
	},
	// 通过验证手机号修改用户密码
	clientUserUpdatePasswordByPhone(data) {
		return request('updatePasswordByPhone', data)
	},
	// 通过验证邮箱修改用户密码
	clientUserUpdatePasswordByEmail(data) {
		return request('updatePasswordByEmail', data)
	},
	// 绑定手机号获取手机验证码
	clientUserBindPhoneGetPhoneValidCode(data) {
		return request('bindPhoneGetPhoneValidCode', data, 'get')
	},
	// 修改绑定手机号获取手机验证码
	clientUserUpdateBindPhoneGetPhoneValidCode(data) {
		return request('updateBindPhoneGetPhoneValidCode', data, 'get')
	},
	// 绑定手机号
	clientUserBindPhone(data) {
		return request('bindPhone', data)
	},
	// 绑定邮箱获取邮箱验证码
	clientUserBindEmailGetEmailValidCode(data) {
		return request('bindEmailGetEmailValidCode', data, 'get')
	},
	// 修改绑定邮箱获取邮箱验证码
	clientUserUpdateBindEmailGetEmailValidCode(data) {
		return request('updateBindEmailGetEmailValidCode', data, 'get')
	},
	// 绑定邮箱
	clientUserBindEmail(data) {
		return request('bindEmail', data)
	},
	// 修改用户头像
	clientUserUpdateAvatar(data) {
		return request('updateAvatar', data)
	},
	// 修改用户签名图片
	clientUserUpdateSignature(data) {
		return request('updateSignature', data)
	},
	// 编辑个人信息
	clientUserUpdateUserInfo(data) {
		return request('updateUserInfo', data)
	},
	// 根据id获取头像
	clientUserGetAvatarById(data) {
		return request('getAvatarById', data, 'get')
	},
	// 判断当前用户是否需要绑定手机号
	clientUserIsUserNeedBindPhone(data) {
		return request('isUserNeedBindPhone', data, 'get')
	},
	// 判断当前用户是否需要绑定邮箱
	clientUserIsUserNeedBindEmail(data) {
		return request('isUserNeedBindEmail', data, 'get')
	},
	// 判断当前用户密码是否过期
	clientUserIsUserPasswordExpired(data) {
		return request('isUserPasswordExpired', data, 'get')
	}
}
