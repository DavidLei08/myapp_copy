import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from './plugins/vuetify';
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import axios from 'axios'
import location from './common/location'
import utils from './common/utils'
import Router from 'vue-router'
import Top from './components/Top'

Vue.component('Top', Top);
Vue.config.productionTip = false;
Vue.prototype.$location = location;
Vue.prototype.$utils = utils

Vue.prototype.$axios = axios
axios.defaults.baseURL = "http://localhost:8081/api"
axios.defaults.headers.post['Content-Type'] = 'application/json; charset=UTF-8'
axios.defaults.headers.get['Content-Type'] = 'application/json; charset=UTF-8'
new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount("#app");

const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}