<template>
	<a-card>
		<a-tabs size="large" v-model:activeKey="activeKey">
			<a-tab-pane v-for="item in tabListNoTitle" :key="item.key" :tab="item.tab">
				<category :type="item.key"/>
			</a-tab-pane>
		</a-tabs>
	</a-card>
</template>

<script setup name="ubaDict">
import Category from './category/index.vue'

const activeKey = ref('ACCESS_LOG')
const tabListNoTitle = ref([
	{key: 'ACCESS_LOG', tab: '访问日志字典'},
	{key: 'TRACKING', tab: '埋点字典'}
])
</script>

<style lang="less" scoped>
:deep(.ant-card-body) {
	padding-top: 0 !important;
}

:deep(.ant-tabs-tab) {
	font-size: 14px !important;
}
</style>
