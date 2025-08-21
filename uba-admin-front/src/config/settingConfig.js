const colorList = [
	{
		key: '薄暮',
		color: '#F5222D'
	},
	{
		key: '火山',
		color: '#FA541C'
	},
	{
		key: '胭脂粉',
		color: '#EB2F96'
	},
	{
		key: '日暮',
		color: '#FAAD14'
	},
	{
		key: '明青',
		color: '#13C2C2'
	},
	{
		key: '极光绿',
		color: '#52C41A'
	},
	{
		key: '深绿',
		color: '#009688'
	},
	{
		key: '拂晓蓝（默认）',
		color: '#1677FF'
	},
	{
		key: '极客蓝',
		color: '#2F54EB'
	},
	{
		key: '酱紫',
		color: '#722ED1'
	},
	{
		key: '主题黑',
		color: '#001529'
	}
]

const updateColorWeak = (colorWeak) => {
	// document.body.className = colorWeak ? 'colorWeak' : '';
	const app = document.body.querySelector('#app')
	colorWeak ? app.classList.add('colorWeak') : app.classList.remove('colorWeak')
}

export {colorList, updateColorWeak}
