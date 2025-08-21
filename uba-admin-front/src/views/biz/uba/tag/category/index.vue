<template>
	<a-row :gutter="10">
		<a-col :xs="24" :sm="24" :md="24" :lg="5" :xl="5">
			<div class="left-tree-container">
				<a-tree
					v-if="treeData.length > 0"
					v-model:expandedKeys="defaultExpandedKeys"
					:tree-data="treeData"
					:field-names="treeFieldNames"
					@select="treeSelect"
				>
				</a-tree>
				<a-empty v-else :image="Empty.PRESENTED_IMAGE_SIMPLE"/>
			</div>
		</a-col>
		<a-col :xs="24" :sm="24" :md="24" :lg="19" :xl="19">
			<a-form ref="searchFormRef" name="advanced_search" class="ant-advanced-search-form mb-3"
					:model="searchFormState">
				<a-row :gutter="24">
					<a-col :span="8">
						<a-form-item name="searchKey" label="标签名称">
							<a-input v-model:value="searchFormState.searchKey" placeholder="请输入标签名称"/>
						</a-form-item>
					</a-col>
					<a-col :span="8">
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
			<a-divider class="m-3 mx-0"/>
			<s-table
				ref="tableRef"
				:columns="columns"
				:data="loadData"
				:expand-row-by-click="true"
				bordered
				:tool-config="toolConfig"
				:row-key="(record) => record.id"
			>
				<template #operator class="table-operator">
					<a-button type="primary" @click="formRef.onOpen(undefined, categoryType, searchFormState.parentId)">
						<template #icon>
							<plus-outlined/>
						</template>
						新增
					</a-button>
				</template>
				<template #bodyCell="{ column, record }">
					<template v-if="column.dataIndex === 'level'">
						<a-tag color="green">{{ $TOOL.dictTypeData('TAG_LEVEL', record.level) }}</a-tag>
					</template>
					<template v-if="column.dataIndex === 'type'">
						<div v-if='record.level!=="LEVEL_THREE"'>-</div>
						<a-tag color="blue" v-if='record.level==="LEVEL_THREE"'>{{ $TOOL.dictTypeData('TAG_TYPE', record.type) }}</a-tag>
					</template>
					<template v-if="column.dataIndex === 'tagSource'">
						<div v-if='record.level!=="LEVEL_THREE"'>-</div>
						<a-tag color="blue" v-if='record.level==="LEVEL_THREE"'>{{ $TOOL.dictTypeData('TAG_SOURCE', record.tagSource) }}</a-tag>
					</template>
					<template v-if="column.dataIndex === 'action'">
						<a @click="formRef.onOpen(record, categoryType)">编辑</a>
						<a-divider type="vertical"/>
						<a-popconfirm title="删除此标签与下级标签吗？" @confirm="remove(record)">
							<a-button type="link" danger size="small">删除</a-button>
						</a-popconfirm>
					</template>
				</template>
			</s-table>
		</a-col>
	</a-row>
	<Form ref="formRef" @successful="formSuccessful()"/>
</template>

<script setup name="tagCategoryIndex">
import {Empty} from 'ant-design-vue'
import tagApi from '@/api/uba/tagApi'
import Form from './form.vue'
import tool from '@/utils/tool'

const props = defineProps({
	type: {
		type: String,
		default: 'USER_TAG'
	}
})
const columns = [
	{
		title: '标签级别',
		dataIndex: 'level',
		width: 100
	},
	{
		title: '标签类型',
		dataIndex: 'type',
		width: 100
	},
	{
		title: '标签来源',
		dataIndex: 'tagSource',
		width: 100
	},
	{
		title: '标签名称',
		dataIndex: 'tagName',
		width: 300
	},
	{
		title: '标签描述',
		dataIndex: 'tagDesc',
		width: 500
	},
	{
		title: '排序',
		dataIndex: 'sortCode'
	},
	{
		title: '操作',
		dataIndex: 'action',
		align: 'center',
		width: '150px'
	}
]
const categoryType = computed(() => {
	return props.type
})
// 定义tableDOM
const tableRef = ref(null)
const formRef = ref()
const searchFormRef = ref()
const searchFormState = ref({})
// 默认展开的节点
let defaultExpandedKeys = ref([])
const treeData = ref([])
// 替换treeNode 中 title,key,children
const treeFieldNames = {children: 'children', title: 'tagName', key: 'id'}
const toolConfig = {refresh: true, height: true, columnSetting: true, striped: false}

// 表格查询 返回 Promise 对象
const loadData = (parameter) => {
	loadTreeData()
	parameter.category = categoryType.value
	return tagApi.tagPage(Object.assign(parameter, searchFormState.value)).then((data) => {
		return data
	})
}
// 重置
const reset = () => {
	searchFormRef.value.resetFields()
	tableRef.value.refresh(true)
}
// 加载左侧的树
const loadTreeData = () => {
	const param = {
		category: categoryType.value
	}
	tagApi.tagTree(param).then((res) => {
		if (res) {
			treeData.value = res
			// 默认展开第一/二级标签
			res.forEach(item => {
				defaultExpandedKeys.value.push(item.id)
				if (item.children.length > 0) {
					item.children.forEach(childrenItem => {
						defaultExpandedKeys.value.push(childrenItem.id)
					})
				}
			})
		}
	})
}
// 点击树查询
const treeSelect = (selectedKeys) => {
	if (selectedKeys && selectedKeys.length > 0) {
		searchFormState.value.parentId = selectedKeys.toString()
	} else {
		delete searchFormState.value.parentId
	}
	tableRef.value.refresh(true)
}
// 删除
const remove = (record) => {
	let params = [
		{
			id: record.id
		}
	]
	tagApi.tagDelete(params).then(() => {
		tableRef.value.refresh()
		refreshStoreTag()
	})
}
// 表单界面回调
const formSuccessful = () => {
	tableRef.value.refresh()
	refreshStoreTag()
}
// 刷新store中的标签
const refreshStoreTag = () => {
	tagApi.tagTree().then((res) => {
		tool.data.set('TAG_TREE_DATA', res)
	})
}
</script>

<style scoped lang="less">
.ant-form-item {
	margin-bottom: 0 !important;
}

.system-button-left {
	margin-left: 8px;
}
</style>
