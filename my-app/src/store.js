import Vue from "vue";
import Vuex from "vuex";
//import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    userInfo: {},
    postInfo: {},
    remarkInfo: {}
  },
  mutations: {
    setUserInfo(state, userInfo) {
      state.userInfo = userInfo
    },
    setPostInfo(state, postInfo) {
      state.postInfo = postInfo
    },
    setRemarkInfo(state, remarkInfo) {
      state.remarkInfo = remarkInfo
    }
  },
  actions: {},
  // plugins: [createPersistedState({
  //   storage: window.sessionStorage
  // })]
});