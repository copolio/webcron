import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import NotFound from "../pages/NotFound.vue";
import HttpLogBoard from "../pages/HttpLogBoard.vue";
import HttpCrontab from "../pages/HttpCrontab.vue";

const routes: RouteRecordRaw[] = [
  {
    path: "/",
    name: "HttpCrontab",
    component: HttpCrontab,
  },
  {
    path: "/logs",
    name: "HttpLogBoard",
    component: HttpLogBoard,
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
    component: NotFound,
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
