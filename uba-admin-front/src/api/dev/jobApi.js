import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/dev/job/` + url, ...arg)
/**
 * 定时任务
 *
 * @author happynewyear
 */
export default {
	// 获取定时任务分页
	jobPage(data) {
		return request('page', data, 'get')
	},
	// 提交表单 edit为true时为编辑，默认为新增
	submitForm(data, edit = false) {
		return request(edit ? 'edit' : 'add', data)
	},
	// 删除定时任务
	jobDelete(data) {
		return request('delete', data)
	},
	// 获取定时任务详情
	jobDetail(data) {
		return request('detail', data, 'get')
	},
	// 停止定时任务
	jobStopJob(data) {
		return request('stopJob', data)
	},
	// 运行定时任务
	jobRunJob(data) {
		return request('runJob', data)
	},
	// 运行定时任务
	jobRunJobNow(data) {
		return request('runJobNow', data)
	},
	// 获取定时任务类
	jobGetActionClass(data) {
		return request('getActionClass', data, 'get')
	}
}
