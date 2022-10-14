<template>
  <slot
    :isLoading="isLoading"
    :isError="isError"
    :data="data"
    :error="error"
    :refetch="refetch"
  />
</template>

<script setup lang="ts">
import { AxiosError, AxiosResponse } from "axios";
import { useQuery } from "vue-query";
import { useQuartzApi } from "../util/AxiosUtil";

const quartzApi = useQuartzApi();

const useJobGroupsQuery = () => {
  return useQuery<AxiosResponse<string[]>, AxiosError<string>>(
    "jobGroups",
    quartzApi.SchedulerApi.getGroups
  );
};

const { isLoading, isError, data, error, refetch } = useJobGroupsQuery();
</script>

<style scoped></style>
