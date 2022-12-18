import { Configuration, HttpSchedulerControllerApiFactory } from "../api";

export function useQuartzApi() {
  const config = new Configuration({
    basePath: "http://localhost",
    baseOptions: { withCredentials: true },
  });
  return {
    HttpSchedulerApi: HttpSchedulerControllerApiFactory(config),
  };
}
