<template>
	<sys-form-container
		:title="formData.id ? '编辑规则' : '增加规则'"
		:width="550"
		:visible="visible"
		:destroy-on-close="true"
		@close="onClose"
	>
		<a-form ref="formRef" :model="formData" :rules="formRules" layout="vertical" :label-col="labelCol">
			<a-form-item label="规则名称：" name="name">
				<a-input v-model:value="formData.name" placeholder="请输入规则名称" allow-clear/>
			</a-form-item>
			<a-form-item label="清洗字段：" name="field">
				<a-input v-model:value="formData.field" placeholder="请输入清洗字段" allow-clear/>
			</a-form-item>
			<a-form-item label="排序：" name="sortCode">
				<a-input-number class="sys-wd" v-model:value="formData.sortCode" :max="1000"/>
			</a-form-item>
		</a-form>
		<template #footer>
			<a-button class="sys-mr8" @click="onClose">关闭</a-button>
			<a-button type="primary" @click="onSubmit">保存</a-button>
		</template>
	</sys-form-container>
</template>

<script setup name="ruleForm">
import {required} from '@/utils/formRules'
import ubaRuleApi from '@/api/uba/ruleApi.js'

// 定义emit事件
const emit = defineEmits({successful: null})
// 默认是关闭状态
const visible = ref(false)
const formRef = ref()
// 表单数据
let formData = ref({})
// 打开抽屉
const onOpen = (record, type) => {
	visible.value = true
	formData.value = {
		sortCode: 99,
		category: type
	}
	if (record) {
		formData.value = Object.assign({}, record)
		formData.value.category = type
	}
}
// 关闭抽屉
const onClose = () => {
	visible.value = false
}
// 默认要校验的
const formRules = {
	name: [required('请输入规则名称')],
	field: [required('请选择清洗字段')],
	sortCode: [required('请选择排序')]
}
// 表单固定label实现
const labelCol = ref({
	style: {
		width: '100px'
	}
})
// 验证并提交数据
const onSubmit = () => {
	formRef.value
		.validate()
		.then(() => {
			ubaRuleApi.submitForm(formData.value, formData.value.id).then(() => {
				visible.value = false
				emit('successful')
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
