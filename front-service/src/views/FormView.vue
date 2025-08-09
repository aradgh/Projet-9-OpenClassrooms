<template>
  <div>
    <h1>{{ id ? 'Modifier' : 'Ajouter' }} un patient</h1>
    <form @submit.prevent="savePatient">
      <input v-model="patient.firstName" placeholder="Prénom" required/>
      <input v-model="patient.lastName" placeholder="Nom" required/>
      <input v-model="patient.dateOfBirth" required type="date"/>
      <select v-model="patient.gender" required>
        <option value="">-- Genre --</option>
        <option value="M">Homme</option>
        <option value="F">Femme</option>
      </select>
      <input v-model="patient.mailingAddress" placeholder="Adresse"/>
      <input v-model="patient.phoneNumber" placeholder="Téléphone"/>
      <button type="submit">💾 Sauvegarder</button>
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
    savePatient() {
      if (this.isEdit) {
        api.updatePatient(this.$route.params.id, this.patient).then(() => {
          this.$router.push('/patients');
        });
      } else {
        api.createPatient(this.patient).then(() => {
          this.$router.push('/patients');
        });
      }
    }
  }
}
</script>
