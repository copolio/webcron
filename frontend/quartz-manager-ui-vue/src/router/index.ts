import { createRouter, createWebHashHistory, RouteRecordRaw } from "vue-router";
import Home from "../pages/Home.vue";
import NotFound from "../pages/NotFound.vue";
import QuartzHttpScheduler from "../pages/QuartzHttpScheduler.vue";

const routes: RouteRecordRaw[] = [
  {
    path: "/",
    redirect: "/home",
  },
  {
    path: "/home",
    name: "Home",
    component: Home,
  },
  {
    path: "/quartz",
    name: "Quartz",
    children: [
      {
        path: "http-scheduler",
        name: "QuartzHttpScheduler",
        component: QuartzHttpScheduler,
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
