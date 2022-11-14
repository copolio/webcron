<template>
  <HttpJobLogList
    v-slot="{ searchCondition, isFetching, data, refetch, antColumns }"
  >
    <Space direction="vertical" style="width: 100%" size="small">
      <Form
        layout="vertical"
        @vnode-mounted="
          () => {
            searchCondition.groupName = props.groupName ?? '';
            searchCondition.jobName = props.jobName ?? '';
          }
        "
      >
        <Row :gutter="24">
          <Col>
            <FormItem label="Group">
              <Input v-model:value="searchCondition.groupName">
                <template #prefix><GroupOutlined /></template>
              </Input>
            </FormItem>
          </Col>
          <Col>
            <FormItem label="Job">
              <Input v-model:value="searchCondition.jobName">
                <template #prefix><SnippetsOutlined /></template>
              </Input>
            </FormItem>
          </Col>
          <Col>
            <FormItem label=" ">
              <Button type="primary" @click="refetch()">Search</Button>
            </FormItem>
          </Col>
        </Row>
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
    </Space>
  </HttpJobLogList>
</template>

<script setup lang="ts">
import { GroupOutlined, SnippetsOutlined } from "@ant-design/icons-vue";
import {
  Button,
  Col,
  Form,
  FormItem,
  Input,
  Row,
  Space,
  Spin,
  Table,
} from "ant-design-vue";
import HttpJobLogList from "../components/HttpJobLogList.vue";

const props = defineProps<{
  groupName?: string;
  jobName?: string;
}>();
</script>

<style scoped></style>
