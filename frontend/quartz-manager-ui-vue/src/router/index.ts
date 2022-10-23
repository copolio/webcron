import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import HttpJobTree from "../views/HttpJobTree.vue";

const routes: RouteRecordRaw[] = [];

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: "/",
      name: "Home",
      component: HttpJobTree,
    },
  ],
});

export default router;
