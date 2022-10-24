<template>
  <div>
    <Button @click="() => showJobForm = true">Add</Button>
    <HttpJobForm v-slot="{ formState, isLoading, isError, error, isSuccess, mutate }">
      <Modal v-model:visible="showJobForm" title="Add Job" @ok="() => closeJobForm(mutate)">
        <Form v-bind="{
          labelCol: { span: 5 },
          wrapperCol: { span: 19 },
        }" :model="formState">
          <FormItem label="URL">
            <Row>
              <Col :span="6">
              <Select v-model:value="formState.httpMethod">
                <SelectOption value="GET">GET</SelectOption>
                <SelectOption value="POST">POST</SelectOption>
                <SelectOption value="PUT">PUT</SelectOption>
                <SelectOption value="PATCH">PATCH</SelectOption>
                <SelectOption value="DELETE">DELETE</SelectOption>
              </Select>
              </Col>
              <Col :span="18">
              <Input v-model:value="formState.url" />
              </Col>
            </Row>
          </FormItem>
          <FormItem label="Request Body">
            <Textarea v-model:value="formState.requestBody" />
          </FormItem>
          <FormItem label="Username">
            <Input v-model:value="formState.username" />
          </FormItem>
          <FormItem label="Password">
            <InputPassword v-model:value="formState.password" />
          </FormItem>
          <FormItem label="Cron">
            <Input v-model:value="formState.cronExpression" />
          </FormItem>
          <FormItem label="Group">
            <Input v-model:value="formState.jobGroup" />
          </FormItem>
          <FormItem label="Name">
            <Input v-model:value="formState.jobName" />
          </FormItem>
          <FormItem label="Description">
            <Input v-model:value="formState.description" />
          </FormItem>
        </Form>
      </Modal>
    </HttpJobForm>
    <HttpJobGroupList v-slot="{ isLoading, data }">
      <Table :columns="groupColumns" :data-source="data?.data" :loading="isLoading"
        :pagination="{ hideOnSinglePage: true }" rowKey="name">
        <template #expandedRowRender="{ record }">
          <p style="margin: 0">
            {{ record.description }}
            <HttpJobList :group-name="record.name" v-slot="{ isLoading, data }">
              <Table :columns="jobColumns" :data-source="data?.data" :loading="isLoading" />
            </HttpJobList>
          </p>
        </template>
      </Table>
    </HttpJobGroupList>
  </div>
</template>

<script setup lang="ts">
import { Button, Col, Form, FormItem, Input, InputPassword, Modal, Row, Select, SelectOption, Table, Textarea } from "ant-design-vue";
import { ref } from "vue";
import HttpJobForm from "../components/HttpJobForm.vue";
import HttpJobGroupList from "../components/HttpJobGroupList.vue";
import HttpJobList from "../components/HttpJobList.vue";

const showJobForm = ref(false);
const closeJobForm = (callback: Function) => {
  callback();
  showJobForm.value = false;
}
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
