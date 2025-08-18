<template>
  <div class="max-w-3xl mx-auto bg-white p-6 rounded-xl shadow-md">
    <div class="flex justify-between items-center mb-4">
      <h1 class="text-xl font-bold text-blue-600">Liste des patients</h1>
      <router-link class="bg-blue-500 text-white px-3 py-1 rounded hover:bg-blue-600 transition" to="/patients/new">
        + Ajouter
      </router-link>
    </div>

    <ul class="divide-y divide-gray-200">
      <li v-for="p in patients" :key="p.id" class="py-2">
        <router-link :to="'/patients/' + p.id" class="text-gray-700 hover:text-blue-600">
          {{ p.firstName }} {{ p.lastName }}
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
import api from '@/services/api';

export default {
  data() {
    return {
      patients: []
    };
  },
  mounted() {
    api.getPatients().then(res => {
      this.patients = res.data;
    });
  }
};
</script>
