<template>
  <div class="patient-notes">
    <h2>Notes médicales</h2>

    <!-- Liste des notes -->
    <div v-if="notes.length > 0">
      <ul>
        <li v-for="note in notes" :key="note.id" class="note-item">
          <pre>{{ note.note }}</pre>
        </li>
      </ul>
    </div>
    <div v-else>
      <p>Aucune note pour ce patient.</p>
    </div>

    <!-- Formulaire ajout -->
    <h3>Ajouter une note</h3>
    <textarea v-model="newNote" placeholder="Saisissez votre note..." rows="4"></textarea>
    <br/>
    <button :disabled="newNote.trim() === ''" @click="addNote">Enregistrer</button>
  </div>
</template>

<script>
import api from "@/services/api.js";

export default {
  name: "PatientNotes",
  props: {
    patId: {
      type: Number,
      required: true
    },
    patientName: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      notes: [],
      newNote: ""
    };
  },
  methods: {
    async fetchNotes() {
      try {
        api.getPatientNotes(this.$route.params.id).then(res => {
          this.notes = res.data;
        })
      } catch (err) {
        console.error("Erreur récupération notes :", err);
      }
    },
    async addNote() {
      try {
        await api.addPatientNote({
          patId: this.patId,
          patient: this.patientName,
          note: this.newNote
        });
        this.newNote = "";
        await this.fetchNotes(); // recharge après ajout
      } catch (err) {
        console.error("Erreur ajout note :", err);
      }
    }
  },
  mounted() {
    this.fetchNotes();
  }
};
</script>

<style scoped>
.patient-notes {
  margin-top: 20px;
}

.note-item {
  margin-bottom: 10px;
  padding: 8px;
  border: 1px solid #ccc;
  background: #fafafa;
  overflow: auto;
}

textarea {
  width: 100%;
  padding: 8px;
}

button {
  margin-top: 5px;
  padding: 6px 12px;
}
</style>
