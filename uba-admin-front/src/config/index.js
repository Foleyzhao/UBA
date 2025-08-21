const DEFAULT_CONFIG = {
	// 是否是微服务cloud版本
	CLOUD_SERVER: false,
	// 首页地址
	DASHBOARD_URL: '/index',
	// 接口地址
	API_URL: import.meta.env.VITE_API_BASEURL,
	// 请求超时
	TIMEOUT: 60000,
	// TokenName // Authorization
	TOKEN_NAME: 'token',
	// Token前缀，注意最后有个空格，如不需要需设置空字符串 // Bearer
	TOKEN_PREFIX: '',
	// 追加其他头
	HEADERS: {},
	// 请求是否开启缓存
	REQUEST_CACHE: false,
	// 布局 经典：classical，双排菜单：doublerow, 顶栏菜单：top
	SYSTEM_LAYOUT: 'doublerow',
	// 菜单是否折叠
	SYSTEM_MENU_COLLAPSE: false,
	// 模块坞
	SYSTEM_MODULE_UNFOLD_OPEN: true,
	// 是否开启多标签
	SYSTEM_LAYOUT_TAGS_OPEN: true,
	// 是否开启展示面包屑
	SYSTEM_BREADCRUMB_OPEN: false,
	// 顶栏是否应用主题色
	SYSTEM_TOP_HEADER_THEME_COLOR_OPEN: false,
	// 顶栏主题色通栏
	SYSTEM_TOP_HEADER_THEME_COLOR_SPREAD: false,
	// 侧边菜单是否排他展开
	SYSTEM_SIDE_UNIQUE_OPEN: true,
	// 登录用户水印
	SYSTEM_LOGIN_USER_WATERMARK_OPEN: false,
	// 页脚版权信息
	SYSTEM_FOOTER_COPYRIGHT_OPEN: false,
	// 圆角风格
	SYSTEM_ROUNDED_CORNER_STYLE_OPEN: false,
	// 语言
	LANG: 'zh-cn',
	// 主题颜色
	COLOR: '#1677FF',
	// 默认整体主题
	SYSTEM_THEME: 'dark',
	// 整体表单风格 modal|drawer
	SYSTEM_FORM_STYLE: 'drawer',
	// 前后台登录链接是否展示
	FRONT_BACK_LOGIN_URL_SHOW: false,
	// 三方登录是否展示
	THREE_LOGIN_SHOW: false,
	// 系统基础配置，这些是数据库中保存起来的
	SYS_BASE_CONFIG: {
		// 默认logo
		SYSTEM_SYS_LOGO: '/img/logo.png',
		// 背景图
		SYSTEM_SYS_BACK_IMAGE: '',
		// 系统名称
		SYSTEM_SYS_NAME: 'UBA',
		// 版本
		SYSTEM_SYS_VERSION: '3.0',
		// 版权
		SYSTEM_SYS_COPYRIGHT: 'UBA ©2024 Created by 欢年科技',
		// 版权跳转URL
		SYSTEM_SYS_COPYRIGHT_URL: 'https://www.huanniankj.com',
		// 默认文件存储
		SYSTEM_SYS_DEFAULT_FILE_ENGINE: 'LOCAL',
		// 是否开启B端验证码
		SYSTEM_SYS_DEFAULT_CAPTCHA_OPEN_FLAG_FOR_B: 'true',
		// 默认重置密码
		SYSTEM_SYS_DEFAULT_PASSWORD: '123456'
	}
}

export default DEFAULT_CONFIG
