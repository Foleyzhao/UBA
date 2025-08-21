<template>
	<a-spin :spinning="loadSpinning">
		<a-form
			ref="formRef"
			:model="formData"
			:rules="formRules"
			layout="vertical"
			:label-col="{ ...layout.labelCol, offset: 0 }"
			:wrapper-col="{ ...layout.wrapperCol, offset: 0 }"
		>
			<a-form-item label="是否开启请求客户端IP过滤：" name="UBA_DEFAULT_ENABLE_IP_FILTER">
				<a-switch
					v-model:checked="formData.UBA_DEFAULT_ENABLE_IP_FILTER"
					checked-children="是"
					un-checked-children="否"
					placeholder="是否开启请求客户端IP过滤"
				/>
			</a-form-item>
			<a-form-item
				v-if="formData.UBA_DEFAULT_ENABLE_IP_FILTER"
				label="请求客户端IP过滤列表："
				name="UBA_DEFAULT_IP_FILTER_LIST"
			>
				<a-input
					v-model:value="formData.UBA_DEFAULT_IP_FILTER_LIST"
					placeholder="请输入请求客户端IP过滤列表"
					multiple/>
			</a-form-item>

			<a-form-item>
				<a-button type="primary" :loading="submitLoading" @click="onSubmit()">保存</a-button>
				<a-button class="sys-ml10" @click="() => formRef.resetFields()">重置</a-button>
			</a-form-item>
		</a-form>
	</a-spin>
</template>

<script setup name="ipForm">
import {cloneDeep} from 'lodash-es'
import {required} from '@/utils/formRules'
import {message} from 'ant-design-vue'
import configApi from '@/api/uba/configApi'

const formRef = ref()
const formData = ref({})
const submitLoading = ref(false)
const loadSpinning = ref(true)
// 查询此界面的配置项,并转为表单
const param = {
	category: 'UBA_DEFINE'
}
configApi.configList(param).then((data) => {
	loadSpinning.value = false
	if (data) {
		data.forEach((item) => {
			formData.value[item.configKey] = transferBooleanInValue(item.configValue)
		})
	} else {
		message.warning('表单项不存在，请初始化数据库')
	}
})
// 转换值
const transferBooleanInValue = (value) => {
	if (value === 'true' || value === 'false') {
		return value === 'true'
	} else {
		return value
	}
}
// 默认要校验的
const formRules = {
	UBA_DEFAULT_ENABLE_IP_FILTER: [required('请选择是否开启请求客户端IP过滤')],
	UBA_DEFAULT_IP_FILTER_LIST: [required('请输入请求客户端IP过滤列表')]
}
// 验证并提交数据
const onSubmit = () => {
	formRef.value
		.validate()
		.then(() => {
			submitLoading.value = true
			let submitParam = cloneDeep(formData.value)
			const param = Object.entries(submitParam).map((item) => {
				return {
					configKey: item[0],
					configValue: item[1]
				}
			})
			configApi
				.configEditForm(param)
				.then(() => {
				})
				.finally(() => {
					submitLoading.value = false
				})
		})
		.catch(() => {
		})
}
const layout = {
	labelCol: {
		span: 4
	},
	wrapperCol: {
		span: 12
	}
}
</script>
