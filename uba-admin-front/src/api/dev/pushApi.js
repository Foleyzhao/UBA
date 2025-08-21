import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/dev/push/` + url, ...arg)
/**
 * 消息推送
 *
 * @author happynewyear
 */
export default {
	// 获取消息推送分页
	pushPage(data) {
		return request('page', data, 'get')
	},
	// 删除消息推送
	pushDelete(data) {
		return request('delete', data)
	},
	// 获取消息推送详情
	pushDetail(data) {
		return request('detail', data, 'get')
	},
	// 动态推送消息
	pushDynamicText(data) {
		return request('pushDynamicText', data)
	},

	// 推送飞书TEXT消息
	pushFeiShuText(data) {
		return request('pushFeiShuText', data)
	},

	// 推送钉钉TEXT消息
	pushDingTalkText(data) {
		return request('pushDingTalkText', data)
	},
	// 推送消息——钉钉MARKDOWN
	pushDingTalkMarkdown(data) {
		return request('pushDingTalkMarkdown', data)
	},
	// 推送消息——钉钉LINK
	pushDingTalkLink(data) {
		return request('pushDingTalkLink', data)
	},

	// 推送消息——企业微信TXT
	pushWorkWechatText(data) {
		return request('pushWorkWechatText', data)
	},
	// 推送消息——企业微信MARKDOWN
	pushWorkWechatMarkdown(data) {
		return request('pushWorkWechatMarkdown', data)
	},
	// 推送消息——企业微信NEWS
	pushWorkWechatNews(data) {
		return request('pushWorkWechatNews', data)
	}
}
