<template>
  <v-app>
    <Top></Top>
    <v-content>
      <v-container fluid>
        <v-layout>
          <v-card
          width="98%"
            class="mx-auto"
          >
            <v-toolbar
              color="primary"
              dark
            >
              <v-app-bar-nav-icon
                title="Self-posting"
                @click="selfPost(username)"
              ></v-app-bar-nav-icon>

              <v-spacer></v-spacer>

              <v-flex style="width:50%">
                <v-spacer></v-spacer>
                <v-text-field
                  v-model="keyword"
                  style="width:100%;"
                  placeholder="input search keyword"
                  @keyup.enter="searchPost"
                >
                </v-text-field>
              </v-flex>

              <v-flex>
                <v-btn
                  icon
                  @click="searchPost"
                >
                  <v-icon>mdi-magnify</v-icon>
                </v-btn>

                <v-dialog
                  v-model="dialog"
                  transition="dialog-bottom-transition"
                  persistent
                  width="90%"
                >
                  <template v-slot:activator="{ on }">
                    <v-btn
                      icon
                      v-on="on"
                    >
                      <v-icon title="Write a post">mdi-pencil</v-icon>
                    </v-btn>
                  </template>

                  <v-card>
                    <v-snackbar
                      v-model="errorInf.hasError"
                      :timeout=5000
                      top
                      color="error"
                    >
                      {{ errorInf.errMsg }}
                    </v-snackbar>
                    <v-toolbar
                      dark
                      color="primary"
                    >
                      <v-btn
                        icon
                        dark
                        @click="dialog = false"
                      >
                        <v-icon>close</v-icon>
                      </v-btn>
                      <v-toolbar-title>WRITER A POST</v-toolbar-title>
                      <v-spacer></v-spacer>
                    </v-toolbar>

                    <v-card-text style="height:60%">
                      <v-container>
                        <v-row>
                          <v-col
                            cols="12"
                            sm="8"
                            md="8"
                          >
                            <v-text-field
                              placeholder="Please enter the name of the post"
                              name="postTitle"
                              v-model.trim="postTitle"
                              type="text"
                              :rules="postTitleRules"
                            ></v-text-field>
                          </v-col>
                          <v-col cols="12">
                            <v-textarea
                              auto-grow
                              clearable="false"
                              solo
                              name="postContent"
                              placeholder="Please enter the content of the post"
                              v-model.trim="postContent"
                              type="text"
                              :rules="postContentRules"
                            ></v-textarea>
                          </v-col>
                        </v-row>
                        <v-card-actions>
                          <v-spacer></v-spacer>
                          <v-dialog
                            v-model="dialogger"
                            transition="dialog-bottom-transition"
                            persistent
                           width="280px"
                          >
                            <template v-slot:activator="{ on }">
                              <v-btn
                                color="primary"
                                v-on="on"
                              >Save</v-btn>
                            </template>
                            <v-card>
                              <v-card-title primary-title>Confirm</v-card-title>
                              <v-card-text>
                                Whether to create a post or not?
                              </v-card-text>
                              <v-divider></v-divider>
                              <v-card-actions>
                                <v-spacer></v-spacer>
                                <v-btn
                                  color="primary"
                                  @click="dialogger = false"
                                >No</v-btn>
                                <v-btn
                                  color="primary"
                                  @click="save"
                                >Yes</v-btn>
                              </v-card-actions>
                            </v-card>
                          </v-dialog>
                        </v-card-actions>
                      </v-container>
                    </v-card-text>
                  </v-card>

                </v-dialog>
              </v-flex>
            </v-toolbar>
            <v-list two-line>
              <v-list-item-group
                v-model="selected"
                multiple
                active-class="blue--text"
              >
                <template v-for="(item, index) in items">
                  <v-list-item
                    :key="item.title"
                    @click="postDetail(item.postId)"
                  >
                    <template v-slot:default="{ active, toggle }">
                      <v-list-item-content>
                        <v-list-item-title v-text="item.postTitle"></v-list-item-title>
                        <v-list-item-subtitle
                          class="text--primary"
                          v-text="item.createUser"
                        ></v-list-item-subtitle>
                        <v-list-item-subtitle v-text="item.postContent"></v-list-item-subtitle>
                      </v-list-item-content>

                      <v-list-item-action>
                        <v-list-item-action-text v-text="item.createTime"></v-list-item-action-text>
                        <v-icon
                          v-if="!active"
                          color="grey lighten-1"
                        >
                          star_border
                        </v-icon>

                        <v-icon
                          v-else
                          color="blue"
                        >
                          star
                        </v-icon>{{item.remarkCount}}
                      </v-list-item-action>
                    </template>
                  </v-list-item>

                  <v-divider
                    v-if="index + 1 < items.length"
                    :key="index"
                  ></v-divider>
                </template>
              </v-list-item-group>
            </v-list>
          </v-card>

        </v-layout>
      </v-container>
    </v-content>
  </v-app>
