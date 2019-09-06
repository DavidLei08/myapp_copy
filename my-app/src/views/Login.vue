<template>
  <v-app id="inspire">
    <v-content>
      <Loading :isLoading="isLogining"></Loading>
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
                <v-toolbar-title>Login</v-toolbar-title>

                <v-spacer></v-spacer>
                <v-btn
                  color="white"
                  @click="logon"
                  icon
                >
                  <v-icon title="Logon">last_page</v-icon>
                </v-btn>
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
                <v-form>
                  <v-text-field
                    id="username"
                    label="Username"
                    :rules="nameRules"
                    name="username"
                    v-model.trim="username"
                    prepend-icon="person"
                    type="text"
                    @keyup.enter="login"
                  ></v-text-field>

                  <v-text-field
                    id="password"
                    label="Password"
                    :rules="passwordRules"
                    name="password"
                    v-model="password"
                    prepend-icon="lock"
                    type="password"
                    @keyup.enter="login"
                  ></v-text-field>
                </v-form>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn
                  color="primary"
                  @click="login"
                >Login</v-btn>
              </v-card-actions>
            </v-card>
          </v-col>
        </v-row>
      </v-container>
    </v-content>
  </v-app>
</template>

<script>
import Loading from "../components/Loading";
const md5 = require("js-md5");

export default {
  name: 'login',
  data () {
    return {
      drawer: null,
      isLogining: false,
      username: '',
      nameRules: [
        v => !!v || 'Username is required',
        v => (v && v.length <= 32) || 'Username must be less than 32 characters',
      ],
      password: '',
      passwordRules: [
        v => !!v || 'Password is required',
        v => (v && v.length <= 32) || 'Password must be less than 32 characters',
      ],
      errorInf: {
        errMsg: "",
        hasError: false
      }
    }
  },
  methods: {
    login: function () {
      this.isLogining = true;

      //username format check
      let result = this.$utils.checkUserName(this.username, this.$location.interface_labels.username)
      if (result.hasError) {
        this.errorInf.errMsg = result.errMsg;
        this.errorInf.hasError = true;
        this.isLogining = false;
        return;
      }

      //username length check
      let usernameLengthCheck = this.$utils.checkUserNameLength(
        this.username,
        this.$location.interface_labels.username,
        32
      );

      if (usernameLengthCheck.hasError) {
        this.errorInf = usernameLengthCheck;
        this.isLogining = false;
        return;
      }

      this.$axios({
        method: "post",
        url: '/login',
        data: {
          username: this.username,
          password: md5(this.password)
        },
        transformRequest: [
          function (data) {
            return JSON.stringify(data);
          }
        ]
      })
        .then(response => {

          let userInfo = {
            accessToken: response.data.accessToken,
            dearName: response.data.dearName,
            username: response.data.username,
          };
          this.$store.commit("setUserInfo", userInfo);
          this.$router.replace({
            name: "main"
          });
          this.isLogining = false;
        })
        .catch(error => {
          if (!error.response) {
            this.errorInf.errMsg = this.$location.message.err_msg_011;
            this.errorInf.hasError = true;
          } else if (error.response.status == 401) {
            this.errorInf.errMsg = this.$location.message.err_msg_012;
            this.errorInf.hasError = true;
          } else if (error.response.status == 405) {
            this.errorInf.errMsg = this.$location.message.err_msg_013;
            this.errorInf.hasError = true;
          } else if (error.response.status == 500) {
            this.errorInf.errMsg = this.$location.message.err_msg_017;
            this.errorInf.hasError = true;
          }
          this.isLogining = false;

        });

    },
    logon: function () {
      this.$router.replace({
        name: "logon"
      });
    }
  },
  components: {
    Loading
  }
}
</script>