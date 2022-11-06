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
import { useHttpJobExecutionQuery } from "../composables/HttpJob";

const { searchCondition, isFetching, data, refetch } =
  useHttpJobExecutionQuery();

const antColumns: ColumnType[] = [
  {
    title: "Id",
    dataIndex: "instanceId",
    key: "instanceId",
    fixed: true,
  },
  {
    title: "Group Name",
    dataIndex: "jobGroup",
    key: "jobGroup",
  },
  {
    title: "Job Name",
    dataIndex: "jobName",
    key: "jobName",
  },
  {
    title: "Start time",
    dataIndex: "startTime",
    key: "startTime",
  },
  {
    title: "End time",
    dataIndex: "endTime",
    key: "endTime",
  },
  {
    title: "Status",
    dataIndex: "statusCode",
    key: "statusCode",
  },
  {
    title: "Response",
    dataIndex: "response",
    key: "response",
    ellipsis: true,
  },
];
</script>

<style scoped></style>
