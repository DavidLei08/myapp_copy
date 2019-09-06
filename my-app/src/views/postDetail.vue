<template>
  <v-app>
    <Top></Top>
    <v-content>
      <v-snackbar
        v-model="errorInf.hasError"
        :timeout=5000
        top
        color="error"
      >
        {{ errorInf.errMsg }}
      </v-snackbar>
      <v-snackbar
        v-model="successInfo.hasSuccess"
        :timeout=5000
        top
        color="success"
      >
        {{ successInfo.successMsg }}
      </v-snackbar>
      <v-container>
        <v-card
        width="98%"
          style="height:auto"
          class="mx-auto"
        >
          <v-list-item>
            <v-list-item-avatar color="primary"></v-list-item-avatar>
            <v-list-item-content>
              <v-list-item-title class="headline">{{postDetail.postTitle}}</v-list-item-title>
              <v-list-item-subtitle>
                by {{ postDetail.createUser }}
                </v-list-item-subtitle>
            </v-list-item-content>
          </v-list-item>

          <v-img
            src="https://cdn.vuetifyjs.com/images/cards/mountain.jpg"
            height="200"
          ></v-img>

          <v-card-text  v-html="postDetail.postContent">
          </v-card-text>

          <v-card-actions>
            <div class="flex-grow-1"></div>
            <v-dialog
              v-model="dialogger"
              transition="dialog-bottom-transition"
              persistent
              width="25%"
            >
              <template v-slot:activator="{ on }">
                <v-btn
                  v-if="delFlag"
                  color="error"
                  icon
                  v-on="on"
                >
                  <v-icon>delete</v-icon>
                </v-btn>
              </template>
              <v-card>
                <v-card-title primary-title>Confirm</v-card-title>
                <v-card-text>
                  Whether to delete a post or not?
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
                    @click="deletePost"
                  >Yes</v-btn>
                </v-card-actions>
              </v-card>
            </v-dialog>
            &nbsp;
            <v-icon color="primary">star</v-icon>{{postDetail.remarkCount}}&nbsp;&nbsp;
            <v-icon color="primary">date_range</v-icon>{{ postDetail.createTime }}
          </v-card-actions>
          <v-card-actions>
                      

            <v-flex 
            width="50%">
              <v-text-field
                v-model="remarkContent"
                placeholder="write remark"
                @keyup.enter="creatRemark"

              >
              </v-text-field>
            </v-flex>
             <v-btn
              @click="creatRemark"
              color="primary"
              icon
            >
              <v-icon>mdi-pencil</v-icon>
            </v-btn>
          </v-card-actions>

          <v-divider></v-divider>
          
        </v-card>
        <v-card
        width="98%"
          style="height:auto"
          class="mx-auto"
        >
          <v-list-item
            border='1'
            v-for="item in postDetail.remarkList"
            :key="item.remarkId"
          >

            <v-list-item-content>
              <v-list-item-title v-text="item.remarkContent"></v-list-item-title>
              <v-list-item-subtitle v-text="item.remarkUser"></v-list-item-subtitle>
            </v-list-item-content>

            <v-list-item-action>
              <v-spacer></v-spacer>

              <v-dialog
                v-model="dialog"
                transition="dialog-bottom-transition"
                persistent
                width="280px"
              >
                <template v-slot:activator="{ on }">
                  <v-btn
                    color="error"
                    v-if="item.remarkUser==$store.state.userInfo.username"
                    icon
                     @click="delRemark(item.remarkId)"
                  >
                    <v-icon>delete</v-icon>
                  </v-btn>
                </template>
                <v-card>
                  <v-card-title primary-title>Confirm</v-card-title>
                  <v-card-text>
                    Whether to delete or not?
                  </v-card-text>
                  <v-divider></v-divider>
                  <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn
                      color="primary"
                      @click="dialog = false"
                    >No</v-btn>
                    <v-btn
                      color="primary"
                      @click="delRemark(item.remarkId)"
                    >Yes</v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
              <div class="flex-grow-1"></div>
              <v-list-item-subtitle>{{item.remarkTime}}</v-list-item-subtitle>
            </v-list-item-action>
          </v-list-item>
        </v-card>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
