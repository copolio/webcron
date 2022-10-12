<template>
    <div>
        <JobGroup v-slot="{jobGroups, isLoading, getJobGroupList}">
            <a-table :dataSource="jobGroups" :loading="isLoading" @change="getJobGroupList" />
        </JobGroup>
    </div>
</template>

<script setup lang="ts">
import JobGroup from '../components/JobGroup.vue';

</script>

<style scoped>

</style>