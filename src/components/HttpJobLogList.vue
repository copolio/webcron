<template>
  <slot
    :searchCondition="searchCondition"
    :isFetching="isFetching"
    :data="data"
    :refetch="refetch"
    :antColumns="antColumns"
  />
</template>

<script setup lang="ts">
import { ColumnType } from "ant-design-vue/lib/table";
import { useHttpJobExecutionQuery } from "../composables/http-job";

const { searchCondition, isFetching, data, refetch } =
  useHttpJobExecutionQuery();

const antColumns: ColumnType[] = [
  {
    title: "Group",
    dataIndex: "jobGroup",
    key: "jobGroup",
    width: 100,
    fixed: true,
  },
  {
    title: "Job",
    dataIndex: "jobName",
    key: "jobName",
    width: 100,
    fixed: true,
  },
  {
    title: "Start time",
    dataIndex: "startTime",
    key: "startTime",
    width: 200,
  },
  {
    title: "End time",
    dataIndex: "endTime",
    key: "endTime",
    width: 200,
  },
  {
    title: "Status",
    dataIndex: "statusCode",
    key: "statusCode",
    width: 220,
  },
  {
    title: "Response",
    dataIndex: "response",
    key: "response",
    width: 500,
    ellipsis: true,
  },
];
</script>

<style scoped></style>
