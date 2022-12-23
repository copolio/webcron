<template>
  <slot :isLoading="isLoading" :mutateAsync="mutateAsync" />
</template>

<script setup lang="ts">
import { useDeleteHttpJobMutation } from "../composables/http-job";

interface PropsInterface {
  groupName: string;
  jobName: string;
}
const props = defineProps<PropsInterface>();

const { isLoading, mutateAsync } = useDeleteHttpJobMutation(
  props.groupName,
  props.jobName
);
</script>

<style scoped></style>
