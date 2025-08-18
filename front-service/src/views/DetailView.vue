<template>
  <div v-if="patient" class="max-w-3xl mx-auto bg-white p-6 rounded-xl shadow-md space-y-4">
    <h1 class="text-2xl font-bold text-blue-600">{{ patient.firstName }} {{ patient.lastName }}</h1>

    <p v-if="riskAssessment" class="text-red-600 font-semibold">
      <strong>Risque de diabète :</strong> {{ riskAssessment }}
    </p>

    <p><strong>Date de naissance :</strong> {{ patient.dateOfBirth }}</p>
    <p><strong>Sexe :</strong> {{ patient.gender }}</p>
    <p><strong>Adresse :</strong> {{ patient.mailingAddress }}</p>
    <p><strong>Téléphone :</strong> {{ patient.phoneNumber }}</p>

    <router-link :to="'/patients/' + patient.id + '/edit'"
                 class="inline-block bg-yellow-400 text-white px-3 py-1 rounded hover:bg-yellow-500 transition">✏️
      Modifier
    </router-link>

    <PatientNotes
        v-if="patient.id"
        :patId="patient.id"
        @note-added="refreshRiskAssessment"
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
      // Charger le rapport de diabète
      this.refreshRiskAssessment();
    });

  },
  methods: {
    refreshRiskAssessment() {
      if (this.patient.id) {
        api.getDiabetesReport(this.patient.id).then(res => {
          this.riskAssessment = res.data.assessment;
        });
      }
    }
  }
};
</script>
