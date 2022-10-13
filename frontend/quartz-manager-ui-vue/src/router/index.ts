import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import JobGroupList from "../views/JobGroupList.vue";

const routes: RouteRecordRaw[] = [];

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: "/",
      name: "Home",
      component: JobGroupList,
    },
  ],
});

export default router;
