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
            <v-card>
              <v-toolbar
                color="primary"
                dark
                flat
              >
                <v-toolbar-title>updateUser</v-toolbar-title>
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
                <v-form>
                  <v-text-field
                    id="username"
                    label="Username"
                    name="logon"
                    v-model.trim="username"
                    prepend-icon="person"
                    type="text"
                    v-bind:disabled="true"
                    :rules="usernameRules"
                  ></v-text-field>

                  <v-text-field
                    id="telephoneNum"
                    label="TelephoneNum"
                    name="telephoneNum"
                    v-model.trim="telephoneNum"
                    prepend-icon="phone"
                    type="text"
                    v-bind:disabled="false"
                    @keyup.enter="updateUser"
                    :rules="telephoneNumRules"
                  ></v-text-field>

                  <v-text-field
                    id="name"
                    label="DearName"
                    name="dearName"
                    v-model.trim="dearName"
                    prepend-icon="account_circle"
                    type="text"
                    v-bind:disabled="false"
                    @keyup.enter="updateUser"
                    :rules="dearNameRules"
                  ></v-text-field>

                  <v-text-field
                    id="email"
                    label="Email"
                    name="email"
                    v-model.trim="email"
                    prepend-icon="email"
                    type="text"
                    v-bind:disabled="false"
                    @keyup.enter="updateUser"
                    :rules="emailRules"
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
                    >updateUser</v-btn>
                  </template>
                  <v-card>
                    <v-card-title primary-title>Confirm</v-card-title>
                    <v-card-text>
                      Whether to updateUser or not?
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
                        @click="updateUser"
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

export default {
  name: 'updateUser',
  data () {
    return {
      dialog: false,
      topdearName: this.$store.state.userInfo.dearName,
      drawer: null,
      isLogining: false,
      username: '',
      telephoneNum: '',
      dearName: '',
      email: '',
      emailRules: [
        v => !!v || 'Email is required',
        v => /.+@.+\..+/.test(v) || 'Email must be valid',
      ],
      usernameRules: [
        v => !!v || 'Username is required',
        v => (v && v.length <= 32) || 'Name must be less than 32 characters',
      ],
      telephoneNumRules: [
        v => !!v || 'telephoneNum is required',
      ],
      dearNameRules: [
        v => (v && v.length <= 32) || 'DearName must be less than 32 characters',
      ],
      errorInf: {
        errMsg: "",
        hasError: false
      }
    }

  },
  created: function () {
    let config = {
      headers: {
        "X-WALLET-ACCESS-TOKEN": this.$store.state.userInfo.accessToken,
        "X-WALLET-USER-NAME": this.$store.state.userInfo.username
      },
      methods: "get",
      url: "/updateuser",
    };
    this.$utils
      .doRequest(this.$axios, this.$store, this.$router, config)
      .then(response => {
        this.username = response.data.username;
        this.telephoneNum = response.data.telephoneNum;
        this.dearName = response.data.dearName;
        this.email = response.data.email;
      })
      .catch(error => {
        if (error.response.status != 200) {
          this.errorInf.errMsg = this.$location.message.err_msg_017;
          this.errorInf.hasError = true;
        }
      });
    // let userInfo = {
    //   username: this.name,
    //   telephoneNum: this.telephoneNum,
    //   dearName: this.dearName,
    //   email: this.email,
    // };
    // this.$store.commit("setUserInfo", userInfo);
  },
  methods: {
    updateUser: function () {
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

      // let userInfo = {
      //   username: this.name,
      //   telephoneNum: this.telephoneNum,
      //   dearName: this.dearName,
      //   email: this.email,
      // };
      // this.$store.commit("setUserInfo", userInfo);

      this.$axios({
        headers: {
          "X-WALLET-ACCESS-TOKEN": this.$store.state.userInfo.accessToken,
          "X-WALLET-USER-NAME": this.$store.state.userInfo.username
        },
        method: "post",
        url: '/updateuser',
        data: {
          username: this.username,
          telephoneNum: this.telephoneNum,
          dearName: this.dearName,
          email: this.email,
        },
        transformRequest: [
          function (data) {
            return JSON.stringify(data);
          }
        ]
      })
        .then(response => {
          let userInfo = {
            accessToken: this.$store.state.userInfo.accessToken,
            username: this.username,
            telephoneNum: this.telephoneNum,
            dearName: this.dearName,
            email: this.email,
          };
          this.$store.commit("setUserInfo", userInfo);
          this.topdearName = this.$store.state.userInfo.dearName;
          this.$router.replace({
            name: "main"
          });
        })

    },

  }
}
</script>