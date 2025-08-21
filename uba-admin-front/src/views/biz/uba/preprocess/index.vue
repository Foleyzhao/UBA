<template>
	<a-card
		class="sys-wd"
		:bordered="false"
		:tab-list="tabListNoTitle"
		:active-tab-key="noTitleKey"
		@tabChange="(key) => onTabChange(key, 'noTitleKey')"
	>
		<p v-if="noTitleKey === 'requestConfig'">
			<RequestConfig/>
		</p>
		<p v-else-if="noTitleKey === 'clientConfig'">
			<ClientConfig/>
		</p>
	</a-card>
</template>

<script setup name="ubaPreprocess">
import RequestConfig from '@/views/biz/uba/preprocess/requestConfig/index.vue'
import ClientConfig from '@/views/biz/uba/preprocess/clientConfig/index.vue'


const key = ref('requestConfig')
const noTitleKey = ref('requestConfig')

const tabListNoTitle = [
	{
		key: 'requestConfig',
		tab: '请求过滤'
	},
	{
		key: 'clientConfig',
		tab: '客户端过滤'
	},
]

const onTabChange = (value, type) => {
	if (type === 'key') {
		key.value = value
	} else if (type === 'noTitleKey') {
		noTitleKey.value = value
	}
}
</script>
<style lang="less" scoped>
:deep(.ant-tabs-tab) {
	font-size: 14px !important;
}
</style>
