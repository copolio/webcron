import vue from "@vitejs/plugin-vue";
import { AntDesignVueResolver } from "unplugin-vue-components/resolvers";
import Components from "unplugin-vue-components/vite";
import { defineConfig } from "vite";

// your plugin installation
Components({
  resolvers: [AntDesignVueResolver()],
});

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  build: {
    outDir: "../../backend/src/main/resources/static",
  },
  server: {
    port: 3000,
  },
});
