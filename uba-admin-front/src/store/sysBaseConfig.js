import configApi from '@/api/dev/configApi'
import {message} from 'ant-design-vue'

const formData = ref({
	SYSTEM_SYS_LOGO: '',
	SYSTEM_SYS_BACK_IMAGE: '',
	SYSTEM_SYS_NAME: '',
	SYSTEM_SYS_VERSION: '',
	SYSTEM_SYS_COPYRIGHT: '',
	SYSTEM_SYS_COPYRIGHT_URL: '',
	SYSTEM_SYS_DEFAULT_FILE_ENGINE: 'LOCAL',
	SYSTEM_SYS_DEFAULT_CAPTCHA_OPEN: true,
	SYSTEM_SYS_DEFAULT_PASSWORD: ''
})

const param = {
	category: 'SYS_BASE'
}

const getSysBaseConfig = () => {
	configApi.configList(param).then((data) => {
		if (data) {
			data.forEach((item) => {
				formData.value[item.configKey] = item.configValue ? '' : item.configValue
			})
		} else {
			message.warning('表单项不存在，请初始化数据库')
		}
	})
}
