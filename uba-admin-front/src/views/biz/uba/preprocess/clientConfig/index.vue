<template>
	<a-tabs v-model:activeKey="activeKey" tab-position="left">
		<a-tab-pane key="ipForm" tab="IP地址">
			<ip-form/>
		</a-tab-pane>
		<a-tab-pane key="uaForm" tab="浏览器标识">
			<ua-form/>
		</a-tab-pane>
	</a-tabs>
</template>

<script setup name="requestConfig">
import IpForm from './ipForm.vue'
import UaForm from './uaForm.vue'

const activeKey = ref('ipForm')
</script>
