<template>
  <slot
    :isFetching="isFetching"
    :isError="isError"
    :data="data"
    :error="error"
    :refetch="refetch"
  />
</template>

<script setup lang="ts">
import { useHttpJobQuery } from "../../composables/HttpJob";

const props = defineProps<{ groupName: string }>();
const { isFetching, isError, data, error, refetch } = useHttpJobQuery(
  props.groupName
);
</script>
