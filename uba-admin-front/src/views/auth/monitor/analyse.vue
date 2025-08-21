<template>
	<div class="sys-pb10">
		<a-row :gutter="16">
			<a-col :span="6">
				<a-card class="system-monitor-card" :bordered="false">
					<template #cover>
						<team-outlined style="color: #69c0ff" class="system-monitor-card-icon"/>
						<div class="system-monitor-card-div">
							<span class="system-monitor-card-span">当前会话数：</span
							><span class="system-monitor-card-span">{{ analysisObj.currentSessionTotalCount }}</span>
						</div>
					</template>
				</a-card>
			</a-col>
			<a-col :span="6">
				<a-card class="system-monitor-card" :bordered="false">
					<template #cover>
						<verified-outlined class="system-monitor-card-icon sys-color-ff9c6e"/>
						<div class="system-monitor-card-div">
							<span class="system-monitor-card-span">最大签发令牌：</span
							><span class="system-monitor-card-span">{{ analysisObj.maxTokenCount }}</span>
						</div>
					</template>
				</a-card>
			</a-col>
			<a-col :span="6">
				<a-card class="system-monitor-card" :bordered="false">
					<template #cover>
						<rise-outlined class="system-monitor-card-icon sys-color-ff85c0"/>
						<div class="system-monitor-card-div">
							<span class="system-monitor-card-span">1小时内新增：</span
							><span class="system-monitor-card-span">{{ analysisObj.oneHourNewlyAdded }}</span>
						</div>
					</template>
				</a-card>
			</a-col>
			<a-col :span="6">
				<a-card class="system-monitor-card" :bordered="false">
					<template #cover>
						<pie-chart-outlined class="system-monitor-card-icon sys-color-5cdbd3"/>
						<div class="system-monitor-card-div">
							<span class="system-monitor-card-span">B/C端占比：</span
							><span class="system-monitor-card-span">{{ analysisObj.proportionOfBAndC }}</span>
						</div>
					</template>
				</a-card>
			</a-col>
		</a-row>
	</div>
</template>

<script setup name="monitorAnalyse">
import monitorApi from '@/api/auth/monitorApi'

// 预置空数据
const analysisObj = ref({
	currentSessionTotalCount: '0',
	maxTokenCount: '0',
	oneHourNewlyAdded: '0',
	proportionOfBAndC: '0/0'
})
monitorApi.monitorAnalysis().then((res) => {
	analysisObj.value = res
})
</script>

<style scoped>
.system-monitor-card {
	height: 100px;
}

.system-monitor-card-icon {
	font-size: 30px;
	padding-top: 18px;
	padding-bottom: 5px;
}

.system-monitor-card-div {
	display: flex;
	justify-content: center;
}

.system-monitor-card-span {
	font-size: 16px;
}

.sys-color-ff9c6e {
	color: #ff9c6e;
}

.sys-color-ff85c0 {
	color: #ff85c0;
}

.sys-color-5cdbd3 {
	color: #5cdbd3;
}
</style>
