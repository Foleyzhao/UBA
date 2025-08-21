<template>
	<sys-form-container title="详情" :width="700" :visible="visible" :destroy-on-close="true" @close="onClose">
		<a-descriptions :column="1" size="middle" bordered class="mb-2">
			<a-descriptions-item label="请求ID">{{ formData.requestId }}</a-descriptions-item>
			<a-descriptions-item label="请求日期">{{ formData.data }}</a-descriptions-item>
			<a-descriptions-item label="服务名称">{{ formData.serverName }}</a-descriptions-item>
			<a-descriptions-item label="连接ID">{{ formData.connection }}</a-descriptions-item>
			<a-descriptions-item label="请求时间">{{ formData.timestamp }}</a-descriptions-item>

			<a-descriptions-item label="客户端IP地址">{{ formData.remoteAddr }}</a-descriptions-item>
			<a-descriptions-item label="请求来源">{{ formData.httpReferer }}</a-descriptions-item>
			<a-descriptions-item label="客户端浏览器标识">{{ formData.httpUserAgent }}</a-descriptions-item>

			<a-descriptions-item label="http状态码">{{ formData.status }}</a-descriptions-item>
			<a-descriptions-item label="请求协议">{{ formData.scheme }}</a-descriptions-item>
			<a-descriptions-item label="http协议版本">{{ formData.serverProtocol }}</a-descriptions-item>
			<a-descriptions-item label="http方法">{{ formData.requestMethod }}</a-descriptions-item>
			<a-descriptions-item label="请求uri">{{ formData.requestUri }}</a-descriptions-item>
			<a-descriptions-item label="查询参数">{{ formData.queryString }}</a-descriptions-item>
			<a-descriptions-item label="请求详情">{{ formData.request }}</a-descriptions-item>

			<a-descriptions-item label="发送给客户端字节数">{{ formData.bodyBytesSent }}</a-descriptions-item>
			<a-descriptions-item label="请求处理时间">{{ formData.requestTime }}</a-descriptions-item>
			<a-descriptions-item label="后端服务器响应时间">{{ formData.upstreamResponseTime }}</a-descriptions-item>

			<a-descriptions-item label="请求host头">{{ formData.httpHost }}</a-descriptions-item>
			<a-descriptions-item label="客户端语言偏好">{{ formData.httpAcceptLanguage }}</a-descriptions-item>
			<a-descriptions-item label="代理服务器的原始客户端IP">{{ formData.httpXForwardedFor }}</a-descriptions-item>
			<a-descriptions-item label="请求头中的请求ID">{{ formData.httpXRequestId }}</a-descriptions-item>
			<a-descriptions-item label="Cookie标识">{{ formData.cookieSession }}</a-descriptions-item>
			<a-descriptions-item label="Cookie详情">{{ formData.httpCookie }}</a-descriptions-item>
		</a-descriptions>
	</sys-form-container>
</template>

<script setup name="accessLogDetail">
import accessLogApi from '@/api/uba/accesslog'
// 默认是关闭状态
const visible = ref(false)
const formRef = ref()
// 表单数据
const formData = ref({})
// 打开抽屉
const onOpen = (record) => {
	visible.value = true
	getAccessLogDetail(record)
}
// 获取访问日志详情
const getAccessLogDetail = (record) => {
	const param = {
		requestId: record.requestId
	}
	accessLogApi.accessLogDetail(param).then((data) => {
		Object.assign(record, data)
		formData.value = record
	})
}
// 关闭抽屉
const onClose = () => {
	formData.value = {}
	visible.value = false
}
// 调用这个函数将子组件的一些数据和方法暴露出去
defineExpose({
	onOpen
})
</script>
