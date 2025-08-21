<template>
	<a-row :gutter="10">
		<a-col :xs="24" :sm="24" :md="24" :lg="19" :xl="24">
			<a-card :bordered="false" class="sys-mb10">
				<a-form ref="searchFormRef" name="advanced_search" class="ant-advanced-search-form"
						:model="searchFormState">
					<a-row :gutter="24">
						<a-col :span="8">
							<a-form-item name="searchKey" label="账号">
								<a-input
									v-model:value="searchFormState.searchKey"
									placeholder="请输入账号"
								/>
							</a-form-item>
						</a-col>
						<a-col :span="8">
							<a-form-item name="userStatus" :label="$t('user.userStatus')">
								<a-select
									v-model:value="searchFormState.userStatus"
									:placeholder="$t('user.placeholderUserStatus')"
									:getPopupContainer="(trigger) => trigger.parentNode"
								>
									<a-select-option v-for="item in statusData" :key="item.value" :value="item.value">{{
											item.label
										}}
									</a-select-option>
								</a-select>
							</a-form-item>
						</a-col>
						<a-col :span="8">
							<a-button type="primary" @click="tableRef.refresh(true)">
								<template #icon>
									<SearchOutlined/>
								</template>
								{{ $t('common.searchButton') }}
							</a-button>
							<a-button class="system-button-left" @click="reset">
								<template #icon>
									<redo-outlined/>
								</template>
								{{ $t('common.resetButton') }}
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
					bordered
					:alert="options.alert.show"
					:row-key="(record) => record.id"
					:row-selection="options.rowSelection"
				>
					<template #operator class="table-operator">
						<a-space>
							<a-button type="primary" @click="formRef.onOpen(undefined)">
								<template #icon>
									<plus-outlined/>
								</template>
								<span>{{ $t('common.addButton') }}</span>
							</a-button>
							<sys-batch-button
								:buttonName="$t('common.batchRemoveButton')"
								icon="DeleteOutlined"
								buttonDanger
								:selectedRowKeys="selectedRowKeys"
								@batchCallBack="deleteBatchUser"
							/>
						</a-space>
					</template>
					<template #bodyCell="{ column, record }">
						<template v-if="column.dataIndex === 'userStatus'">
							<a-switch :loading="loading" :checked="record.userStatus === 'ENABLE'"
									  @change="editStatus(record)"/>
						</template>
						<template v-if="column.dataIndex === 'source'">
							{{ $TOOL.dictTypeData('UBA_USER_SOURCE', record.source) }}
						</template>
						<template v-if="column.dataIndex === 'action'">
							<a @click="formRef.onOpen(record)">{{ $t('common.editButton') }}</a>
							<a-divider type="vertical"/>
							<a-popconfirm :title="$t('user.popconfirmDeleteUser')" placement="topRight"
										  @confirm="removeUser(record)">
								<a-button type="link" danger size="small">
									{{ $t('common.removeButton') }}
								</a-button>
							</a-popconfirm>
<!--							<a-divider type="vertical"/>
							<a-dropdown>
								<a class="ant-dropdown-link">
									{{ $t('common.more') }}
									<DownOutlined/>
								</a>
								<template #overlay>
									<a-menu>
										<a-menu-item>
											<a @click="addTag(record)">{{ "添加标签" }}</a>
										</a-menu-item>
									</a-menu>
								</template>
							</a-dropdown>-->
						</template>
					</template>
				</s-table>
			</a-card>
		</a-col>
	</a-row>
	<Form ref="formRef" @successful="tableRef.refresh()"/>
</template>

<script setup name="ubaUser">
import tool from '@/utils/tool'
import userApi from '@/api/uba/userApi'
import Form from './form.vue'

const columns = [
	{
		title: '账号',
		dataIndex: 'account',
		ellipsis: true
	},
	{
		title: '昵称',
		dataIndex: 'nickname',
		ellipsis: true
	},
	{
		title: '来源类型',
		dataIndex: 'source',
		ellipsis: true
	},
	{
		title: '上次操作时间',
		dataIndex: 'lastOperateTime',
		ellipsis: true
	},
	{
		title: '状态',
		dataIndex: 'userStatus',
		width: '80px'
	},
	{
		title: '操作',
		dataIndex: 'action',
		align: 'center',
		width: '220px'
	}
]
const statusData = tool.dictList('COMMON_STATUS')
const searchFormRef = ref()
const searchFormState = ref({})
const tableRef = ref(null)
const selectedRowKeys = ref([])
const formRef = ref(null)
const loading = ref(false)
// 表格查询 返回 Promise 对象
const loadData = (parameter) => {
	return userApi.userPage(Object.assign(parameter, searchFormState.value)).then((res) => {
		return res
	})
}
// 列表选择配置
const options = {
	alert: {
		show: false,
		clear: () => {
			selectedRowKeys.value = ref([])
		}
	},
	rowSelection: {
		onChange: (selectedRowKey, selectedRows) => {
			selectedRowKeys.value = selectedRowKey
		}
	}
}
// 重置
const reset = () => {
	searchFormRef.value.resetFields()
	tableRef.value.refresh(true)
}
// 修改状态
const editStatus = (record) => {
	loading.value = true
	if (record.userStatus === 'ENABLE') {
		userApi
			.userDisableUser(record)
			.then(() => {
				tableRef.value.refresh()
			})
			.finally(() => {
				loading.value = false
			})
	} else {
		userApi
			.userEnableUser(record)
			.then(() => {
				tableRef.value.refresh()
			})
			.finally(() => {
				loading.value = false
			})
	}
}
// 删除用户
const removeUser = (record) => {
	let params = [
		{
			id: record.id
		}
	]
	userApi.userDelete(params).then(() => {
		tableRef.value.refresh()
	})
}
// 批量删除
const deleteBatchUser = (params) => {
	userApi.userDelete(params).then(() => {
		tableRef.value.clearRefreshSelected()
	})
}
</script>

<style scoped>
.ant-form-item {
	margin-bottom: 0 !important;
}

.system-table-avatar {
	margin-top: -10px;
	margin-bottom: -10px;
}

.system-button-left {
	margin-left: 8px;
}
</style>
