<template>
  <div>
    <JobGroupList v-slot="{ isLoading, data }">
      <a-table :columns="groupColumns" :data-source="data?.data" :loading="isLoading"
        :pagination="{hideOnSinglePage: true}" rowKey="name">
        <template #bodyCell="{ column }">
          <template v-if="column.key === 'action'">
            <a>Delete</a>
          </template>
        </template>
        <template #expandedRowRender="{ record }">
          <p style="margin: 0">
            {{ record.description }}
            <JobList :group-name="record.name" v-slot="{isLoading, data}">
              <a-table :columns="jobColumns" :data-source="data?.data" :loading="isLoading" />
            </JobList>
          </p>
        </template>
      </a-table>
    </JobGroupList>
  </div>
</template>

<script setup lang="ts">
import JobGroupList from "../components/JobGroupList.vue";
import JobList from "../components/JobList.vue";
const groupColumns = [
  {
    title: 'Group Name',
    dataIndex: 'name',
    key: 'name',
    fixed: true
  }
];

const jobColumns = [
  {
    title: 'Name',
    dataIndex: 'name',
    key: 'name',
    fixed: true
  },
  {
    title: 'Cron Expression',
    dataIndex: 'cronExpression',
    key: 'cronExpression',
    width: '12%',
  },
  {
    title: 'Description',
    dataIndex: 'description',
    width: '30%',
  },
];
</script>

<style scoped>

</style>
