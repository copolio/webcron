import { AxiosError, AxiosPromise, AxiosResponse } from "axios";
import { ref } from "vue";
import { useMutation, useQuery } from "vue-query";
import {
  CreateHttpJob,
  CreateHttpJobResult,
  DefaultError,
  SchedulerJobGroupQueryResult,
} from "../api";
import { useQuartzApi } from "../util/api-util";

const quartzApi = useQuartzApi();

export function useHttpJobGroupQuery() {
  return useQuery<
    AxiosResponse<Array<SchedulerJobGroupQueryResult>>,
    AxiosError<DefaultError>
  >("jobGroups", quartzApi.HttpSchedulerApi.getGroups);
}

export function useHttpJobQuery(groupName: string) {
  const queryKey = ["jobs", groupName];
  return useQuery<
    AxiosResponse<Array<SchedulerJobGroupQueryResult>>,
    AxiosResponse<DefaultError>
  >(queryKey, () => quartzApi.HttpSchedulerApi.getJobs(groupName));
}

export function useAddHttpJobMutation(newJob: CreateHttpJob) {
  return useMutation<
    AxiosResponse<CreateHttpJobResult>,
    AxiosError<DefaultError>
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
      quartzApi.JobExecutionApi.getJobExecutions(
        searchCondition.value.groupName,
        searchCondition.value.jobName,
        searchCondition.value.page,
        searchCondition.value.size,
        searchCondition.value.sort
      )
    ),
  };
}
