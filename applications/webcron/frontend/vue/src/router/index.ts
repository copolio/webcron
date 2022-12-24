import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";

const routes: RouteRecordRaw[] = [
  {
    path: "/",
    name: "HttpCrontab",
    component: () => import("../pages/HttpCrontab.vue"),
  },
  {
    path: "/logs",
    name: "HttpLogBoard",
    component: () => import("../pages/HttpLogBoard.vue"),
    props: (route) => {
      return {
        groupName: route.query.groupName,
        jobName: route.query.jobName,
      };
    },
  },
  {
    path: "/404",
    name: "NotFound",
    component: () => import("../pages/NotFound.vue"),
  },
  {
    path: "/:pathMatch(.*)*",
    redirect: "/404",
  },
];

const router = createRouter({
  history: createWebHashHistory(),
  routes: routes,
});

export default router;
