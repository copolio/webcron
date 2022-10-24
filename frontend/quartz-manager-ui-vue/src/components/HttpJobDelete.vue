<template>
    <slot :isLoading="isLoading" :mutate="mutate" />
</template>

<script setup lang="ts">
import { useMutation } from 'vue-query';
import { useQuartzApi } from '../util/AxiosUtil';

interface PropsInterface {
    groupName: string;
    jobName: string;
}
const quartzApi = useQuartzApi();
const props = defineProps<PropsInterface>();

function deleteJobMutation() {
    return useMutation(() => quartzApi.HttpSchedulerApi.deleteJob(props.groupName, props.jobName));
}

const { isLoading, mutate } = deleteJobMutation();
</script>

<style scoped>

</style>