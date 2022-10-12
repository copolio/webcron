<template>
    <slot :isLoading="isLoading" :jobGroups="jobGroups" :getJobGroupList="getJobGroupList" />
</template>

<script setup lang="ts">
import { onMounted, Ref, ref } from 'vue';
import { SchedulerControllerApi } from '../api';

const isLoading = ref(false);
const jobGroups: Ref<Array<string>> = ref([]);
const getJobGroupList = () => {
    isLoading.value = true;
    const api = new SchedulerControllerApi();
    api.getGroups()
        .then(res => jobGroups.value = res.data)
        .catch(err => alert(err))
        .finally(() => isLoading.value = false);
};

onMounted(() => {
    getJobGroupList();
});
</script>

<style scoped>

</style>