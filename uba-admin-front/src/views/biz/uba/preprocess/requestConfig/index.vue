<template>
	<a-tabs v-model:activeKey="activeKey" tab-position="left">
		<a-tab-pane key="refererForm" tab="请求来源">
			<referer-form/>
		</a-tab-pane>
		<a-tab-pane key="uriForm" tab="请求路径">
			<uri-form/>
		</a-tab-pane>
	</a-tabs>
</template>

<script setup name="requestConfig">
import RefererForm from './refererForm.vue'
import UriForm from './uriForm.vue'

const activeKey = ref('refererForm')
</script>
