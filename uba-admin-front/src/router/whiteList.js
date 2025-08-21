const constRouters = [
	{
		path: '/findpwd'
	},
	{
		path: '/register'
	},
	{
		path: '/callback'
	},
	/*{
		path: '/other',
		name: 'other',
		component: () => import('@/views/other/index.vue'),
		meta: {
			title: '其他'
		}
	}*/
]
/**
 * 路由白名单（数组形式）
 *
 * 如果组件像登录一样，那就简单的写一个path，即可实现放开，
 * 如果组件不在这边的，需要手动添加组件，就像other一样，
 * 因为没登陆你没法拿到后端给你返回的那一坨，当然就找不到component
 *
 * @author happynewyear
 */
export default constRouters
