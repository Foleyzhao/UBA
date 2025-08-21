export const required = (message, trigger = ['blur', 'change']) => ({
	required: true,
	message,
	trigger
})

// 常用正则规则大全：https://any86.github.io/any-rule/
// 表单上面使用参照菜单管理的 title 字段，例如：-> title: [required('请输入菜单名称'), rules.horizontalChart]
export const rules = {
	phone: {
		pattern: /^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\d{8}$/,
		message: '请填写符合要求的11位手机号',
		trigger: 'blur'
	},
	email: {
		pattern: /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/,
		message: '请填写正确的邮箱号',
		trigger: 'blur'
	},
	idCard: {
		pattern:
			/(^\d{8}(0\d|10|11|12)([0-2]\d|30|31)\d{3}$)|(^\d{6}(18|19|20)\d{2}(0[1-9]|10|11|12)([0-2]\d|30|31)\d{3}(\d|X|x)$)/,
		message: '请填写符合要求的身份证号',
		trigger: 'blur'
	},
	lettersNum: {
		pattern: /^[A-Za-z0-9]+$/,
		message: '填写内容须是字母或数字组成',
		trigger: 'blur'
	},
	number: {
		pattern: /^\d{1,}$/,
		message: '填写内容必须是纯数字',
		trigger: 'blur'
	},
	price: {
		pattern: /(?:^[1-9]([0-9]+)?(?:\.[0-9]{1,2})?$)|(?:^(?:0)$)|(?:^[0-9]\.[0-9](?:[0-9])?$)/,
		message: '只支持正数金额',
		trigger: 'blur'
	},
	horizontalChart: {
		pattern: /^[^-]*$/,
		message: '不可包含横杠 “-”'
	},
	initialNotBackslashChart: {
		pattern: /^(?!\/)[\s\S]*$/,
		message: '首字母不可出现反斜杠 “/”'
	},
	initialYesBackslashChart: {
		pattern: /^\/[^/].*$/,
		message: '首字母必须是反斜杠 “/”'
	},
	mustBeLetters: {
		pattern: /^[a-zA-Z]+$/,
		message: '输入内容必须是英文字母'
	}
}
