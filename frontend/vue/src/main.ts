import Antd from "ant-design-vue";
import "ant-design-vue/dist/antd.css";
import { createPinia } from "pinia";
import { createApp } from "vue";
import { VueQueryPlugin, VueQueryPluginOptions } from "vue-query";
import App from "./App.vue";
import router from "./router";

const app = createApp(App);
const pinia = createPinia();

const vueQueryPluginOptions: VueQueryPluginOptions = {
  queryClientConfig: {
    defaultOptions: {
      queries: {
        refetchOnWindowFocus: false,
      },
    },
  },
};

app.use(Antd);
app.use(pinia);
app.use(router);
app.use(VueQueryPlugin, vueQueryPluginOptions);
app.mount("#app");
