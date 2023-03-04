import { defineStore } from "pinia";

export const useHttpJobStore = defineStore("httpJob", {
  state: () => {
    return {
      jobGroups: [],
      jobs: [],
    };
  },
});
