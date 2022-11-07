<template>
  <HttpJobLogList
    v-slot="{ searchCondition, isFetching, data, refetch, antColumns }"
  >
    <Form
      layout="inline"
      @vnode-mounted="
        () => {
          searchCondition.groupName = props.groupName ?? '';
          searchCondition.jobName = props.jobName ?? '';
        }
      "
    >
      <FormItem label="Group">
        <Input v-model:value="searchCondition.groupName"> </Input>
      </FormItem>
      <FormItem label="Job">
        <Input v-model:value="searchCondition.jobName"> </Input>
      </FormItem>
    </Form>

    <Table
      :loading="isFetching"
      :data-source="data?.data.content"
      :columns="antColumns"
      bordered
      :scroll="{ x: 2000, y: 900 }"
      v-model:current="searchCondition.page"
      v-model:pageSize="searchCondition.size"
      :pagination="{
        showSizeChanger: true,
        showQuickJumper: true,
        total: data?.data.totalElements,
        pageSize: searchCondition.size,
        onChange: (newPage, newPageSize) => {
          searchCondition.page = newPage - 1;
          searchCondition.size = newPageSize;
        },
      }"
    >
    </Table>
  </HttpJobLogList>
</template>

<script setup lang="ts">
import { Form, FormItem, Input, Spin, Table } from "ant-design-vue";
import HttpJobLogList from "../components/HttpJobLogList.vue";

const props = defineProps<{
  groupName?: string;
  jobName?: string;
}>();
</script>

<style scoped></style>
