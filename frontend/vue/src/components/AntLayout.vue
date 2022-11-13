<template>
  <Layout>
    <LayoutHeader class="header">
      <div class="logo" @click="router.push('/')">Web Crontab</div>
    </LayoutHeader>
    <Layout>
      <LayoutSider width="200" style="background: #fff">
        <Menu
          :selectedKeys="selectedKey"
          v-model:openKeys="openKeys"
          mode="inline"
          :style="{ height: '100%', borderRight: 0 }"
        >
          <SubMenu key="Spring">
            <template #title>
              <span>
                <ScheduleOutlined />
                Spring
              </span>
            </template>
            <MenuItem
              key="QuartzHttpScheduler"
              @click="router.push('/spring/http-scheduler')"
            >
              HTTP Jobs
            </MenuItem>
            <MenuItem
              key="QuartzHttpJobLogBoard"
              @click="router.push('/spring/http-executions')"
            >
              HTTP Executions
            </MenuItem>
            <MenuItem
              key="QuartzBatchScheduler"
              @click="router.push('/spring/batch-scheduler')"
            >
              Batch Jobs
            </MenuItem>
          </SubMenu>
          <!-- <SubMenu key="cron">
            <template #title>
              <span>
                <LaptopOutlined />
                Cron
              </span>
            </template>
            <MenuItem key="option">option</MenuItem>
          </SubMenu> -->
        </Menu>
      </LayoutSider>
      <Layout style="padding: 0 24px 24px">
        <LayoutContent
          :style="{
            background: '#fff',
            padding: '24px',
            margin: 0,
            minHeight: '280px',
          }"
        >
          <router-view></router-view>
        </LayoutContent>
      </Layout>
    </Layout>
  </Layout>
</template>
<script setup lang="ts">
import { LaptopOutlined, ScheduleOutlined } from "@ant-design/icons-vue";
import { computed } from "@vue/reactivity";
import {
  Layout,
  LayoutContent,
  LayoutHeader,
  LayoutSider,
  Menu,
  MenuItem,
  SubMenu,
} from "ant-design-vue";
import { Ref, ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const openKeys: Ref<string[]> = ref(["Spring"]);

const selectedKey = computed(() => {
  return [router.currentRoute.value.name];
});
</script>
<style>
#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.logo {
  color: #fff;
  font-size: 20px;
}

.site-layout-background {
  background: #fff;
}
</style>
