<template>
	<sys-form-container
		:title="formData.id ? '编辑运营用户' : '增加运营用户'"
		:width="800"
		:visible="visible"
		:destroy-on-close="true"
		:body-style="{ 'padding-top': '0px' }"
		@close="onClose"
	>
		<a-form ref="formRef" :model="formData" :rules="formRules" layout="vertical">
			<a-tabs v-model:activeKey="activeTabsKey">
				<a-tab-pane key="1" tab="基础信息" force-render>
					<a-row :gutter="16">
						<a-col :span="12">
							<a-form-item label="账号：" name="account">
								<a-input v-model:value="formData.account" placeholder="请输入账号" allow-clear/>
							</a-form-item>
						</a-col>
						<a-col :span="12">
							<a-form-item label="昵称：" name="name">
								<a-input v-model:value="formData.nickname" placeholder="请输入昵称" allow-clear/>
							</a-form-item>
						</a-col>
					</a-row>
				</a-tab-pane>
<!--				<a-tab-pane key="2" tab="更多信息" force-render>-->
<!--				</a-tab-pane>-->
			</a-tabs>
		</a-form>
		<template #footer>
			<a-button class="sys-mr8" @click="onClose">关闭</a-button>
			<a-button type="primary" :loading="formLoading" @click="onSubmit">保存</a-button>
		</template>
	</sys-form-container>
</template>

<script setup>
import userApi from '@/api/uba/userApi'
import {required} from '@/utils/formRules'
import {cloneDeep} from "lodash-es";
// 默认是关闭状态
const visible = ref(false)
const formRef = ref()
const activeTabsKey = ref('1')
const emit = defineEmits({successful: null})
// 表单数据
const formData = ref({})
const formLoading = ref(false)

// 打开抽屉
const onOpen = (record) => {
	visible.value = true
	if (record) {
		let recordData = cloneDeep(record)
		formData.value = Object.assign({}, recordData)
	}
}
// 关闭抽屉
const onClose = () => {
	formRef.value.resetFields()
	formData.value = {}
	visible.value = false
}
// 默认要校验的
const formRules = {
	account: [required('请输入账号')],
	nickname: [required('请输入昵称')]
}
// 验证并提交数据
const onSubmit = () => {
	formRef.value
		.validate()
		.then(() => {
			formLoading.value = true
			const formDataParam = cloneDeep(formData.value)
			userApi
				.submitForm(formDataParam, formDataParam.id)
				.then(() => {
					onClose()
					emit('successful')
				})
				.finally(() => {
					formLoading.value = false
				})
		})
		.catch(() => {
		})
}
// 调用这个函数将子组件的一些数据和方法暴露出去
defineExpose({
	onOpen
})
</script>

<style scoped lang="less">
.form-row {
	background-color: var(--item-hover-bg);
	margin-left: 0 !important;
	margin-bottom: 10px;
}

.form-row-con {
	padding-bottom: 5px;
	padding-top: 5px;
	padding-left: 15px;
}
</style>
