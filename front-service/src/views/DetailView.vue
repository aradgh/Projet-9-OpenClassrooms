<template>
  <div v-if="patient">
    <h1>{{ patient.firstName }} {{ patient.lastName }}</h1>
    <!-- Affichage du niveau de risque -->
    <p v-if="riskAssessment">
      <strong>Risque de diabète :</strong> {{ riskAssessment }}
    </p>

    <p>Date de naissance : {{ patient.dateOfBirth }}</p>
    <p>Sexe : {{ patient.gender }}</p>
    <p>Adresse : {{ patient.mailingAddress }}</p>
    <p>Téléphone : {{ patient.phoneNumber }}</p>

    <router-link :to="'/patients/' + patient.id + '/edit'">✏️ Modifier</router-link>

    <PatientNotes
        v-if="patient.id"
        :patId="patient.id"
    />
  </div>
</template>

<script>
import api from '@/services/api';
import PatientNotes from "@/views/PatientNotes.vue";

export default {
  components: {PatientNotes},
  data() {
    return {
      patient: {},
      riskAssessment: null
    };
  },
  mounted() {
    const patientId = this.$route.params.id;

    // Charger le patient
    api.getPatient(patientId).then(res => {
      this.patient = res.data;
    });

    // Charger le rapport de diabète
    api.getDiabetesReport(patientId).then(res => {
      this.riskAssessment = res.data.assessment;
    });
  }
};
</script>
