<template>
  <slot
    :formState="formState"
    :isLoading="isLoading"
    :isError="isError"
    :error="error"
    :isSuccess="isSuccess"
    :mutate="mutateAsync"
  />
</template>

<script setup lang="ts">
import { ref } from "vue";
import { PostHttpJobRequest } from "../api";
import { useAddHttpJobMutation } from "../composables/HttpJob";

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

const { isLoading, isError, error, isSuccess, mutateAsync } =
  useAddHttpJobMutation(formState.value);
</script>

<style scoped></style>
