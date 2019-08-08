<template>
  <div>
    <div
      id="main-content"
      class="container"
    >
      <div class="row">
        <div class="col-md-6">
          <form class="form-inline">
            <div class="form-group">
              <label for="connect">WebSocket connection:</label>
              <button
                id="connect"
                class="btn btn-default"
                type="submit"
                :disabled="connected == true"
                @click.prevent="connect"
              >Connect</button>
              <button
                id="disconnect"
                class="btn btn-default"
                type="submit"
                :disabled="connected == false"
                @click.prevent="disconnect"
              >Disconnect
              </button>
            </div>
          </form>
        </div>
        <div class="col-md-6">
          <form class="form-inline">
            <div class="form-group">
              <label for="name">What is your name?</label>
              <input
                type="text"
                id="name"
                class="form-control"
                v-model="send_message"
                placeholder="Your name here..."
              >
            </div>
            <button
              id="send"
              class="btn btn-default"
              type="submit"
              @click.prevent="send"
            >Send</button>
          </form>
        </div>
      </div>
      <div class="row">
        <div class="col-md-12">
          <table
            id="conversation"
            class="table table-striped"
          >
            <thead>
              <tr>
                <th>Greetings</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="item in received_messages"
                :key="item"
              >
                <td>{{ item }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "websocketdemo",
  data() {
    return {
      websocketUrl: "ws://localhost:8080/greetings",
      received_messages: [],
      send_message: null,
      connected: false
    };
  },
  methods: {
    send() {
      console.log("Send message:" + this.send_message);
      if (this.connected) {
        const msg = { name: this.send_message };
        console.log(JSON.stringify(msg));
        this.socket.send(JSON.stringify(msg));
      }
    },
    connect() {
      this.socket = new WebSocket(this.websocketUrl);
      this.socket.onopen = () => {
        console.log("connected to " + this.websocketUrl);
        this.connected = true;
      };

      this.socket.onerror = error => {
        console.log("could not connect to " + this.websocketUrl);
        console.log(error);
      };

      this.socket.onmessage = message => {
        console.log(message.data);
        this.received_messages.push(JSON.parse(message.data).content);
      };

      this.socket.onclose = error => {
        console.log("disconnect from " + this.websocketUrl);
        this.connected = false;
        console.log(error);
      };
    },
    disconnect() {
      console.log("trying to disconnect..");
      this.socket.close();
    }
  },
  mounted() {
    // this.connect();
  }
};
</script>