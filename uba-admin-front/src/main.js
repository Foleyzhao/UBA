import {createApp} from 'vue'
import Antd from 'ant-design-vue'
import {createPinia} from 'pinia'

import './style/index.less'
import uba from './uba'
import i18n from './locales'
import router from './router'
import App from './App.vue'
import './tailwind.css'

const app = createApp(App)
app.use(createPinia())
app.use(router)
app.use(Antd)
app.use(i18n)
app.use(uba)

// 挂载app
app.mount('#app')
