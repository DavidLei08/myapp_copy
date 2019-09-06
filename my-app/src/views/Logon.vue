<template>
  <v-app id="inspire">
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
                <v-toolbar-title>Logon</v-toolbar-title>
                <v-spacer></v-spacer>

                <v-btn
                  color="white"
                  @click="login"
                  icon
                >
                  <v-icon title="Login">last_page</v-icon>
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
                    label="username"
                    name="logon"
                    v-model.trim="username"
                    prepend-icon="person"
                    type="text"
                    @keyup.enter="logon"
                  ></v-text-field>

                  <v-text-field
                    id="password"
                    label="Password"
                    name="password"
                    v-model="password"
                    prepend-icon="lock"
                    type="password"
                    @keyup.enter="logon"
                  ></v-text-field>

                  <v-text-field
                    id="confirmPassword"
                    label="ConfirmPassword"
                    name="confirmPassword"
                    v-model="confirmPassword"
                    prepend-icon="lock"
                    type="password"
                    @keyup.enter="logon"
                  ></v-text-field>

                  <v-text-field
                    id="telephoneNum"
                    label="TelephoneNum"
                    name="telephoneNum"
                    v-model.trim="telephoneNum"
                    prepend-icon="phone"
                    type="text"
                    @keyup.enter="logon"
                  >
                  </v-text-field>

                  <v-text-field
                    id="name"
                    label="DearName"
                    name="dearName"
                    v-model.trim="dearName"
                    prepend-icon="account_circle"
                    type="text"
                    @keyup.enter="logon"
                  ></v-text-field>

                  <v-text-field
                    id="email"
                    label="Email"
                    name="email"
                    v-model.trim="email"
                    prepend-icon="email"
                    type="text"
                    @keyup.enter="logon"
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
                    >Logon</v-btn>
                  </template>
                  <v-card>
                    <v-card-title primary-title>Confirm
                    </v-card-title>
                    <v-card-text>
                      Whether to register or not?
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
                        @click="logon"
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
  name: 'login',
  data () {
    return {
      dialog: false,
      drawer: null,
      isLogining: false,
      username: '',
      password: '',
      confirmPassword: '',
      telephoneNum: '',
      dearName: '',
      email: '',
      errorInf: {
        errMsg: "",
        hasError: false
      }
    }
  },
  methods: {
    login: function () {
      this.$router.replace({
        name: "login"
      });
    },
    logon: function () {
      if (!this.username && this.password && this.email) {
        this.errorInf.errMsg = this.$location.message.err_msg_001;
        this.errorInf.hasError = true;
        return;
      }
      if (this.username && !this.password && this.email) {
        this.errorInf.errMsg = this.$location.message.err_msg_002;
        this.errorInf.hasError = true;
        return;
      }
      if (this.username && this.password && !this.email) {
        this.errorInf.errMsg = this.$location.message.err_msg_006;
        this.errorInf.hasError = true;
        return;
      }
      if (!this.username && !this.password && !this.email) {
        this.errorInf.errMsg = this.$location.message.err_msg_007;
        this.errorInf.hasError = true;
        return;
      }
      //username format check
      let result = this.$utils.checkUserName(this.username, this.$location.interface_labels.username)
      if (result.hasError) {
        this.errorInf.errMsg = result.errMsg;
        this.errorInf.hasError = true;
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
        return;
      }

      //confirm password
      if (this.password !== this.confirmPassword) {
        this.errorInf.errMsg = this.$location.message.err_msg_016;
        this.errorInf.hasError = true;
        return;
      }

      //telephoneNum format check
      let telPhoneCheck = this.$utils.checkTelPhone(this.telephoneNum, this.$location.interface_labels.telephoneNum)
      if (telPhoneCheck.hasError) {
        this.errorInf.errMsg = telPhoneCheck.errMsg;
        this.errorInf.hasError = true;
        return;
      }

      //telPhone length check
      let telPhoneLengthCheck = this.$utils.checkTelPhoneLength(this.telephoneNum, this.$location.interface_labels.telephoneNum)
      if (telPhoneLengthCheck.hasError) {
        this.errorInf = telPhoneLengthCheck;
        return;
      }

      //dearName length check
      let nameLengthCheck = this.$utils.checkNameLength(
        this.dearName,
        this.$location.interface_labels.dearName,
        32
      );
      if (nameLengthCheck.hasError) {
        this.errorInf = nameLengthCheck;
        return;
      }

      //email format check
      let emailCheck = this.$utils.checkEmail(this.email, this.$location.interface_labels.email)
      if (emailCheck.hasError) {
        this.errorInf.errMsg = emailCheck.errMsg;
        this.errorInf.hasError = true;
        return;
      }

      this.$axios({
        method: "post",
        url: '/logon',
        data: {
          username: this.username,
          password: md5(this.password),
          telephoneNum: this.telephoneNum,
          dearName: this.dearName,
          email: this.email
        },
        transformRequest: [
          function (data) {
            return JSON.stringify(data);
          }
        ]
      })
        .then(response => {
          this.dialog = false;
          this.$router.replace({
            name: "login"
          });
        })
        .catch(error => {
          if (error.response.status != 200) {
            this.errorInf.errMsg = "User information is wrong ,failed to register!"
            this.errorInf.hasError = true;
          }
        })
    }
  }
};
</script>
