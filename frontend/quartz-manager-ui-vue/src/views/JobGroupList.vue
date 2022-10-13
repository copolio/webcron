<template>
  <div>
    <JobGroup v-slot="{ isLoading, isError, data, error }">
      <a-list
        bordered
        item-layout="horizontal"
        :dataSource="data?.data"
        :loading="isLoading"
      >
        <template #renderItem="{ item }">
          <a-list-item>{{ item }}</a-list-item>
        </template>
      </a-list>
    </JobGroup>
  </div>
</template>

<script setup lang="ts">
import JobGroup from "../components/JobGroup.vue";
</script>

<style scoped></style>
