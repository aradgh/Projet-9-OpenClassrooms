<template>
  <div v-if="patient">
    <h1>{{ patient.firstName }} {{ patient.lastName }}</h1>
    <p>Date de naissance : {{ patient.dateOfBirth }}</p>
    <p>Sexe : {{ patient.gender }}</p>
    <p>Adresse : {{ patient.mailingAddress }}</p>
    <p>Téléphone : {{ patient.phoneNumber }}</p>

    <router-link :to="'/patients/' + patient.id + '/edit'">✏️ Modifier</router-link>

    <PatientNotes
        v-if="patient.id"
        :patId="patient.id"
        :patientName="patient.lastName"
    />
  </div>
</template>

<script>
import api from '@/services/api';
import PatientNotes from "@/views/PatientNotes.vue";

export default {
  components: {PatientNotes},
  data() {
    return {patient: {}};
  },
  mounted() {
    api.getPatient(this.$route.params.id).then(res => {
      this.patient = res.data;
    });
  }
};
</script>
