import {defineStore} from 'pinia'

export const iframeStore = defineStore('iframe', () => {
	// 定义state
	const iframeList = ref([])
	const setIframeList = (route) => {
		iframeList.value = []
		iframeList.value.push(route)
	}

	// 定义action
	const pushIframeList = (route) => {
		const target = iframeList.value.find((item) => item.path === route.path)
		if (!target) {
			iframeList.value.push(route)
		}
	}
	const removeIframeList = (route) => {
		iframeList.value.forEach((item, index) => {
			if (item.path === route.path) {
				iframeList.value.splice(index, 1)
			}
		})
	}
	const refreshIframe = (route) => {
		iframeList.value.forEach((item) => {
			if (item.path === route.path) {
				const url = route.meta.url
				item.meta.url = ''
				setTimeout(() => {
					item.meta.url = url
				}, 200)
			}
		})
	}
	const clearIframeList = () => {
		iframeList.value = []
	}

	return {
		iframeList,
		setIframeList,
		pushIframeList,
		removeIframeList,
		refreshIframe,
		clearIframeList
	}
})
