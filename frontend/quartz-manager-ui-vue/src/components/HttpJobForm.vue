<template>
    <slot :formState="formState" :isLoading="isLoading" :isError="isError" :error="error" :isSuccess="isSuccess"
        :mutate="mutate" />
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { PostHttpJobRequest } from '../api/quartmanager';
import { useAddHttpJobMutation } from '../composables/HttpJob';

const formState = ref<PostHttpJobRequest>({
    jobGroup: "",
    jobName: "",
    description: "",
    url: "",
    username: "",
    password: "",
    requestBody: "",
    httpMethod: "GET",
    cronExpression: "",
});

const { isLoading, isError, error, isSuccess, mutate } = useAddHttpJobMutation(formState.value);
</script>

<style scoped>

</style>