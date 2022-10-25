<template>
  <slot :isLoading="isLoading" :isError="isError" :data="data" :error="error" :refetch="refetch" />
</template>

<script setup lang="ts">
import { useHttpJobGroupQuery } from "../composables/HttpJob";

const { isLoading, isError, data, error, refetch } = useHttpJobGroupQuery();
</script>
