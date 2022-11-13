<template>
  <slot
    :formState="formState"
    :isLoading="isLoading"
    :isError="isError"
    :error="error"
    :isSuccess="isSuccess"
    :mutateAsync="mutateAsync"
    :resetForm="resetForm"
  />
</template>

<script setup lang="ts">
import { ref } from "vue";
import { PostHttpJobRequest } from "../api";
import { useAddHttpJobMutation } from "../composables/HttpJob";

const formState = ref<PostHttpJobRequest>({
  httpJobRequest: {
    jobName: "",
    jobGroup: "",
    url: "",
    apiKey: "",
    apiToken: "",
    body: "",
    httpMethod: "GET",
  },
  description: "",
  cronExpression: "",
});

const resetForm = () => {
  formState.value = {
    httpJobRequest: {
      jobName: "",
      jobGroup: "",
      url: "",
      apiKey: "",
      apiToken: "",
      body: "",
      httpMethod: "GET",
    },
    description: "",
    cronExpression: "",
  };
};

const { isLoading, isError, error, isSuccess, mutateAsync } =
  useAddHttpJobMutation(formState.value);
</script>

<style scoped></style>
