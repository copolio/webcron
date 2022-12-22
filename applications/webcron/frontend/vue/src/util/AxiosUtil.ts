import { Configuration, HttpSchedulerRestControllerApiFactory } from "../api";

export function useQuartzApi() {
  const config = new Configuration({
    basePath: "http://localhost",
    baseOptions: { withCredentials: true },
  });
  return {
    HttpSchedulerApi: HttpSchedulerRestControllerApiFactory(config),
  };
}
