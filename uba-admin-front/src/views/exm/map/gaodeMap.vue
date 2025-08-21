<template>
	<gaode-map
		ref="map"
		api-key="89aeebd6e5c1185879cd465bd7337542"
		@complete="handleComplete"
		@marker-click="handleMarkerClick"
	/>
</template>

<script setup name="exmGaodeMap">
import GaodeMap from '@/components/Map/gaodeMap/index.vue'
// 使用该组件请再高德地图官网注册，申请应用获得key
const map = ref(null)

const handleComplete = () => {
	// 点标记
	// map.value.renderMarker(
	// 	[
	// 		{
	// 			position: [116.39, 39.9],
	// 			title: 'TA',
	// 			content: 'CA',
	// 			label: {
	// 				content: 'LCA'
	// 			}
	// 		},
	// 		{
	// 			position: [116.33, 39.5],
	// 			title: 'TB',
	// 			icon: '//vdata.amap.com/icons/b18/1/2.png'
	// 		}
	// 	]
	// )
	// 圆点标记
	// map.value.renderCircleMarker(
	// 	[
	// 		{
	// 			position: [116.39, 39.9],
	// 			radius: 30,
	// 			strokeColor: 'green',
	// 			fillColor: 'green'
	// 		},
	// 		{
	// 			position: [116.33, 39.5],
	// 			radius: 10,
	// 			strokeColor: 'orange',
	// 			fillColor: 'orange'
	// 		}
	// 	]
	// )
	// 简单点标记
	// map.value.renderSimpleMarker(
	// 	[
	// 		{
	// 			position: [116.39, 39.9],
	// 			label: 'A',
	// 			labelStyle: {
	// 				color: '#333',
	// 				fontSize: '15px'
	// 			},
	// 			style: 'green'
	// 		},
	// 		{
	// 			position: [116.33, 39.5],
	// 			label: 'B',
	// 			labelStyle: {
	// 				color: '#555',
	// 				fontSize: '15px'
	// 			},
	// 			style: 'orange'
	// 		}
	// 	]
	// )
	// 字体点标记
	// map.value.renderAwesomeMarker(
	// 	[
	// 		{
	// 			position: [116.39, 39.9],
	// 			awesomeIcon: 'address-book-o',
	// 			labelStyle: {
	// 				color: '#333',
	// 				fontSize: '15px'
	// 			},
	// 			style: 'green'
	// 		},
	// 		{
	// 			position: [116.33, 39.5],
	// 			awesomeIcon: 'anchor',
	// 			labelStyle: {
	// 				color: '#333',
	// 				fontSize: '15px'
	// 			},
	// 			style: 'orange'
	// 		}
	// 	]
	// )
	// 面
	// map.value.renderPolygon(
	// 	[
	// 		{
	// 			position: [116.39, 39.9]
	// 		},
	// 		{
	// 			position: [116.47, 39.8]
	// 		},
	// 		{
	// 			position: [116.46, 39.7]
	// 		},
	// 		{
	// 			position: [116.35, 39.6]
	// 		}
	// 	]
	// )
	// 信息窗体
	map.value.renderInfoWindow([
		{
			position: [120.20, 30.30],
			content: [
				"<div style='padding:0'><b>UBA-用户行为分析系统</b>",
				'网站 : https://www.huanniankj.com',
				'UBA是一款用户行为分析系统，采用Vue3.0+AntDesignVue3.0+SpringBoot2.8前后分离技术打造</div>'
			]
		},
		{
			position: [116.33, 39.5],
			content: [
				"<div style='padding:0'><b>UBA-用户行为分析系统</b>",
				'网站 : https://www.huanniankj.com',
				'UBA是一款用户行为分析系统，采用Vue3.0+AntDesignVue3.0+SpringBoot2.8前后分离技术打造</div>'
			]
		}
	])
}

const handleMarkerClick = (position) => {
	map.value.openInfoWindow(position)
}
</script>
