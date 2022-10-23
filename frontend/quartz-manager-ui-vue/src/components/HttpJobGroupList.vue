<template>
  <slot :isLoading="isLoading" :isError="isError" :data="data" :error="error" :refetch="refetch" />
</template>

<script setup lang="ts">
import { AxiosError, AxiosResponse } from "axios";
import { useQuery } from "vue-query";
import { GetJobGroupResponse } from "../api/quartmanager";
import { useQuartzApi } from "../util/AxiosUtil";

const quartzApi = useQuartzApi();

const useJobGroupQuery = () => {
  return useQuery<AxiosResponse<Array<GetJobGroupResponse>>, AxiosError<string>>(
    "jobGroups",
    quartzApi.HttpSchedulerApi.getGroups
  );
};

const { isLoading, isError, data, error, refetch } = useJobGroupQuery();
</script>
