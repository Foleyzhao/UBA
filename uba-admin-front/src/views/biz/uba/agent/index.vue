<template>
	<a-card :bordered="false" class="sys-mb10">
		<a-form ref="searchFormRef" name="advanced_search" class="ant-advanced-search-form" :model="searchFormState">
			<a-row :gutter="24">
				<a-col :span="6">
					<a-form-item name="agentId" label="内网IP">
						<a-input v-model:value="searchFormState.hostIp" placeholder="请输入内网IP"></a-input>
					</a-form-item>
				</a-col>
				<a-col :span="6">
					<a-form-item name="ip" label="外网IP">
						<a-input v-model:value="searchFormState.publicIp" placeholder="请输入外网IP"></a-input>
					</a-form-item>
				</a-col>
				<a-col :span="6">
					<a-form-item name="engine" label="状态">
						<a-select
							v-model:value="searchFormState.status"
							:options="statusOptions"
							placeholder="请选择状态"
							:getPopupContainer="(trigger) => trigger.parentNode"
							allow-clear
						></a-select>
					</a-form-item>
				</a-col>
				<a-col :span="6">
					<a-button type="primary" @click="tableRef.refresh(true)">
						<template #icon>
							<SearchOutlined/>
						</template>
						查询
					</a-button>
					<a-button class="system-button-left" @click="reset">
						<template #icon>
							<redo-outlined/>
						</template>
						重置
					</a-button>
				</a-col>
			</a-row>
		</a-form>
	</a-card>
	<a-card :bordered="false">
		<s-table
			ref="tableRef"
			:columns="columns"
			:data="loadData"
			:expand-row-by-click="true"
			:alert="options.alert.show"
			bordered
			:row-key="(record) => record.id"
			:row-selection="options.rowSelection"
		>
			<template #operator class="table-operator">
				<a-space>
					<a-button danger @click="deleteBatchAgent()">删除</a-button>
				</a-space>
			</template>
			<template #bodyCell="{ column, record }">
				<template v-if="column.dataIndex === 'status'">
					{{ $TOOL.dictTypeData('AGENT_STATUS', record.status) }}
				</template>
				<template v-if="column.dataIndex === 'action'">
					<a @click="detailRef.onOpen(record)">详情</a>
					<a-divider type="vertical"/>
					<a-popconfirm title="删除此采集器？" @confirm="deleteAgent(record)">
						<a-button type="link" danger size="small">删除</a-button>
					</a-popconfirm>
				</template>
			</template>
		</s-table>
	</a-card>
	<Form ref="formRef" @successful="tableRef.refresh(true)"/>
	<detail ref="detailRef"/>
</template>

<script setup name="ubaAgent">
import {message} from 'ant-design-vue'
import agentApi from '@/api/uba/agentApi'
import Detail from './detail.vue'
import tool from "@/utils/tool";

const tableRef = ref(null)
const formRef = ref()
const searchFormRef = ref()
const searchFormState = ref({})
const detailRef = ref()

const columns = [
	{
		title: 'agent ID',
		dataIndex: 'agentId',
		ellipsis: true
	},
	{
		title: '状态',
		dataIndex: 'status',
		ellipsis: true
	},
	{
		title: '操作',
		dataIndex: 'action',
		align: 'center',
		width: '150px'
	}
]
let selectedRowKeys = ref([])
// 列表选择配置
const options = {
	alert: {
		show: false,
		clear: () => {
			selectedRowKeys = ref([])
		}
	},
	rowSelection: {
		onChange: (selectedRowKey, selectedRows) => {
			selectedRowKeys.value = selectedRowKey
		}
	}
}
// 表格查询 返回 Promise 对象
const loadData = (parameter) => {
	return agentApi.agentPage(Object.assign(parameter, searchFormState.value)).then((data) => {
		return data
	})
}
// 重置
const reset = () => {
	searchFormRef.value.resetFields()
	tableRef.value.refresh(true)
}
const statusOptions = tool.dictList('AGENT_STATUS')
// 删除
const deleteAgent = (record) => {
	let params = [
		{
			id: record.id
		}
	]
	agentApi.agentDelete(params).then(() => {
		tableRef.value.refresh(true)
	})
}
// 批量删除
const deleteBatchAgent = () => {
	if (selectedRowKeys.value.length < 1) {
		message.warning('请选择一条或多条数据')
		return false
	}
	const params = selectedRowKeys.value.map((m) => {
		return {
			id: m
		}
	})
	agentApi.agentDelete(params).then(() => {
		tableRef.value.clearRefreshSelected()
	})
}
</script>

<style scoped>
.ant-form-item {
	margin-bottom: 0 !important;
}

.system-button-left {
	margin-left: 8px;
}
</style>
