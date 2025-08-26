<template>
	<sys-form-container
		title="规则详情"
		:width="550"
		:visible="visible"
		:destroy-on-close="true"
		@close="onClose"
	>
		<a-alert class="mb-3" message="温馨提示：编辑规则名称、清洗字段请编辑规则表单，而非在详情中编辑。" type="warning"/>
		<a-form ref="formRef" :model="formData" layout="vertical" :label-col="labelCol">
			<a-row :gutter="16">
				<a-col :span="12">
					<a-form-item label="规则名称：" name="name">
						<a-input v-model:value="formData.name" disabled/>
					</a-form-item>
				</a-col>
				<a-col :span="12">
					<a-form-item label="清洗字段：" name="field">
						<a-input v-model:value="formData.field" disabled/>
					</a-form-item>
				</a-col>
			</a-row>
			<a-col :span="24">
				<a-form-item label="清洗规则项：" name="ruleItemList">
					<s-table
						ref="ruleItemTableRef"
						:columns="ruleItemColumns"
						:data="loadRuleItemData"
						bordered
						:row-key="(record) => record.id"
						:tool-config="toolConfig"
					>
						<template #operator class="table-operator">
							<a-space>
								<a-button type="primary" @click="ruleItemFormRef.onOpen(undefined, formData.id)">
									<template #icon>
										<plus-outlined/>
									</template>
									新增
								</a-button>
							</a-space>
						</template>
						<template #bodyCell="{ column, record }">
							<template v-if="column.dataIndex === 'action'">
								<a-space>
									<a @click="ruleItemFormRef.onOpen(record, categoryType)">编辑</a>
									<a-divider type="vertical"/>
									<a-popconfirm title="确定要删除吗？" @confirm="deleteRuleItem(record)">
										<a-button type="link" danger size="small">删除</a-button>
									</a-popconfirm>
								</a-space>
							</template>
						</template>
					</s-table>
				</a-form-item>
			</a-col>
		</a-form>
		<template #footer>
			<a-button class="sys-mr8" @click="onClose">关闭</a-button>
			<a-button type="primary" @click="onSubmit">保存</a-button>
		</template>
	</sys-form-container>
</template>

<script setup name="ruleForm">
import ubaRuleApi from '@/api/uba/ruleApi.js'
import ubaRuleItemApi from '@/api/uba/ruleItemApi.js'
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
// ------ 清洗规则项 ------
const ruleItemTableRef = ref()
const ruleItemFormRef = ref()
const ruleItemColumns = [
	{
		title: '规则项内容',
		dataIndex: 'content'
	},
	{
		title: '规则项结果',
		dataIndex: 'result'
	},
	{
		title: '状态',
		dataIndex: 'status'
	},
	{
		title: '排序码',
		dataIndex: 'sortCode'
	},
	{
		title: '操作',
		dataIndex: 'action',
		align: 'center',
		width: 50
	}
]
const selectedRuleItemRowKeys = ref([])
const toolConfig = {refresh: true, height: true, columnSetting: true, striped: false}
// 加载清洗规则项数据
const loadRuleItemData = (parameter) => {
	parameter.ruleId = formData.value.id
	return ubaRuleItemApi.ruleItemPage(parameter).then((data) => {
		return data
	})
}
// 删除数据清洗规则项
const deleteRuleItem = (record) => {
	let params = [
		{
			id: record.id
		}
	]
	ubaRuleItemApi.ruleItemDelete(params).then(() => {
		ruleItemTableRef.value.refresh(true)
	})
}
</script>
