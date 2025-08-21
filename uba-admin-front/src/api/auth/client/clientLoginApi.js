import {baseRequest} from '@/utils/request'
import tool from '@/utils/tool'

const request = (url, ...arg) => baseRequest(`/auth/c/` + url, ...arg)
/**
 * 登录
 *
 * @author happynewyear
 */
export default {
	// C端获取图片验证码
	clientGetPicCaptcha(data) {
		return request('getPicCaptcha', data, 'get')
	},
	// C端获取手机验证码
	clientGetPhoneValidCode(data) {
		return request('getPhoneValidCode', data, 'get')
	},
	// C端获取邮箱验证码
	clientGetEmailValidCode(data) {
		return request('getEmailValidCode', data, 'get')
	},
	// C端账号密码登录
	clientLogin(data) {
		return request('doLogin', data, 'post', false)
	},
	// C端手机验证码登录
	clientLoginByPhone(data) {
		return request('doLoginByPhone', data, 'post', false)
	},
	// C端邮箱验证码登录
	clientLoginByEmail(data) {
		return request('doLoginByEmail', data, 'post', false)
	},
	// 退出
	clientLogout(data) {
		return request('doLogout', data, 'get')
	},
	// 获取用户信息
	clientGetLoginUser(data) {
		return request('getLoginUser', data, 'get')
	},
	// C端注册
	clientRegister(data) {
		return request('register', data, 'post')
	}
}
