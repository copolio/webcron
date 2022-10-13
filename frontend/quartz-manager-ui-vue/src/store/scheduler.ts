import { defineStore } from "pinia";

export const useSchedulerStore = defineStore("scheduler", {
  state: () => {
    return {
      jobGroups: [],
      jobs: [],
    };
  },
});
