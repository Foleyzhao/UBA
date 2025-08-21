<template>
	<a-layout>
		<a-layout class="layout">
			<div id="systemHeader" class="system-header top-system-header sys-pd050" v-show="displayLayout">
				<div class="system-header-left sys-pl0">
					<header id="systemHeaderLogo" class="system-header-logo">
						<div class="system-header-left">
							<div class="logo-bar">
								<img class="logo" :src="sysBaseConfig.SYSTEM_SYS_LOGO"/>
								<span>{{ sysBaseConfig.SYSTEM_SYS_NAME }}</span>
							</div>
						</div>
					</header>
				</div>
				<moduleMenu v-if="moduleMenuShow" @switchModule="switchModule" class="sys-pdl25"/>
				<div class="sys-navmenu-line" id="sys-line-nav">
					<a-menu
						class="sys-bb0"
						id="topHeaderMenu"
						:selectedKeys="selectedKeys"
						:theme="sideTheme"
						mode="horizontal"
						@select="onSelect"
						@openChange="onOpenChange"
						collapsed="true"
					>
						<NavMenu :nav-menus="menuList"/>
					</a-menu>
				</div>
				<div class="system-header-right">
					<user-bar/>
				</div>
			</div>
			<!-- 手机端情况下的左侧菜单 -->
			<Side-m v-if="isMobile" v-show="displayLayout"/>
			<breadcrumb v-if="!isMobile && breadcrumbOpen" v-show="displayLayout"/>
			<!-- 多标签 -->
			<Tags v-if="!isMobile && layoutTagsOpen" v-show="displayLayout"/>
			<a-layout-content
				:class="displayLayout ? 'main-content-wrapper' : 'main-content-wrapper main-content-wrapper-max'"
			>
				<div id="admin-ui-main" class="admin-ui-main">
					<router-view v-slot="{ Component }">
						<keep-alive :include="kStore.keepLiveRoute">
							<component :is="Component" v-if="kStore.routeShow" :key="route.name"/>
						</keep-alive>
					</router-view>
					<iframe-view/>
					<div v-if="footerCopyrightOpen" class="main-bottom-wrapper">
						<a class="sys-color-a0a0a0" :href="sysBaseConfig.SYSTEM_SYS_COPYRIGHT_URL" target="_blank">{{
								sysBaseConfig.SYSTEM_SYS_COPYRIGHT
							}}</a>
					</div>
				</div>
			</a-layout-content>
		</a-layout>
	</a-layout>
</template>

<script setup>
import {useRoute} from 'vue-router'
import UserBar from '@/layout/components/userbar.vue'
import Tags from '@/layout/components/tags.vue'
import SideM from '@/layout/components/sideM.vue'
import NavMenu from '@/layout/components/NavMenu.vue'
import ModuleMenu from '@/layout/components/moduleMenu.vue'
import IframeView from '@/layout/components/iframeView.vue'
import Breadcrumb from '@/layout/components/breadcrumb.vue'

const props = defineProps({
	layout: {},
	menu: {type: Array}, // 菜单
	menuList: {type: Array}, // 菜单
	sysBaseConfig: {type: Object},
	moduleMenuShow: {type: Boolean},
	selectedKeys: {type: Array},
	openKeys: {type: Array},
	sideTheme: {type: String},
	isMobile: {type: Boolean}, // 是否移动端
	breadcrumbOpen: {type: Boolean}, //面包屑
	layoutTagsOpen: {type: Boolean},
	layoutSiderDowbleMenu: {type: Boolean},
	kStore: {type: Object}, // 获取的仓库数据
	footerCopyrightOpen: {type: Boolean} //页脚版权信息
})
const emit = defineEmits(['onSelect', 'switchModule', 'onOpenChange', 'displayLayoutChange'])
const displayLayout = ref(true)
const route = useRoute()
watch(route, () => {
	nextTick(() => {
		displayLayout.value = displayLayoutResult()
	})
	if (displayLayout.value) {
		emit('displayLayoutChange')
	}
})
onMounted(() => {
	nextTick(() => {
		displayLayout.value = displayLayoutResult()
	})
})
const displayLayoutResult = () => {
	// 根据route.meta.keepLive动态管理keepLiveRoute
	if (route.meta.keepLive === true) {
		props.kStore.pushKeepLive(route.name)
	} else {
		props.kStore.removeKeepLive(route.name)
	}
	if (
		route.meta.displayLayout === undefined ||
		route.meta.displayLayout === null ||
		route.meta.displayLayout === 'null'
	) {
		return true
	} else {
		return route.meta.displayLayout
	}
}
const onSelect = (obj) => {
	emit('onSelect', obj)
}
const switchModule = (id) => {
	emit('switchModule', id)
}
const onOpenChange = (keys) => {
	emit('onOpenChange', keys)
}
</script>

<style lang="less" scoped>
.sys-color-fff {
	color: #fff;
}

.sys-pdl25 {
	padding-left: 11px;
}

.sys-menu-line {
	text-align: center;
	height: auto;
	line-height: 20px;
	flex: none;
	display: block;
	padding: 12px 0 !important;
}

.sys-navmenu-line {
	min-width: 0;
	flex: 1 1 0%;
	overflow: hidden;
}

.sys-bb0 {
	border-bottom: none;
	position: relative;
}

.ant-layout-content {
	display: flex;
	flex-direction: column;
}

.sys-pd1180 {
	padding: 10px 150px 0 150px;
}

.sys-pd050 {
	padding: 0 50px;
}

.sys-pl10 {
	padding-left: 10px;
}

.sys-mg050 {
	margin: 0px 150px;
}

.main-content-wrapper-max {
	padding: 0;
}
</style>
