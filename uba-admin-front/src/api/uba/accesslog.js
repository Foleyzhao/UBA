import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/uba/accesslog/` + url, ...arg)

/**
 * 访问日志
 *
 * @author happynewyear
 */
export default {
	// 获取访问日志分页
	accessLogPage(data) {
		return request('page', data, 'get')
	},
	// 删除访问日志
	accessLogDelete(data) {
		return request('delete', data)
	},
	// 获取访问日志详情
	accessLogDetail(data) {
		return request('detail', data, 'get')
	}
}
