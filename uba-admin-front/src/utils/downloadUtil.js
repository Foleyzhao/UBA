import {message} from 'ant-design-vue'

export default {
	// 对下载的流进行处理，直接从浏览器下载下来
	resultDownload(res) {
		if (res.data.type === 'application/json') {
			// 错误以及无权限
			const reader = new FileReader(res.data)
			reader.readAsText(res.data)
			reader.onload = () => {
				const result = JSON.parse(reader.result)
				message.error(result.msg)
			}
		} else {
			const blob = new Blob([res.data], {type: 'application/octet-stream;charset=UTF-8'})
			const contentDisposition = res.headers['content-disposition']
			const patt = new RegExp('filename=([^;]+\\.[^\\.;]+);*')
			const $link = document.createElement('a')
			$link.href = URL.createObjectURL(blob)
			$link.download = decodeURIComponent(patt.exec(contentDisposition)[1])
			$link.click()
			document.body.appendChild($link)
			document.body.removeChild($link) // 下载完成移除元素
			window.URL.revokeObjectURL($link.href) // 释放掉blob对象
		}
	}
}
