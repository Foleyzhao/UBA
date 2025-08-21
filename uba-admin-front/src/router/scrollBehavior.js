import {nextTick} from 'vue'
import {viewTagsStore} from '@/store'

export function beforeEach(to, from) {
	const adminMain = document.querySelector('#admin-ui-main')
	if (!adminMain) {
		return false
	}
	const store = viewTagsStore()
	store.updateViewTags({
		fullPath: from.fullPath,
		scrollTop: adminMain.scrollTop
	})
}

export function afterEach(to) {
	const adminMain = document.querySelector('#admin-ui-main')
	if (!adminMain) {
		return false
	}
	nextTick(() => {
		const store = viewTagsStore()
		const beforeRoute = store.viewTags.filter((v) => v.path === to.path)[0]
		if (beforeRoute) {
			adminMain.scrollTop = beforeRoute.scrollTop || 0
		}
	})
}
