import './line/iconfont.css'
import lineJsonData from './line/iconfont.json'

import './filled/iconfont.css'
import filledJsonData from './filled/iconfont.json'

export default {
	icons: [
		{
			name: '基础',
			key: 'default',
			iconItem: [
				{
					name: '线框风格',
					key: 'default',
					item: lineJsonData.glyphs
				},
				{
					name: '实底风格',
					key: 'filled',
					item: filledJsonData.glyphs
				}
			]
		}
	]
}
