<template>
    <slot :formState="formState" :isLoading="isLoading" :isError="isError" :error="error" :isSuccess="isSuccess"
        :mutate="mutate" />
</template>

<script setup lang="ts">
import { Ref, ref } from 'vue';
import { useMutation } from 'vue-query';
import { PostHttpJobRequest } from '../api/quartmanager';
import { useQuartzApi } from '../util/AxiosUtil';

const quartzApi = useQuartzApi();

const formState: Ref<PostHttpJobRequest> = ref({
    jobGroup: "",
    jobName: "",
    description: "",
    url: "",
    username: "",
    password: "",
    requestBody: "",
    httpMethod: "GET",
    cronExpression: "",
} as PostHttpJobRequest);

function addJobMutation() {
    return useMutation(() => quartzApi.HttpSchedulerApi.addJob(formState.value));
}

const { isLoading, isError, error, isSuccess, mutate } = addJobMutation();

</script>

<style scoped>

</style>