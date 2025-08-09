<template>
  <div>
    <h1>Liste des patients</h1>
    <router-link to="/patients/new">➕ Ajouter un patient</router-link>
    <ul>
      <li v-for="p in patients" :key="p.id">
        <router-link :to="'/patients/' + p.id">
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
