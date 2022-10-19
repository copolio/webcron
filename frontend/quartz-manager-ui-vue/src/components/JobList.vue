<template>
    <slot :isLoading="isLoading" :isError="isError" :data="data" :error="error" :refetch="refetch" />
</template>

<script setup lang="ts">
import { AxiosResponse } from 'axios';
import { useQuery } from 'vue-query';
import { GetRestJobResponse } from '../api/quartmanager';
import { useQuartzApi } from '../util/AxiosUtil';

const props = defineProps<{ groupName: string }>();

const quartzApi = useQuartzApi();
const queryKey = ["jobs", props.groupName]
const useJobQuery = () => {
    return useQuery<AxiosResponse<Array<GetRestJobResponse>>, AxiosResponse<string>>(queryKey, () => quartzApi.SchedulerApi.getJobs(props.groupName));
}

const { isLoading, isError, data, error, refetch } = useJobQuery();
</script>