import {defineStore} from 'pinia'
import dictApi from '@/api/dev/dictApi'
import tool from '@/utils/tool'

export const useDictStore = defineStore('useDictStore', () => {
	// 刷新字典信息
	const refreshDict = async () => {
		dictApi.dictTree().then((data) => {
			// 设置字典到store中
			tool.data.set('DICT_TYPE_TREE_DATA', data)
		})
	}
	return {refreshDict}
})
