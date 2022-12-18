import { AxiosError, AxiosPromise, AxiosResponse } from "axios";
import { ref } from "vue";
import { useMutation, useQuery } from "vue-query";
import {
  GetHttpJobResponse,
  GetJobGroupResponse,
  PostHttpJobRequest,
} from "../api";
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
  return useMutation<
    AxiosResponse<GetHttpJobResponse>,
    AxiosError<{ error: string; message: string }>
  >(() => quartzApi.HttpSchedulerApi.addJob(newJob));
}

export function useDeleteHttpJobMutation(groupName: string, jobName: string) {
  return useMutation(() =>
    quartzApi.HttpSchedulerApi.deleteJob(groupName, jobName)
  );
}

export function useHttpJobExecutionQuery() {
  const searchCondition = ref({
    groupName: "",
    jobName: "",
    page: 0,
    size: 20,
    sort: ["instanceId,desc"],
  });
  const queryKey = ["httpJobExecutions", searchCondition];

  return {
    searchCondition,
    ...useQuery(queryKey, () =>
      quartzApi.HttpSchedulerApi.getJobExecutions(
        searchCondition.value.groupName,
        searchCondition.value.jobName,
        searchCondition.value.page,
        searchCondition.value.size,
        searchCondition.value.sort
      )
    ),
  };
}
