import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/auth/b/` + url, ...arg)
/**
 * 登录
 *
 * @author happynewyear
 */
export default {
	// B端获取图片验证码
	getPicCaptcha(data) {
		return request('getPicCaptcha', data, 'get')
	},
	// B端获取手机验证码
	getPhoneValidCode(data) {
		return request('getPhoneValidCode', data, 'get')
	},
	// B端获取邮箱验证码
	getEmailValidCode(data) {
		return request('getEmailValidCode', data, 'get')
	},
	// B端账号密码登录
	login(data) {
		return request('doLogin', data, 'post', false)
	},
	// B端手机验证码登录
	loginByPhone(data) {
		return request('doLoginByPhone', data, 'post', false)
	},
	// B端邮箱验证码登录
	loginByEmail(data) {
		return request('doLoginByEmail', data, 'post', false)
	},
	// 退出
	logout(data) {
		return request('doLogout', data, 'get')
	},
	// 获取用户信息
	getLoginUser(data) {
		return request('getLoginUser', data, 'get')
	},
	// 注册用户
	register(data) {
		return request('register', data)
	}
}
