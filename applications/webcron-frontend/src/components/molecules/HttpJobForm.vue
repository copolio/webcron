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
import { CreateHttpJob } from "../../api";
import { useAddHttpJobMutation } from "../../composables/HttpJob";

const formState = ref<CreateHttpJob>({
  httpJob: {
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
    httpJob: {
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
