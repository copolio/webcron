<template>
  <slot :isLoading="isLoading" :isError="isError" :data="data" :error="error" />
</template>

<script setup lang="ts">
import { useQuery } from "vue-query";
import { useQuartzApi } from "../util/AxiosUtil";

const quartzApi = useQuartzApi();

const useJobGroupsQuery = () => {
  return useQuery("jobGroups", quartzApi.SchedulerApi.getGroups);
};

const { isLoading, isError, data, error } = useJobGroupsQuery();
</script>

<style scoped></style>