export default {
  data () {
    return {
      dialogger: false,
      dialog: false,
      errorInf: {
        errMsg: "",
        hasError: false
      },
      successInfo: {
        successMsg: "",
        hasSuccess: false
      },
      remarkContent: '',
      bank: ' ',
      postDetail: [
        {
          createTime: '',
          postTitle: '',
          createUser: '',
          postContent: '',
          remarkCount: '',
          remarkList: [],
        }
      ],
      dearName: this.$store.state.userInfo.dearName,
      postId: 0,
      delFlag: false,
    }
  },
  created: function () {
    this.postId = this.$route.query.postId;
    this.getDetail();
  },
  methods: {
    creatRemark: function () {
      this.$axios({
        headers: {
          "X-WALLET-ACCESS-TOKEN": this.$store.state.userInfo.accessToken,
          "X-WALLET-USER-NAME": this.$store.state.userInfo.username
        },
        method: "post",
        url: "/remark/create",
        data: {
          postId: this.postId,
          remarkContent: this.remarkContent
        },
        transformRequest: [
          function (data) {
            return JSON.stringify(data);
          }
        ]
      })
        .then(response => {
          this.successInfo.successMsg = "Remarks Created Successfully!";
          this.successInfo.hasSuccess = true;
          let remarkInfo = {
            accessToken: this.$store.state.userInfo.accessToken,
            postId: this.postId,
            remarkContent: this.remarkContent
          }
          this.$store.commit("setRemarkInfo", remarkInfo);
          this.getDetail();
          this.remarkContent = ''

        })
        .catch(error => {
          if (error.response.status != 200) {
            this.errorInf.errMsg = this.$location.message.err_msg_017;
            this.errorInf.hasError = true;
          }
        });
    },
    delRemark: function (remarkId) {
      debugger;
      this.$axios({
        headers: {
          "X-WALLET-ACCESS-TOKEN": this.$store.state.userInfo.accessToken,
          "X-WALLET-USER-NAME": this.$store.state.userInfo.username
        },

        method: "get",
        url: "/remark/delete/" + remarkId,
      })
        .then(response => {
          this.dialog = false;
          this.successInfo.successMsg = "Successful deletion of remarks!";
          this.successInfo.hasSuccess = true;
          this.getDetail();
        })
        .catch(error => {
            this.errorInf.errMsg = this.$location.message.err_msg_017;
            this.errorInf.hasError = true;
        })
    },

    deletePost: function () {
      this.$axios({
        headers: {
          "X-WALLET-ACCESS-TOKEN": this.$store.state.userInfo.accessToken,
          "X-WALLET-USER-NAME": this.$store.state.userInfo.username
        },
        method: "get",
        url: "/post/delete/" + this.postId,
      })
        .then(response => {
          this.dialog = false;
          this.$router.replace({
            name: "main"
          });
        })
        .catch(error => {
          if (error.response.status != 200) {
            this.errorInf.errMsg = this.$location.message.err_msg_017;
            this.errorInf.hasError = true;
          }
        })
    },

    getDetail: function () {
      this.$axios({
        headers: {
          "X-WALLET-ACCESS-TOKEN": this.$store.state.userInfo.accessToken,
          "X-WALLET-USER-NAME": this.$store.state.userInfo.username
        },
        method: "get",
        url: "/post/finddetail/" + this.postId,
      })
        .then(response => {
          this.postDetail = response.data;
          this.formatText();
          this.$store.commit("setPostInfo", this.postDetail);
          if (this.postDetail.createUser == this.$store.state.userInfo.username) {
            this.delFlag = true;
          }
        })
        .catch(error => {
            this.errorInf.errMsg = this.$location.message.err_msg_017;
            this.errorInf.hasError = true;
        })
    },
    formatText:function(){
      this.postDetail.postContent=this.postDetail.postContent.replace(/\n|\r\n/g,"<br/>");
      this.postDetail.postContent=this.postDetail.postContent.replace(/\s+/g,'&nbsp;');
    }
  }
}
</script>
