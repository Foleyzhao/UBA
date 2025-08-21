import userRoutes from '@/config/route'

// 获取第一个界面
const getIndexMenu = (menu) => {
	if (menu[0] && menu[0].children) {
		let indexMenu = menu[0].children[0]
		// 如果第一个菜单为目录，接着往下找
		if (indexMenu.meta.type === 'catalog') {
			indexMenu = traverseChild(menu)
		}
		return indexMenu
	} else {
		return userRoutes.menu[0]
	}
}
// 遍历进行判断，其中处理了被隐藏的
const traverseChild = (menu) => {
	if (menu[0] && menu[0].children !== undefined) {
		if (menu[0].children.length > 0) {
			if (menu[0].children[0] && menu[0].children[0].meta.hidden && menu[0].children[0].meta.hidden === true) {
				return menu[0]
			} else {
				return traverseChild(menu[0].children)
			}
		}
	} else {
		return menu[0]
	}
}

export default {
	getIndexMenu
}
