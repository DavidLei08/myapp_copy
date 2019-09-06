<template>
  <v-app>
    <Top></Top>
    <v-content>
      <v-container
        class="fill-height"
        fluid
      >
        <v-row
          align="center"
          justify="center"
        >
          <v-col
            cols="12"
            sm="8"
            md="4"
          >
            <v-card class="elevation-12">
              <v-toolbar
                color="primary"
                dark
                flat
              >
                <v-toolbar-title>UpdatePassword</v-toolbar-title>
                <v-spacer></v-spacer>

              </v-toolbar>

              <v-snackbar
                v-model="errorInf.hasError"
                :timeout=5000
                top
                color="error"
              >
                {{ errorInf.errMsg }}
              </v-snackbar>

              <v-card-text>

              </v-card-text>

              <v-card-text>
                <v-form>
                  <v-text-field
                    id="oldPassword"
                    label="OldPassword"
                    name="oldPassword"
                    v-model="oldPassword"
                    prepend-icon="lock"
                    type="password"
                    @keyup.enter="updatePassword"
                  ></v-text-field>

                  <v-text-field
                    id="newPassword"
                    label="NewPassword"
                    name="newPassword"
                    v-model="newPassword"
                    prepend-icon="lock"
                    type="password"
                    @keyup.enter="updatePassword"
                  ></v-text-field>

                  <v-text-field
                    id="confirmPassword"
                    label="ConfirmPassword"
                    name="confirmPassword"
                    v-model="confirmPassword"
                    prepend-icon="lock"
                    type="password"
                    @keyup.enter="updatePassword"
                  ></v-text-field>
                </v-form>
              </v-card-text>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-dialog
                  v-model="dialog"
                  transition="dialog-bottom-transition"
                  persistent
                  max-width="20%"
                >
                  <template v-slot:activator="{ on }">
                    <v-btn
                      color="primary"
                      v-on="on"
                    >UpdatePassword</v-btn>
                  </template>
                  <v-card>
                    <v-card-title primary-title>Confirm</v-card-title>
                    <v-card-text>
                      Whether to updatePassword or not?
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
                        @click="updatePassword"
                      >Yes</v-btn>
                    </v-card-actions>
                  </v-card>
                </v-dialog>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
const md5 = require("js-md5");
export default {
  data () {
    return {
      dialog: false,
      topdearName: this.$store.state.userInfo.dearName,
      oldPassword: '',
      newPassword: '',
      confirmPassword: '',
      errorInf: {
        errMsg: "",
        hasError: false
      }
    }
  },
  methods: {
    updatePassword: function () {
      //confirm password
      debugger
      if (this.newPassword !== this.confirmPassword) {
        this.errorInf.errMsg = this.$location.message.err_msg_007;
        this.errorInf.hasError = true;
        return;
      }

      this.$axios({
        headers: {
          "X-WALLET-ACCESS-TOKEN": this.$store.state.userInfo.accessToken,
          "X-WALLET-USER-NAME": this.$store.state.userInfo.username
        },
        method: "post",
        url: '/updatepassword',
        data: {
          username: this.$store.state.userInfo.username,
          oldPassword: md5(this.oldPassword),
          newPassword: md5(this.newPassword),
        },
        transformRequest: [
          function (data) {
            return JSON.stringify(data);
          }
        ]
      })
        .then(response => {
          debugger;
          let userInfo = {
            accessToken: this.$store.state.userInfo.accessToken,
            username: this.$store.state.userInfo.username,
            password: this.newPassword,
            dearName: this.$store.state.userInfo.dearName,
            email: this.$store.state.userInfo.email,
          };
          this.$store.commit("setUserInfo", userInfo);
          this.topdearName = this.$store.state.userInfo.dearName;
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

  }
}
</script>