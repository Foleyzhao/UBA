import {defineStore} from 'pinia'

export const keepAliveStore = defineStore('keepAlive', () => {
	// 定义state
	const keepLiveRoute = ref([])
	const routeKey = ref(null)
	const routeShow = ref(true)

	// 定义action
	const pushKeepLive = (component) => {
		if (!keepLiveRoute.value.includes(component)) {
			keepLiveRoute.value.push(component)
		}
	}
	const removeKeepLive = (component) => {
		const index = keepLiveRoute.value.indexOf(component)
		if (index !== -1) {
			keepLiveRoute.value.splice(index, 1)
		}
	}
	const clearKeepLive = () => {
		keepLiveRoute.value = []
	}
	const setRouteKey = (key) => {
		routeKey.value = key
	}
	const setRouteShow = (key) => {
		routeShow.value = key
	}
	const setRouteKeyAction = (key) => {
		setRouteKey(key)
	}

	return {
		keepLiveRoute,
		routeKey,
		routeShow,
		pushKeepLive,
		removeKeepLive,
		clearKeepLive,
		setRouteKey,
		setRouteShow,
		setRouteKeyAction
	}
})
