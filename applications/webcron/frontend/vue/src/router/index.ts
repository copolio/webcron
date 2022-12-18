import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import Home from "../pages/Home.vue";
import NotFound from "../pages/NotFound.vue";
import QuartzHttpJobLogBoard from "../pages/QuartzHttpJobLogBoard.vue";
import QuartzHttpScheduler from "../pages/QuartzHttpScheduler.vue";

const routes: RouteRecordRaw[] = [
  {
    path: "/",
    redirect: "/spring/http-scheduler",
  },
  {
    path: "/spring",
    name: "Spring",
    children: [
      {
        path: "http-scheduler",
        name: "QuartzHttpScheduler",
        component: QuartzHttpScheduler,
      },
      {
        path: "http-executions",
        name: "QuartzHttpJobLogBoard",
        component: QuartzHttpJobLogBoard,
        props: (route) => {
          return {
            groupName: route.query.groupName,
            jobName: route.query.jobName,
          };
        },
      },
    ],
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
