import Vue from "vue";
import Router from "vue-router";
import login from "./views/Login.vue";
import logon from './views/Logon.vue'
import main from './views/Main.vue'
import updateUser from './views/updateUser.vue'
import updatePassword from './views/updatePassword.vue'
import postDetail from './views/postDetail.vue'

Vue.use(Router);

export default new Router({
  routes: [{
      path: "/",
      name: "login",
      component: login
    },
    {
      path: "/logon",
      name: "logon",
      component: logon
    },
    {
      path: "/main",
      name: "main",
      component: main,

    },
    {
      path: '/updateUser',
      name: 'updateUser',
      component: updateUser
    },
    {
      path: '/updatePassword',
      name: 'updatePassword',
      component: updatePassword
    },
    {
      path: '/postDetail',
      name: 'postDetail',
      component: postDetail
    },
  ]
});