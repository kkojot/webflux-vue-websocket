<template>
  <div class="home">
    <websocket-greetings></websocket-greetings>
  </div>
</template>

<script>
import WebsocketGreetings from "@/components/WebsocketGreetings.vue";

export default {
  name: "home",
  components: {
    WebsocketGreetings
  }
};
</script>