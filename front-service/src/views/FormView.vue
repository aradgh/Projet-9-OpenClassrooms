<template>
  <div class="max-w-md mx-auto bg-white p-6 rounded-xl shadow-md">
    <h1 class="text-xl font-bold text-blue-600 mb-4">{{ id ? 'Modifier' : 'Ajouter' }} un patient</h1>
    <form class="space-y-3" @submit.prevent="savePatient">
      <input v-model="patient.firstName" class="w-full border rounded p-2 focus:outline-none focus:ring-2 focus:ring-blue-400" placeholder="Prénom"
             required/>
      <input v-model="patient.lastName" class="w-full border rounded p-2 focus:outline-none focus:ring-2 focus:ring-blue-400" placeholder="Nom"
             required/>
      <input v-model="patient.dateOfBirth" class="w-full border rounded p-2 focus:outline-none focus:ring-2 focus:ring-blue-400" required
             type="date"/>
      <select v-model="patient.gender" class="w-full border rounded p-2 focus:outline-none focus:ring-2 focus:ring-blue-400"
              required>
        <option value="">-- Genre --</option>
        <option value="M">Homme</option>
        <option value="F">Femme</option>
      </select>
      <input v-model="patient.mailingAddress" class="w-full border rounded p-2 focus:outline-none focus:ring-2 focus:ring-blue-400"
             placeholder="Adresse"/>
      <input v-model="patient.phoneNumber" class="w-full border rounded p-2 focus:outline-none focus:ring-2 focus:ring-blue-400"
             placeholder="Téléphone"/>
      <button class="w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600 transition" type="submit">💾
        Sauvegarder
      </button>
    </form>
  </div>
</template>


<script>
import api from "@/services/api.js";

export default {
  props: ['id'],
  data() {
    return {
      patient: {firstName: '', lastName: '', dateOfBirth: '', gender: '', mailingAddress: '', phoneNumber: ''},
      isEdit: false
    };
  },
  mounted() {
    if (this.$route.params.id) {
      this.isEdit = true;
      api.getPatient(this.$route.params.id).then(res => {
        this.patient = res.data;
      });
    }
  },
  methods: {
    async savePatient() {
      if (this.isEdit) {
        await api.updatePatient(this.$route.params.id, this.patient).then(() => {
          this.$router.push(`/patients/${this.patient.id}`);
        });
      } else {
        const res = await api.createPatient(this.patient);
        const newPatientId = res.data.id;
        this.$router.push(`/patients/${newPatientId}`);
      }
    }
  }
}
</script>
