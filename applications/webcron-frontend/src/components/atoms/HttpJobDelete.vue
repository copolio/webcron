<template>
  <slot :isLoading="isLoading" :mutateAsync="mutateAsync" />
</template>

<script setup lang="ts">
import { useDeleteHttpJobMutation } from "../../composables/HttpJob";

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
