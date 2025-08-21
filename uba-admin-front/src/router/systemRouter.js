import config from '@/config'
import tool from '@/utils/tool'
import routerUtil from '@/utils/routerUtil'

const Layout = () => import('@/layout/index.vue')
const Login = () => import('@/views/auth/login/login.vue')
const FindPwd = () => import('@/views/auth/findPwd/index.vue')
const Callback = () => import('@/views/auth/login/callback.vue')
const Register = () => import('@/views/auth/login/register.vue')

// 系统路由
const routes = [
	{
		name: 'layout',
		path: '/',
		component: Layout,
		redirect: tool.data.get('MENU') ? routerUtil.getIndexMenu(tool.data.get('MENU')).path : config.DASHBOARD_URL,
		children: []
	},
	{
		path: '/login',
		component: Login,
		meta: {
			title: '登录'
		}
	},
	{
		path: '/register',
		component: Register,
		meta: {
			title: '注册'
		}
	},
	{
		path: '/findpwd',
		component: FindPwd,
		meta: {
			title: '找回密码'
		}
	},
	{
		path: '/callback',
		component: Callback,
		meta: {
			title: '三方登录'
		}
	}
]

export default routes
