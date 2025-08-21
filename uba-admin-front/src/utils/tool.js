const tool = {}

// localStorage
tool.data = {
	set(table, settings) {
		const _set = JSON.stringify(settings)
		const SYSTEMSTRING = table.slice(0, 6) === 'SYSTEM_' && table !== 'SYSTEM_SYS_BASE_CONFIG'
		if (SYSTEMSTRING) {
			let localSetting = JSON.parse(localStorage.getItem('SYSTEM_SETTING')) || {}
			let newSetting = {}
			newSetting[table] = _set
			return localStorage.setItem('SYSTEM_SETTING', JSON.stringify(Object.assign(localSetting, newSetting)))
		} else return localStorage.setItem(table, _set)
	},
	get(table) {
		const SYSTEMSTRING = table.slice(0, 6) === 'SYSTEM_' && table !== 'SYSTEM_SYS_BASE_CONFIG'
		const SYSTEM_SETTING = JSON.parse(localStorage.getItem('SYSTEM_SETTING')) || {}
		let data = SYSTEMSTRING ? SYSTEM_SETTING[table] : localStorage.getItem(table)
		try {
			data = JSON.parse(data)
		} catch (err) {
			return null
		}
		return data
	},
	remove(table) {
		return localStorage.removeItem(table)
	},

	clear() {
		return localStorage.clear()
	}
}

// sessionStorage
tool.session = {
	set(table, settings) {
		const _set = JSON.stringify(settings)
		return sessionStorage.setItem(table, _set)
	},
	get(table) {
		let data = sessionStorage.getItem(table)
		try {
			data = JSON.parse(data)
		} catch (err) {
			return null
		}
		return data
	},
	remove(table) {
		return sessionStorage.removeItem(table)
	},
	clear() {
		return sessionStorage.clear()
	}
}

// 千分符
tool.groupSeparator = (num) => {
	num = `${num}`
	if (!num.includes('.')) num += '.'

	return num
		.replace(/(\d)(?=(\d{3})+\.)/g, ($0, $1) => {
			return `${$1},`
		})
		.replace(/\.$/, '')
}

// 获取所有字典数组
tool.dictDataAll = () => {
	return tool.data.get('DICT_TYPE_TREE_DATA')
}

// 字典翻译方法，界面插槽使用方法 {{ $TOOL.dictType('sex', record.sex) }}
tool.dictTypeData = (dictValue, value) => {
	const dictTypeTree = tool.dictDataAll()
	if (!dictTypeTree) {
		return '需重新登录'
	}
	const tree = dictTypeTree.find((item) => item.dictValue === dictValue)
	if (!tree) {
		return ''
	}
	const children = tree.children
	if (!tree.children) {
		return ''
	}
	const dict = children.find((item) => item.dictValue === value)
	return dict ? dict.dictLabel : ''
}

// 获取某个code下字典的列表，多用于字典下拉框
tool.dictTypeList = (dictValue) => {
	const dictTypeTree = tool.dictDataAll()
	if (!dictTypeTree) {
		return []
	}
	const tree = dictTypeTree.find((item) => item.dictValue === dictValue)
	if (tree && tree.children) {
		return tree.children
	}
	return []
}

// 获取某个code下字典的列表，基于dictTypeList 改进，保留老的，逐步替换
tool.dictList = (dictValue) => {
	const dictTypeTree = tool.dictDataAll()
	if (!dictTypeTree) {
		return []
	}
	const tree = dictTypeTree.find((item) => item.dictValue === dictValue)
	if (!tree) {
		return []
	}
	if (!tree.children) {
		return []
	}
	return tree.children.map((item) => {
		return {
			value: item['dictValue'],
			label: item['name']
		}
	})
}

// 树形翻译 需要指定最顶级的 parentValue  和当级的value
tool.translateTree = (parentValue, value) => {
	const tree = tool.dictDataAll().find((item) => item.dictValue === parentValue)
	const targetNode = findNodeByValue(tree, value)
	return targetNode ? targetNode.dictLabel : ''
}
const findNodeByValue = (node, value) => {
	if (node.dictValue === value) {
		return node
	}
	if (node.children) {
		for (let i = 0; i < node.children.length; i++) {
			const result = findNodeByValue(node.children[i], value)
			if (result) {
				return result
			}
		}
	}
	return null
}

// 生成UUID
tool.systemUuid = () => {
	let uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, (c) => {
		let r = (Math.random() * 16) | 0,
			v = c === 'x' ? r : (r & 0x3) | 0x8
		return v.toString(16)
	})
	// 首字符转换成字母
	return 'xn' + uuid.slice(2)
}

// 输入位数获得英文字母大小写随机码
tool.generateString = (length = 8) => {
	let result = ''
	let characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz'
	let charactersLength = characters.length
	for (let i = 0; i < length; i++) {
		result += characters.charAt(Math.floor(Math.random() * charactersLength))
	}
	return result
}

tool.parseTime = (time, cFormat) => {
	if (time == null || time.length === 0) {
		return ''
	}

	const format = cFormat || '{y}-{m}-{d} {h}:{i}:{s}'
	let date
	if (typeof time === 'object') {
		date = time
	} else {
		if (typeof time === 'string' && /^[0-9]+$/.test(time)) {
			time = parseInt(time)
		}
		if (typeof time === 'number' && time.toString().length === 10) {
			time = time * 1000
		}
		date = new Date(time)
	}
	const formatObj = {
		y: date.getFullYear(),
		m: date.getMonth() + 1,
		d: date.getDate(),
		h: date.getHours(),
		i: date.getMinutes(),
		s: date.getSeconds(),
		a: date.getDay()
	}
	const time_str = format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
		let value = formatObj[key]
		// Note: getDay() returns 0 on Sunday
		if (key === 'a') {
			return ['日', '一', '二', '三', '四', '五', '六'][value]
		}
		if (result.length > 0 && value < 10) {
			value = '0' + value
		}
		return value || 0
	})
	return time_str
}

// 判断不为空
tool.isNotEmpty = (value) => {
	if (typeof value === 'object') {
		for (const key in value) {
			return true
		}
		return false
	}
	return !(value === null || value === undefined || value === 'undefined' || value === '')
}
// 判断为空
tool.isEmpty = (value) => {
	return !tool.isNotEmpty(value)
}

export default tool
