<template>
	<a-card :bordered="false">
		<a-form ref="ruleSearchFormRef" name="advanced_search" :model="ruleSearchFormState"
				class="ant-advanced-search-form">
			<a-row :gutter="24">
				<a-col :span="6">
					<a-form-item label="规则名称" name="name">
						<a-input v-model:value="ruleSearchFormState.name" placeholder="请输入规则名称"/>
					</a-form-item>
				</a-col>
				<a-col :span="6">
					<a-button type="primary" @click="ruleTableRef.refresh()">查询</a-button>
					<a-button style="margin: 0 8px" @click="ruleReset">重置</a-button>
				</a-col>
			</a-row>
		</a-form>
		<a-divider class="m-3 mx-0"/>

		<s-table
			ref="ruleTableRef"
			:columns="ruleColumns"
			:data="loadRuleData"
			:alert="ruleOptions.alert.show"
			bordered
			:row-key="(record) => record.id"
			:tool-config="toolConfig"
			:row-selection="ruleOptions.rowSelection"
		>
			<template #operator class="table-operator">
				<a-space>
					<a-button type="primary" @click="ruleFormRef.onOpen(undefined, categoryType)">
						<template #icon>
							<plus-outlined/>
						</template>
						新增
					</a-button>
					<sys-batch-button
						buttonName="批量删除"
						icon="DeleteOutlined"
						buttonDanger
						:selectedRowKeys="selectedRuleRowKeys"
						@batchCallBack="deleteBatchRule"
					/>
				</a-space>
			</template>
			<template #bodyCell="{ column, record }">
				<template v-if="column.dataIndex === 'field'">
					<a-tag color="orange">{{ $TOOL.dictTypeData('RULE_FIELD', record.field) }}</a-tag>
				</template>
				<template v-if="column.dataIndex === 'action'">
					<a-space>
						<a @click="ruleFormRef.onOpen(record, categoryType)">编辑</a>
						<a-divider type="vertical"/>
						<a @click="ruleDetailRef.onOpen(record, categoryType)">详情</a>
						<a-divider type="vertical"/>
						<a-popconfirm title="确定要删除吗？" @confirm="deleteRule(record)">
							<a-button type="link" danger size="small">删除</a-button>
						</a-popconfirm>
					</a-space>
				</template>
			</template>
		</s-table>
	</a-card>
	<Form ref="ruleFormRef" @successful="ruleTableRef.refresh()"/>
	<Detail ref="ruleDetailRef" @successful="ruleTableRef.refresh()"/>
</template>

<script setup name="ruleCategoryIndex">
import Form from './form.vue'
import Detail from './detail.vue'
import ubaRuleApi from '@/api/uba/ruleApi.js'
const props = defineProps({
	type: {
		type: String,
		default: 'ACCESS_LOG'
	}
})
const categoryType = computed(() => {
	return props.type
})
// ------ 清洗规则 ------
const ruleSearchFormState = ref({})
const ruleSearchFormRef = ref()
const ruleTableRef = ref()
const ruleFormRef = ref()
const ruleDetailRef = ref()
const ruleColumns = [
	{
		title: '规则名称',
		dataIndex: 'name'
	},
	{
		title: '清洗字段',
		dataIndex: 'field'
	},
	{
		title: '排序码',
		dataIndex: 'sortCode'
	},
	{
		title: '操作',
		dataIndex: 'action',
		align: 'center',
		width: 220
	}
]
const selectedRuleRowKeys = ref([])
const toolConfig = {refresh: true, height: true, columnSetting: true, striped: false}
// 列表选择配置
const ruleOptions = {
	// columns数字类型字段加入 needTotal: true 可以勾选自动算账
	alert: {
		show: false,
		clear: () => {
			selectedRuleRowKeys.value = ref([])
		}
	},
	rowSelection: {
		onChange: (selectedRowKey, selectedRows) => {
			selectedRuleRowKeys.value = selectedRowKey
		}
	}
}
// 加载清洗规则数据
const loadRuleData = (parameter) => {
	parameter.category = categoryType.value
	return ubaRuleApi.rulePage(Object.assign(parameter, ruleSearchFormState.value)).then((data) => {
		return data
	})
}
// 重置清洗规则查询
const ruleReset = () => {
	ruleSearchFormRef.value.resetFields()
	ruleTableRef.value.refresh(true)
}
// 删除数据清洗规则
const deleteRule = (record) => {
	let params = [
		{
			id: record.id
		}
	]
	ubaRuleApi.ruleDelete(params).then(() => {
		ruleTableRef.value.refresh(true)
	})
}
// 批量删除
const deleteBatchRule = (params) => {
	ubaRuleApi.ruleDelete(params).then(() => {
		ruleTableRef.value.clearRefreshSelected()
	})
}
</script>
