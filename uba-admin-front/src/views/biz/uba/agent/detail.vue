<template>
	<sys-form-container title="详情" :width="700" :visible="visible" :destroy-on-close="true" @close="onClose">
		<a-descriptions :column="1" size="middle" bordered class="mb-2">
			<a-descriptions-item label="采集器ID">{{ formData.agentId }}</a-descriptions-item>
			<a-descriptions-item label="OS信息">{{ formData.os }}</a-descriptions-item>
			<a-descriptions-item label="内网IP">{{ formData.hostIp }}</a-descriptions-item>
			<a-descriptions-item label="外网IP">{{ formData.publicIp }}</a-descriptions-item>
		</a-descriptions>
		<a-form ref="formRef" :model="formData" layout="vertical">
			<a-form-item label="采集器状态：" name="status">
				<span>{{ $TOOL.dictTypeData('AGENT_STATUS', formData.status) }}</span>
			</a-form-item>
		</a-form>
	</sys-form-container>
</template>

<script setup name="agentDetail">
import agentApi from '@/api/uba/agentApi'
// 默认是关闭状态
const visible = ref(false)
const formRef = ref()
// 表单数据
const formData = ref({})
// 打开抽屉
const onOpen = (record) => {
	visible.value = true
	getAgentDetail(record)
}
// 获取采集器详情
const getAgentDetail = (record) => {
	const param = {
		id: record.id
	}
	agentApi.agentDetail(param).then((data) => {
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
