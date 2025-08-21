<template>
	<a-card :bordered="false">
		<div class="sys-card-line">
			<div class="sys-flex">
				<a-avatar class="sys-wh60" :src="userInfo.avatar"
						  :size="{ xs: 24, sm: 32, md: 40, lg: 64, xl: 80, xxl: 100 }"/>
				<div class="system-index-card-left-one-username">
					<span>{{ userInfo.name }}</span>
					<span>{{ userInfo.orgName }} | {{ userInfo.positionName }}</span>
				</div>
			</div>
			<span class="system-index-userinfo-time">
				{{ currentTime }}
			</span>
		</div>
	</a-card>
</template>

<script setup name="userInfo">
import dayjs from 'dayjs'
import weekday from 'dayjs/plugin/weekday'
import localeData from 'dayjs/plugin/localeData'

dayjs.extend(weekday)
dayjs.extend(localeData)

import {onBeforeUnmount} from 'vue'
import tool from '@/utils/tool'

const userInfo = tool.data.get('USER_INFO')

const currentTime = ref(dayjs().format('YYYY年MM月DD日 HH时mm分ss秒'))
// 运行定时器，一秒获取一次
const interval = window.setInterval(() => {
	currentTime.value = dayjs().format('YYYY年MM月DD日 HH时mm分ss秒')
}, 1000)
// 这个界面不在我们视线中的时候，关闭定时器
onBeforeUnmount(() => {
	window.clearInterval(interval)
})
</script>

<style scoped>
.sys-card-line {
	display: flex;
	justify-content: space-between;
}

.sys-wh60 {
	width: 60px;
	height: 60px;
}

.system-index-card-left-one-username {
	margin-left: 8px;
	display: flex;
	flex-direction: column;
	justify-content: center;
}

.system-index-card-left-one-username > span:nth-child(1) {
	font-weight: 600;
	margin: 2px;
	font-size: 18px;
}

.system-index-card-left-one-username > span:nth-child(2) {
	color: #6d737b;
	margin: 2px;
}

.system-index-userinfo-time {
	margin: 2px;
}

@media (max-width: 992px) {
	.system-index-userinfo-time {
		display: none;
	}
}

.sys-flex {
	display: flex;
}
</style>
