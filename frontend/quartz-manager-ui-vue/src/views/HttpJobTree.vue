<template>
  <div>
    <a-button @click="() => showJobForm = true">Add</a-button>
    <a-modal v-model:visible="showJobForm" title="Add Job" @ok="closeJobForm">
      <HttpJobForm v-slot="{formState, isLoading, isError, error, isSuccess, mutate}">
        <a-form v-bind="{
          labelCol: { span: 5 },
          wrapperCol: { span: 19 },
        }">
          <a-form-item label="URL">
            <a-row>
              <a-col :span="6">
                <a-select v-model:value="formState.httpMethod">
                  <a-select-option value="GET">GET</a-select-option>
                  <a-select-option value="POST">POST</a-select-option>
                  <a-select-option value="PUT">PUT</a-select-option>
                  <a-select-option value="PATCH">PATCH</a-select-option>
                  <a-select-option value="DELETE">DELETE</a-select-option>
                </a-select>
              </a-col>
              <a-col :span="18">
                <a-input v-model="formState.url" />
              </a-col>
            </a-row>
          </a-form-item>
          <a-form-item label="Username">
            <a-input v-model="formState.username" />
          </a-form-item>
          <a-form-item label="Password">
            <a-input v-model="formState.password" type="password" />
          </a-form-item>
          <a-form-item label="Cron">
            <a-input v-model="formState.cronExpression" />
          </a-form-item>
          <a-form-item label="Group">
            <a-input v-model="formState.jobGroup" />
          </a-form-item>
          <a-form-item label="Name">
            <a-input v-model="formState.jobName" />
          </a-form-item>
          <a-form-item label="Description">
            <a-input v-model="formState.description" />
          </a-form-item>
        </a-form>
      </HttpJobForm>
    </a-modal>
    <HttpJobGroupList v-slot="{ isLoading, data }">
      <a-table :columns="groupColumns" :data-source="data?.data" :loading="isLoading"
        :pagination="{hideOnSinglePage: true}" rowKey="name">
        <template #bodyCell="{ column }">
          <template v-if="column.key === 'action'">
            <a-button>Add</a-button>
          </template>
        </template>
        <template #expandedRowRender="{ record }">
          <p style="margin: 0">
            {{ record.description }}
            <HttpJobList :group-name="record.name" v-slot="{isLoading, data}">
              <a-table :columns="jobColumns" :data-source="data?.data" :loading="isLoading" />
            </HttpJobList>
          </p>
        </template>
      </a-table>
    </HttpJobGroupList>
  </div>
</template>

<script setup lang="ts">
import { ref } from "vue";
import HttpJobForm from "../components/HttpJobForm.vue";
import HttpJobGroupList from "../components/HttpJobGroupList.vue";
import HttpJobList from "../components/HttpJobList.vue";

const showJobForm = ref(false);
const closeJobForm = () => {
  showJobForm.value = false;
}
const groupColumns = [
  {
    title: 'Group Name',
    dataIndex: 'name',
    key: 'name',
    fixed: true
  },
  {
    title: 'Action',
    key: 'action'
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
