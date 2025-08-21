<template>
	<a-card>
		<sys-editor v-model="html" placeholder="请输入" :height="400"></sys-editor>
	</a-card>
</template>

<script setup name="exmEditor">
import SysEditor from '@/components/Editor/index.vue'

const html = ref('')
</script>
