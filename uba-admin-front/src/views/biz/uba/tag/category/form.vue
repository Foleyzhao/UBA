<template>
	<sys-form-container
		:title="formData.id ? '编辑标签' : '增加标签'"
		:width="550"
		:visible="visible"
		:destroy-on-close="true"
		@close="onClose"
	>
		<a-form ref="formRef" :model="formData" :rules="formRules" layout="vertical" :label-col="labelCol">
			<a-form-item label="上级标签：" name="parentId">
				<a-tree-select
					v-model:value="formData.parentId"
					v-model:treeExpandedKeys="defaultExpandedKeys"
					class="sys-wd"
					:dropdown-style="{ maxHeight: '400px', overflow: 'auto' }"
					placeholder="请选择上级标签"
					allow-clear
					:tree-data="treeData"
					:field-names="{
						children: 'children',
						label: 'name',
						value: 'id',
					}"
					selectable="false"
					:default-expand-all="true"
					treeLine
					@change="onChange"
				>
				</a-tree-select>
			</a-form-item>
			<a-form-item label="标签级别：" name="level">
				<a-select
					v-model:value="formData.level"
					placeholder="请选择标签级别"
					:options="levelOptions"
					:disabled="true"
				/>
			</a-form-item>
			<a-form-item label="标签类别：" name="type" v-show="formData.level === 'LEVEL_THREE'">
				<a-select
					v-model:value="formData.type"
					placeholder="请选择标签类别"
					:options="typeOptions"
				/>
			</a-form-item>
			<a-form-item label="标签来源：" name="tagSource" v-show="formData.level === 'LEVEL_THREE'">
				<a-select
					v-model:value="formData.tagSource"
					placeholder="请选择标签来源"
					:options="sourceOptions"
				/>
			</a-form-item>
			<a-form-item label="标签名称：" name="tagName">
				<a-input
					v-model:value="formData.tagName"
					placeholder="请输入标签名称"
					allow-clear
				/>
			</a-form-item>
			<a-form-item label="标签描述：" name="tagDesc">
				<a-input
					v-model:value="formData.tagDesc"
					placeholder="请输入标签描述"
					allow-clear
				/>
			</a-form-item>
			<a-form-item label="排序：" name="sortCode">
				<a-input-number
					class="sys-wd"
					v-model:value="formData.sortCode"
					:max="1000"
				/>
			</a-form-item>
		</a-form>
		<template #footer>
			<a-button class="sys-mr8" @click="onClose">关闭</a-button>
			<a-button type="primary" @click="onSubmit">保存</a-button>
		</template>
	</sys-form-container>
</template>

<script setup name="tagForm">
import {required} from '@/utils/formRules'
import tagApi from '@/api/uba/tagApi'
import tool from '@/utils/tool'
// 标签级别
const levelOptions = tool.dictList('TAG_LEVEL')
// 标签来源
const sourceOptions = tool.dictList('TAG_SOURCE')
// 标签类别
const typeOptions = tool.dictList('TAG_TYPE')
// 定义emit事件
const emit = defineEmits({successful: null})
// 默认是关闭状态
const visible = ref(false)
const formRef = ref()
// 表单数据
let formData = ref({})
// 定义树元素
const treeData = ref([])
// 默认展开的节点(顶级)
const defaultExpandedKeys = ref([0])

const onChange = (value, label, extra) => {
	const nodeData = extra.triggerNode ? extra.triggerNode.props : null;
	const parentId = nodeData ? nodeData.parentId : null;
	if (value === 0) {
		formData.value.level = 'LEVEL_ONE';
	} else if (parentId === '0') {
		formData.value.level = 'LEVEL_TWO';

	} else {
		formData.value.level = 'LEVEL_THREE';
	}
	// 默认值
	formData.value.type = 'STATIC_TAG';
	formData.value.tagSource = 'STATIC';
}

// 打开抽屉
const onOpen = (record, type, parentId) => {
	visible.value = true
	formData.value = {
		sortCode: 99,
		category: type
	}
	if (parentId) {
		formData.value.parentId = parentId
		const idParam = {
			id: parentId
		}
		tagApi.tagDetail(idParam).then((res) => {
			if (res.level === 'LEVEL_ONE') {
				formData.value.level = 'LEVEL_TWO';
			} else {
				formData.value.level = 'LEVEL_THREE';
			}
			// 默认值
			formData.value.type = 'STATIC_TAG';
			formData.value.tagSource = 'STATIC';
		})
	}
	if (record) {
		formData.value = Object.assign({}, record)
		formData.value.category = type
	}
	// 获取树并加入顶级
	const treeParam = {
		category: type
	}
	tagApi.tagTree(treeParam).then((res) => {
		treeData.value = [
			{
				id: 0,
				parentId: '-1',
				name: '顶级',
				children: res
			}
		]
		// 默认展开第一/二级标签
		res.forEach(item => {
			defaultExpandedKeys.value.push(item.id)
		})
	})
}
// 关闭抽屉
const onClose = () => {
	visible.value = false
}
// 默认要校验的
const formRules = {
	parentId: [required('请选择上级标签')],
	level: [required('请选择标签级别')],
	type: [required('请选择类别')],
	tagSource: [required('请选择标签来源')],
	tagName: [required('请输入标签名称')],
	tagDesc: [required('请输入标签描述')],
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
			tagApi.submitForm(formData.value, formData.value.id).then(() => {
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
