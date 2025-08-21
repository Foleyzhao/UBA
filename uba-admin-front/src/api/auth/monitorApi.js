import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/auth/` + url, ...arg)
/**
 * 绘画
 *
 * @author happynewyear
 */
export default {
	// 会话统计
	monitorAnalysis(data) {
		return request('session/analysis', data, 'get')
	},
	// 获取会话分页
	monitorBPage(data) {
		return request('session/b/page', data, 'get')
	},
	// 获取会话分页
	monitorCPage(data) {
		return request('session/c/page', data, 'get')
	},
	// 强退B端session
	monitorBExit(data) {
		return request('session/b/exit', data)
	},
	// 强退C端session
	monitorCExit(data) {
		return request('session/c/exit', data)
	},
	// 强退B端token
	monitorTokenBExit(data) {
		return request('token/b/exit', data)
	},
	// 强退C端token
	monitorTokenCExit(data) {
		return request('token/c/exit', data)
	}
}
