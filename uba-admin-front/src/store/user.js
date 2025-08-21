import {defineStore} from 'pinia'
import loginApi from '@/api/auth/loginApi'
import {useGlobalStore} from '@/store'
import tool from '@/utils/tool'

export const useUserStore = defineStore('useUserStore', () => {
	// 初始化用户信息
	const initUserInfo = async () => {
		const data = await loginApi.getLoginUser()
		const globalStore = useGlobalStore()
		globalStore.setUserInfo(data)
		tool.data.set('USER_INFO', data)
	}
	// 刷新登录用户信息
	const refreshUserLoginUserInfo = () => {
		loginApi.getLoginUser().then((data) => {
			const globalStore = useGlobalStore()
			globalStore.setUserInfo(data)
			tool.data.set('USER_INFO', data)
		})
	}
	return {initUserInfo, refreshUserLoginUserInfo}
})
