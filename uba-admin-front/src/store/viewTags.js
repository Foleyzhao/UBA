import {defineStore} from 'pinia'
import {useRouter} from 'vue-router'

export const viewTagsStore = defineStore('viewTags', () => {
	// 定义state
	const viewTags = ref([])
	const router = useRouter()
	// 监听路由变化
	watch(() => router.currentRoute.value, (newRoute) => {
		const viewTag = viewTags.value.find((item) => item.path === newRoute.path)
		if (viewTag) {
			Object.assign(viewTag, newRoute)
		}
	}, {immediate: true})

	// 定义action
	const pushViewTags = (route) => {
		const target = viewTags.value.find((item) => item.path === route.path)
		const isName = route.name
		if (!target && isName) {
			viewTags.value.push(route)
		}
		if (target) {
			updateViewTags(route)
		}
	}
	const removeViewTags = (route) => {
		viewTags.value.forEach((item, index) => {
			if (item.path === route.path) {
				viewTags.value.splice(index, 1)
			}
		})
	}
	const updateViewTags = (route) => {
		viewTags.value.forEach((item, index) => {
			if (item.fullPath === route.fullPath) {
				viewTags.value[index] = {...route, ...item}
			}
		})
	}
	// 更新或删除视图标签
	const updateOrRemoveViewTags = (routes) => {
		if (routes && routes.length > 0) {
			viewTags.value.forEach((item, index) => {
				// 使用path进行比较，忽略参数部分
				const target = routes.find((route) => route.path === item.path)
				if (!target) {
					// 路由不存在，删除
					viewTags.value.splice(index, 1)
				} else {
					// 路由存在，更新
					updateViewTags(target)
				}
			})
		}
	}
	const updateViewTagsTitle = (title = '') => {
		const nowFullPath = location.hash.substring(1)
		viewTags.value.forEach((item) => {
			if (item.fullPath === nowFullPath) {
				item.meta.key = Date.now()
				item.meta.title = title
			}
		})
	}
	const clearViewTags = () => {
		viewTags.value = []
	}

	return {
		viewTags,
		pushViewTags,
		removeViewTags,
		updateViewTags,
		updateViewTagsTitle,
		clearViewTags,
		updateOrRemoveViewTags
	}
})
