<template>
  <slot
    :isLoading="isLoading"
    :isFetching="isFetching"
    :isError="isError"
    :data="data"
    :error="error"
    :refetch="refetch"
  />
</template>

<script setup lang="ts">
import { useHttpJobGroupQuery } from "../composables/http-job";

const { isLoading, isFetching, isError, data, error, refetch } =
  useHttpJobGroupQuery();
</script>
