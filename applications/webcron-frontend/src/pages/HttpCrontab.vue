<template>
  <HttpJobGroupList v-slot="{ isFetching, data, refetch }">
    <Space direction="vertical" style="width: 100%" size="small">
      <Form layout="vertical">
        <Row :gutter="24">
          <Col>
            <FormItem>
              <Button
                type="primary"
                @click="refetch()"
                shape="circle"
                :loading="isFetching"
              >
                <template #icon>
                  <ReloadOutlined />
                </template>
              </Button>
            </FormItem>
          </Col>
          <Col>
            <FormItem>
              <Button @click="() => (showJobForm = true)">Create New</Button>
            </FormItem>
          </Col>
        </Row>
      </Form>
      <HttpJobForm
        v-slot="{ formState, mutateAsync, resetForm, error, isError }"
      >
        <Modal
          v-model:visible="showJobForm"
          title="Add Job"
          @ok="() => closeJobForm(mutateAsync, resetForm, refetch)"
        >
          <Alert
            v-if="isError"
            :message="error?.response?.data.error"
            :description="error?.response?.data.message"
            type="error"
            closable
          />
          <Form
            v-bind="{
              labelCol: { span: 5 },
              wrapperCol: { span: 19 },
            }"
            :model="formState"
          >
            <FormItem label="URL">
              <Row>
                <Col :span="6">
                  <Select v-model:value="formState.httpJob.httpMethod">
                    <SelectOption value="GET">GET</SelectOption>
                    <SelectOption value="POST">POST</SelectOption>
                    <SelectOption value="PUT">PUT</SelectOption>
                    <SelectOption value="PATCH">PATCH</SelectOption>
                    <SelectOption value="DELETE">DELETE</SelectOption>
                  </Select>
                </Col>
                <Col :span="18">
                  <Input v-model:value="formState.httpJob.url" />
                </Col>
              </Row>
            </FormItem>
            <FormItem label="Request Body">
              <Textarea v-model:value="formState.httpJob.body" />
            </FormItem>
            <FormItem label="Username">
              <Input v-model:value="formState.httpJob.apiKey" />
            </FormItem>
            <FormItem label="Password">
              <InputPassword v-model:value="formState.httpJob.apiToken" />
            </FormItem>
            <FormItem label="Cron">
              <Input v-model:value="formState.cronExpression" />
            </FormItem>
            <FormItem label="Group">
              <Input v-model:value="formState.httpJob.jobGroup" />
            </FormItem>
            <FormItem label="Name">
              <Input v-model:value="formState.httpJob.jobName" />
            </FormItem>
            <FormItem label="Description">
              <Input v-model:value="formState.description" />
            </FormItem>
          </Form>
        </Modal>
      </HttpJobForm>
      <Table
        :columns="groupColumns"
        :data-source="data?.data"
        :loading="isFetching"
        :pagination="{ hideOnSinglePage: true }"
        rowKey="name"
      >
        <template #expandedRowRender="{ record }">
          <p style="margin: 0">
            {{ record.description }}
            <HttpJobList
              :group-name="record.name"
              v-slot="{ isFetching, data }"
            >
              <Table
                :columns="jobColumns"
                :data-source="data?.data"
                :loading="isFetching"
              >
                <template #bodyCell="{ column, record }">
                  <template v-if="column.key === 'action'">
                    <HttpJobDelete
                      :group-name="record.groupName"
                      :job-name="record.name"
                      v-slot="{ mutateAsync }"
                    >
                      <Button
                        @click="showDeleteConfirm(mutateAsync, refetch)"
                        danger
                      >
                        <template #icon>
                          <DeleteOutlined />
                        </template>
                      </Button>
                    </HttpJobDelete>
                    <Button
                      @click="
                        () =>
                          router.push(
                            `/logs?groupName=${record.groupName}&jobName=${record.name}`
                          )
                      "
                    >
                      Logs
                    </Button>
                  </template>
                </template>
              </Table>
            </HttpJobList>
          </p>
        </template>
      </Table>
    </Space>
  </HttpJobGroupList>
</template>

<script setup lang="ts">
import {
  DeleteOutlined,
  ExclamationCircleOutlined,
  ReloadOutlined,
} from "@ant-design/icons-vue";
import {
  Alert,
  Button,
  Col,
  Form,
  FormItem,
  Input,
  InputPassword,
  Modal,
  Row,
  Select,
  SelectOption,
  Space,
  Table,
  Textarea,
} from "ant-design-vue";
import { createVNode, ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();

const showJobForm = ref(false);
const closeJobForm = async (
  mutateAsync: Function,
  refetch: Function,
  resetForm: Function
) => {
  await mutateAsync();
  await resetForm();
  await refetch();
  showJobForm.value = false;
};
const groupColumns = [
  {
    title: "Group Name",
    dataIndex: "name",
    key: "name",
    fixed: true,
  },
];

const showDeleteConfirm = (mutateAsync: Function, refetch: Function) => {
  Modal.confirm({
    title: "Do you want to delete this job?",
    icon: createVNode(ExclamationCircleOutlined),
    content: createVNode(
      "div",
      { style: "color:red;" },
      "Deleted job will be permanently removed from the database."
    ),
    onOk: async () => {
      await mutateAsync();
      await refetch();
    },
    onCancel() {},
  });
};

const jobColumns = [
  {
    title: "Name",
    dataIndex: "name",
    key: "name",
    fixed: true,
  },
  {
    title: "Cron Expression",
    dataIndex: "cronExpression",
    key: "cronExpression",
    width: "12%",
  },
  {
    title: "Description",
    dataIndex: "description",
    key: "description",
    width: "30%",
  },
  {
    title: "Action",
    key: "action",
    width: "20%",
  },
];
</script>

<style scoped></style>
