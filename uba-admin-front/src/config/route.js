// 静态路由配置
const routes = {
	// 默认模块，仅限于后端未添加任何单页配置，用此路由
	module: [
		{
			id: '01',
			name: 'homeModule',
			path: '/homeModule',
			component: '',
			meta: {
				title: '默认',
				type: 'module',
				icon: 'bank-outlined'
			},
			children: []
		}
	],
	// 默认谁都有个人中心
	menu: [
		{
			id: '001',
			name: 'usercenter',
			path: '/usercenter',
			component: 'sys/user/userCenter',
			meta: {
				title: '个人中心',
				type: 'menu',
				hidden: true
			}
		}
	]
}

export default routes
