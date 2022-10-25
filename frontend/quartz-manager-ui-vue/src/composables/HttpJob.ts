import { AxiosError, AxiosResponse } from "axios";
import { useMutation, useQuery } from "vue-query";
import {
  GetHttpJobResponse,
  GetJobGroupResponse,
  PostHttpJobRequest,
} from "../api/quartmanager";
import { useQuartzApi } from "../util/AxiosUtil";

const quartzApi = useQuartzApi();

export function useHttpJobGroupQuery() {
  return useQuery<
    AxiosResponse<Array<GetJobGroupResponse>>,
    AxiosError<string>
  >("jobGroups", quartzApi.HttpSchedulerApi.getGroups);
}

export function useHttpJobQuery(groupName: string) {
  const queryKey = ["jobs", groupName];
  return useQuery<
    AxiosResponse<Array<GetHttpJobResponse>>,
    AxiosResponse<string>
  >(queryKey, () => quartzApi.HttpSchedulerApi.getJobs(groupName));
}

export function useAddHttpJobMutation(newJob: PostHttpJobRequest) {
  return useMutation(() => quartzApi.HttpSchedulerApi.addJob(newJob));
}

export function useDeleteHttpJobMutation(groupName: string, jobName: string) {
  return useMutation(() =>
    quartzApi.HttpSchedulerApi.deleteJob(groupName, jobName)
  );
}
