<template>
	<a-card>
		<a-tabs size="large" v-model:activeKey="activeKey">
			<a-tab-pane v-for="item in tabListNoTitle" :key="item.key" :tab="item.tab">
				<category :type="item.key"/>
			</a-tab-pane>
		</a-tabs>
	</a-card>
</template>

<script setup name="ubaTag">
import Category from './category/index.vue'

const activeKey = ref('USER_TAG')
const tabListNoTitle = ref([
	{key: 'USER_TAG', tab: '用户标签'}
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
