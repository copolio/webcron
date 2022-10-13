import {
  Configuration,
  SchedulerControllerApiFactory,
} from "../api/quartmanager";

export function useQuartzApi() {
  const config = new Configuration({
    basePath: "http://localhost:8080",
    baseOptions: { withCredentials: true },
  });
  return {
    SchedulerApi: SchedulerControllerApiFactory(config),
  };
}
