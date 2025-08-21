import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/dev/email/` + url, ...arg)
/**
 * 邮件
 *
 * @author happynewyear
 */
export default {
	// 获取邮件分页
	emailPage(data) {
		return request('page', data, 'get')
	},
	// 发送邮件——本地TXT
	emailSendLocalTxt(data) {
		return request('sendLocalTxt', data)
	},
	// 发送邮件——本地HTML
	emailSendLocalHtml(data) {
		return request('sendLocalHtml', data)
	},
	// 发送邮件——阿里云TXT
	emailSendAliyunTxt(data) {
		return request('sendAliyunTxt', data)
	},
	// 发送邮件——阿里云HTML
	emailSendAliyunHtml(data) {
		return request('sendAliyunHtml', data)
	},
	// 发送邮件——阿里云TMP
	emailSendAliyunTmp(data) {
		return request('sendAliyunTmp', data)
	},
	// 发送邮件——腾讯云TXT
	emailSendTencentTxt(data) {
		return request('sendTencentTxt', data)
	},
	// 发送邮件——腾讯云HTML
	emailSendTencentHtml(data) {
		return request('sendTencentHtml', data)
	},
	// 发送邮件——腾讯云TMP
	emailSendTencentTmp(data) {
		return request('sendTencentTmp', data)
	},
	// 删除邮件
	emailDelete(data) {
		return request('delete', data)
	},
	// 获取邮件详情
	emailDetail(data) {
		return request('detail', data, 'get')
	}
}
