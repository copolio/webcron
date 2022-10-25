<template>
    <slot :isLoading="isLoading" :mutate="mutate" />
</template>

<script setup lang="ts">
import { useDeleteHttpJobMutation } from '../composables/HttpJob';

interface PropsInterface {
    groupName: string;
    jobName: string;
}
const props = defineProps<PropsInterface>();


const { isLoading, mutate } = useDeleteHttpJobMutation(props.groupName, props.jobName);
</script>

<style scoped>

</style>