</template>
<script>
import { format } from 'url';

export default {
  data () {
    return {
      username: this.$store.state.userInfo.username,
      dialogger: false,
      keyword: '',
      postId: '',
      dialog: false,
      postTitle: '',
      postTitleRules: [
        v => !!v || 'postTitle is required',
        v => (v && v.length <= 32) || 'PostTitle must be less than 32 characters',
      ],
      postContent: '',
      postContentRules: [
        v => !!v || 'PostContent is required',
        v => (v && v.length <= 1000) || 'postContent must be less than 1000 characters',
      ],
      dearName: this.$store.state.userInfo.dearName,
      selected: [2],
      items: [
        {
          createTime: '2019/08/20 12:58',
          postTitle: 'Brunch this weekend?',
          createUser: 'Ali Connors',
          postContent: "I'll be in your neighborhood doing errands this weekend. Do you want to hang out?",
          remarkCount: 25,
        }
      ],
      errorInf: {
        errMsg: "",
        hasError: false
      }
    }
  },

  created: function () {
    this.dearName = this.$store.state.userInfo.dearName;
    this.accessToken = this.$store.state.userInfo.accessToken;
    this.getMain();
  },

  methods: {
    getMain: function () {
      let config = {
        headers: {
          "X-WALLET-ACCESS-TOKEN": this.$store.state.userInfo.accessToken,
          "X-WALLET-USER-NAME": this.$store.state.userInfo.username
        },
        method: "get",
        url: "/post/findall",
      };
      this.$utils

        .doRequest(this.$axios, this.$store, this.$router, config)
        .then(response => {
          this.items = response.data
          this.$store.commit("setUserInfo", items);
        })
        .catch(error => {
            this.errorInf.errMsg = this.$location.message.err_msg_017;
            this.errorInf.hasError = true;
        });
    },

    save: function () {
      if (!this.postTitle || !this.postContent) {
        this.errorInf.errMsg = this.$location.message.err_msg_018;
        this.errorInf.hasError = true;
      }
      this.$axios({
        headers: {
          "X-WALLET-ACCESS-TOKEN": this.$store.state.userInfo.accessToken,
          "X-WALLET-USER-NAME": this.$store.state.userInfo.username
        },
        method: "post",
        url: "/post/create",
        data: {
          postTitle: this.postTitle,
          postContent: this.postContent
        },
        transformRequest: [
          function (data) {
            return JSON.stringify(data);
          }
        ]
      })
        .then(response => {
          this.dialog = false;
          let userInfo = {
            accessToken: this.$store.state.userInfo.accessToken,
            postTitle: this.username,
            postContent: this.postContent,
            username: this.$store.state.userInfo.username,
            dearName: this.$store.state.userInfo.dearName
          };
          this.$store.commit("setUserInfo", userInfo);
          this.getMain();
        })
        .catch(error => {
          if (error.response.status != 200) {
            this.errorInf.errMsg = "Post Creation Failed!";
            this.errorInf.hasError = true;
          }
        });
    },

    searchPost: function () {
      this.$axios({
        headers: {
          "X-WALLET-ACCESS-TOKEN": this.$store.state.userInfo.accessToken,
          "X-WALLET-USER-NAME": this.$store.state.userInfo.username
        },
        method: "get",
        url: "/post/search/" + this.keyword,
      })
        .then(response => {
          this.items = response.data
          this.$store.commit("setPostInfo", this.items);
          let userInfo = {
            accessToken: this.$store.state.userInfo.accessToken,
            username: this.$store.state.userInfo.username,
            dearName: this.$store.state.userInfo.dearName
          };
          this.$store.commit("setUserInfo", userInfo);
        })
        .catch(error => {
          if (error.response.status != 200) {
            this.errorInf.errMsg = this.$location.message.err_msg_017;
            this.errorInf.hasError = true;
          }
        })
    },

    postDetail: function (postId) {
      this.$router.replace({
        name: 'postDetail',
        query: {
          postId: postId
        }
      })
    },

    selfPost: function (username) {
      debugger;
      this.$axios({
        headers: {
          "X-WALLET-ACCESS-TOKEN": this.$store.state.userInfo.accessToken,
          "X-WALLET-USER-NAME": this.$store.state.userInfo.username
        },
        methods: "get",
        url: "/selfPost/search/" + username,
      })
        .then(response => {
          this.items = response.data
          this.$store.commit("setPostInfo", this.items);
          let userInfo = {
            accessToken: this.$store.state.userInfo.accessToken,
            username: this.$store.state.userInfo.username,
            dearName: this.$store.state.userInfo.dearName
          };
          this.$store.commit("setUserInfo", userInfo);
        })
        .catch(error => {
            this.errorInf.errMsg = this.$location.message.err_msg_017;
            this.errorInf.hasError = true;
        })
    }
  }
}

</script>
<style>
@media screen and (min-width: 320px)and (max-width: width 480px){
  .v-dialog{
  width: 60%;
}
}

</style>