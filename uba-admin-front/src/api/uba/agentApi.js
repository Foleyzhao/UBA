import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/uba/agent/` + url, ...arg)

/**
 * 采集器
 *
 * @author happynewyear
 */
export default {
	// 获取采集器分页
	agentPage(data) {
		return request('page', data, 'get')
	},
	// 删除采集器
	agentDelete(data) {
		return request('delete', data)
	},
	// 获取采集器详情
	agentDetail(data) {
		return request('detail', data, 'get')
	}
}
