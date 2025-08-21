import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/auth/third/` + url, ...arg)
/**
 * 三方用户
 *
 * @author happynewyear
 */
export default {
	// 获取三方用户分页
	thirdPage(data) {
		return request('page', data, 'get')
	},
	// 第三方登录页面渲染
	thirdRender(data) {
		return request('render', data, 'get')
	},
	// 第三方登录授权回调
	thirdCallback(data) {
		return request('callback', data, 'get')
	}
}
