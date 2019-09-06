<template>
  <v-app-bar
    app
    color="primary"
    dark
  >
    <v-toolbar-title>MY POST BAR</v-toolbar-title>
    <v-spacer></v-spacer>

    <v-btn
      icon
      @click="home"
    >
      <v-icon title="Home">account_circle</v-icon>
    </v-btn>
      {{dearName}}
    <v-menu
      left
      bottom
      offset-y
    >
      <template v-slot:activator="{ on }">
        <v-btn
          icon
          v-on="on"
        >
          <v-icon>keyboard_arrow_down</v-icon>
        </v-btn>
      </template>

      <v-list>
        <v-list-item @click="settings">
          <v-list-item-icon>
            <v-icon>settings</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Modify information</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item @click="updatePassword">
          <v-list-item-icon>
            <v-icon>lock</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Change password</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
        <v-list-item @click="logout">
          <v-list-item-icon>
            <v-icon>exit_to_app</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>Logout</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-menu>
  </v-app-bar>
</template>

<script>
export default {
  data () {
    return {
      dearName: this.$store.state.userInfo.dearName,
    }
  },
  methods: {
    home: function () {
      this.$router.replace({
        name: "main"
      });
    },

    settings: function () {
      this.$router.replace({
        name: "updateUser"
      });
    },

    updatePassword: function () {
      this.$router.replace({
        name: "updatePassword"
      });
    },

    logout: function () {
      this.$axios({
        headers: {
          "X-WALLET-ACCESS-TOKEN": this.$store.state.userInfo.accessToken,
          "X-WALLET-USER-NAME": this.$store.state.userInfo.username
        },
        methods: "get",
        url: "/logout",
      })
        .then(response => {
          this.$router.replace({
            name: "login"
          });
        })
    }
  }
}
</script>
