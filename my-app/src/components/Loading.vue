<template>
  <div
    class="v-overlay v-overlay--active"
    v-if="isLoading"
  >
    <v-layout
      class="loading-lay"
      justify-center
      align-center
    >
      <v-progress-circular
        indeterminate
        color="white"
        :width="4"
        :size="80"
      ></v-progress-circular>
    </v-layout>
  </div>
</template>

<script>
export default {
  props: {
    isLoading: Boolean
  },
  data () {
    return {
    }
  },
}
</script>

<style scoped>
div .v-overlay .v-overlay--active {
  z-index: 201;
}
.loading-lay {
  height: 100%;
}
</style>
