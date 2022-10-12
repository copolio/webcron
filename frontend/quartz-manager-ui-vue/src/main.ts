import Antd from "ant-design-vue";
import "ant-design-vue/dist/antd.css";
import { createPinia } from "pinia";
import { createApp } from "vue";
import App from "./App.vue";
const app = createApp(App);
const pinia = createPinia();

app.use(Antd);
app.use(pinia);
app.mount("#app");
