import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import QuartzJobTreeVue from "../views/QuartzJobTree.vue";

const routes: RouteRecordRaw[] = [];

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: "/",
      name: "Home",
      component: QuartzJobTreeVue,
    },
  ],
});

export default router;
