import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import fetch from '../static/js/fetch.js'
import VueCookies from 'vue-cookies'


const VueCodeMirror = require('vue-codemirror-lite')
Vue.use(VueCodeMirror);

Vue.use(ElementUI);
Vue.use(VueCookies);
Vue.config.productionTip = false;
Vue.prototype.fetch=fetch;

new Vue({
    el: '#app',
    router,
    render: h => h(App)
});
