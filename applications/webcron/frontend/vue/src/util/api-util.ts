import { JobExecutionRestControllerApiFactory } from "./../api/api";
import { Configuration, SchedulerRestControllerApiFactory } from "../api";

export function useQuartzApi() {
  const config = new Configuration({
    basePath: "http://localhost",
    baseOptions: { withCredentials: true },
  });
  return {
    HttpSchedulerApi: SchedulerRestControllerApiFactory(config),
    JobExecutionApi: JobExecutionRestControllerApiFactory(config),
  };
}
