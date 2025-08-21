import {baseRequest} from '@/utils/request'

const request = (url, ...arg) => baseRequest(`/dev/file/` + url, ...arg)
/**
 * 文件
 *
 * @author happynewyear
 */
export default {
	// 动态上传文件返回id
	fileUploadDynamicReturnId(data) {
		return request('uploadDynamicReturnId', data)
	},
	// 动态上传文件返回url
	fileUploadDynamicReturnUrl(data) {
		return request('uploadDynamicReturnUrl', data)
	},
	// 本地文件上传，返回文件id
	fileUploadReturnId(data) {
		return request('uploadLocalReturnId', data)
	},
	// 阿里云文件上传，返回文件id
	fileUploadAliyunReturnId(data) {
		return request('uploadAliyunReturnId', data)
	},
	// 腾讯云文件上传，返回文件id
	fileUploadTencentReturnId(data) {
		return request('uploadTencentReturnId', data)
	},
	// MINIO文件上传，返回文件id
	fileUploadMinioReturnId(data) {
		return request('uploadMinioReturnId', data)
	},
	// 本地文件上传，返回文件Url
	fileUploadLocalReturnUrl(data) {
		return request('uploadLocalReturnUrl', data)
	},
	// 阿里云文件上传，返回文件Url
	fileUploadAliyunReturnUrl(data) {
		return request('uploadAliyunReturnUrl', data)
	},
	// 腾讯云文件上传，返回文件Url
	fileUploadTencentReturnUrl(data) {
		return request('uploadTencentReturnUrl', data)
	},
	// MINIO文件上传，返回文件Url
	fileUploadMinioReturnUrl(data) {
		return request('uploadMinioReturnUrl', data)
	},
	// 获取文件分页列表
	filePage(data) {
		return request('page', data, 'get')
	},
	// 获取文件列表
	fileList(data) {
		return request('list', data, 'get')
	},
	// 下载文件，这里要带上blob类型
	fileDownload(data) {
		return request('download', data, 'get', {
			responseType: 'blob'
		})
	},
	// 获取文件详情
	fileDetail(data) {
		return request('detail', data, 'get')
	},
	// 根据文件url集合获取文件集合
	fileGetFileListByUrlList(data) {
		return request('getFileListByUrlList', data)
	},
	// 删除文件
	fileDelete(data) {
		return request('delete', data)
	},
	// 物理删除文件
	fileDeleteAbsolute(data) {
		return request('deleteAbsolute', data)
	}
}
