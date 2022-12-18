<template>
    <div>
        Page Not Found.
        Will Redirect in {{ timeRemaining }} seconds...
    </div>
</template>

<script setup lang="ts">
import { onMounted, ref, watch } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const timeRemaining = ref(5);

watch(timeRemaining, () => {
    if (timeRemaining.value == 0) router.push("/");
});

onMounted(() => {
    window.setInterval(() => {
        if (timeRemaining.value > 0)
            timeRemaining.value--;
    }, 1000);
})
</script>

<style scoped>

</style>