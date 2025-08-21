/* eslint-disable eqeqeq */
export default {
	// 加深
	darken(color, level) {
		const rgbc = this.hexToRgb(color)
		for (let i = 0; i < 3; i++) rgbc[i] = Math.floor(rgbc[i] * (1 - level))
		return this.rgbToHex(rgbc[0], rgbc[1], rgbc[2])
	},
	// 变淡
	lighten(color, level) {
		const rgbc = this.hexToRgb(color)
		for (let i = 0; i < 3; i++) rgbc[i] = Math.floor((255 - rgbc[i]) * level + rgbc[i])
		return this.rgbToHex(rgbc[0], rgbc[1], rgbc[2])
	},
	// rgb颜色转hex颜色
	rgbToHex(rgb) {
		const bg = rgb.match(/^rgb\(\s*(\d+)\s*,\s*(\d+)\s*,\s*(\d+)\s*\)$/)
		// 返回空字符串
		if (!bg) {
			return ''
		}
		return '#' + toHex(bg[1]) + toHex(bg[2]) + toHex(bg[3])
	},
	// hex颜色转rgb颜色
	hexToRgb(hex) {
		// 去除开头 #
		if (hex.startsWith('#')) {
			hex = hex.substring(1)
		}
		// 如果当前传入的是 3 位小数值，直接转换为 6 位进行处理
		if (hex.length === 3) {
			hex = [hex[0], hex[0], hex[1], hex[1], hex[2], hex[2]].join('')
		}
		if (hex.length !== 6) {
			throw new Error('invalid hex:' + hex)
		}
		const r = parseInt(hex.slice(0, 2), 16)
		const g = parseInt(hex.slice(2, 4), 16)
		const b = parseInt(hex.slice(4, 6), 16)
		if ([r, g, b].some((x) => Number.isNaN(x))) {
			throw new Error('invalid hex:' + hex)
		}
		return [r, g, b]
	},
	// 透明度
	alpha(color, alpha = 1) {
		let hex = color.length > 7 ? color.rgbToHex(color) : color
		const {r, g, b} = color.hexToRgb(hex)
		return `rgba(${r}, ${g}, ${b}, ${alpha})`
	}
}

// 转Hex
const toHex = (x) => ('0' + parseInt(x).toString(16)).slice(-2)
