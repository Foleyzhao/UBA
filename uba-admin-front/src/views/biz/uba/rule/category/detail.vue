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
								<a-button type="primary" @click="addRuleItem(formData.id)">
									<template #icon>
										<plus-outlined/>
									</template>
									新增
								</a-button>
							</a-space>
						</template>
						<template #bodyCell="{ column, record }">
							<template v-if="column.dataIndex === 'content'">
								<div v-if="record.editable">
									<a-input v-model:value="record.content" placeholder="请输入"/>
								</div>
								<div v-else>
									<div>{{ record.content }}</div>
								</div>
							</template>
							<template v-if="column.dataIndex === 'result'">
								<div v-if="record.editable">
									<a-input v-model:value="record.result" placeholder="请输入"/>
								</div>
								<div v-else>
									<div>{{ record.result }}</div>
								</div>
							</template>
							<template v-if="column.dataIndex === 'status'">
								<a-switch :loading="loading" :checked="record.status === 'ENABLE'"
										  @change="editRuleItemStatus(record)"/>
							</template>
							<template v-if="column.dataIndex === 'sortCode'">
								<div v-if="record.editable">
									<a-input v-model:value="record.sortCode" placeholder="请输入"/>
								</div>
								<div v-else>
									<div>{{ record.sortCode }}</div>
								</div>
							</template>
							<template v-if="column.dataIndex === 'action'">
								<a-space>
									<div v-if="record.editable">
										<a-button type="link" primary size="small" @click="saveRuleItem(record)">
											保存
										</a-button>
										<a-divider type="vertical"/>
										<a-button type="link" primary size="small" @click="cancelEditRuleItem(record)">
											取消
										</a-button>
									</div>
									<div v-else>
										<a-button type="link" primary size="small"
												  @click="editRuleItem(record)">编辑
										</a-button>
										<a-divider type="vertical"/>
										<a-popconfirm title="确定要删除吗？" @confirm="deleteRuleItem(record)">
											<a-button type="link" danger size="small">删除</a-button>
										</a-popconfirm>
									</div>
								</a-space>
							</template>
						</template>
					</s-table>
				</a-form-item>
			</a-col>
		</a-form>
		<template #footer>
			<a-button class="sys-mr8" @click="onClose">关闭</a-button>
		</template>
	</sys-form-container>

	<!-- 以下是弹窗内容 -->
	<a-modal
		v-model:open="addVisible"
		title="新增清洗规则项"
		:width="500"
		:mask-closable="false"
		:destroy-on-close="true"
		@ok="handleAddOk"
		@cancel="handleAddClose"
	>
		<a-form :model="addFormData" :rules="addFormRules" layout="vertical" :label-col="labelCol">
			<br>
			<a-form-item label="规则项内容：" name="content">
				<a-input v-model:value="addFormData.content" placeholder="请输入规则项内容" allow-clear/>
			</a-form-item>
			<a-form-item label="规则项结果：" name="result">
				<a-input v-model:value="addFormData.result" placeholder="请输入规则项结果" allow-clear/>
			</a-form-item>
			<a-form-item label="排序码：" name="sortCode">
				<a-input-number class="sys-wd" v-model:value="addFormData.sortCode" :max="1000"/>
			</a-form-item>
		</a-form>
	</a-modal>
</template>

<script setup name="ruleItemForm">
import ubaRuleItemApi from '@/api/uba/ruleItemApi.js'
import ruleItemApi from '@/api/uba/ruleItemApi.js'
import {required} from "@/utils/formRules";
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
// 调用这个函数将子组件的一些数据和方法暴露出去
defineExpose({
	onOpen
})
// ------ 清洗规则项 ------
const ruleItemTableRef = ref()
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
const loading = ref(false)
// 修改清洗规则项状态
const editRuleItemStatus = (record) => {
	loading.value = true
	if (record.status === 'ENABLE') {
		ruleItemApi
			.ruleItemDisable(record)
			.then(() => {
				ruleItemTableRef.value.refresh()
			})
			.finally(() => {
				loading.value = false
			})
	} else {
		ruleItemApi
			.ruleItemEnable(record)
			.then(() => {
				ruleItemTableRef.value.refresh()
			})
			.finally(() => {
				loading.value = false
			})
	}
}
// 编辑清洗规则项
const editRuleItem = (record) => {
	record.editable = true
}
// 取消编辑清洗规则项
const cancelEditRuleItem = (record) => {
	record.editable = false
}
// 保存编辑清洗规则项
const saveRuleItem = (record) => {
	ubaRuleItemApi.submitForm(record, record.id).then(() => {
		emit('successful')
	}).finally(() => {
			record.editable = false
		}
	)
}
// 新增清洗规则项
const addRuleItem = (ruleId) => {
	addVisible.value = true
	addVisible.value = true
}
//------ 数据规则项新增 ------
const addVisible = ref(false)
let addFormData = ref({
	sortCode: 99,
})
const addFormRules = {
	content: [required('请输入规则名称')],
	result: [required('请选择清洗字段')],
	sortCode: [required('请选择排序')]
}
const handleAddOk = () => {
	addFormData.value.ruleId = formData.value.id
	addFormData.value.status = 'ENABLE'
	console.log(addFormData)
	ubaRuleItemApi.submitForm(addFormData.value, false).then(() => {
		emit('successful')
	}).then(() => {
			handleAddClose()
		}
	).finally(() => {
			ruleItemTableRef.value.refresh(true)
		}
	)
}
const handleAddClose = () => {
	addFormData.value = {
		sortCode: 99,
	}
	addVisible.value = false
}
</script>
