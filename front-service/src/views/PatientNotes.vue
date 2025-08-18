<template>
  <div class="mt-6 bg-gray-50 p-4 rounded-xl shadow-inner">
    <h2 class="text-lg font-bold text-blue-600 mb-3">Notes médicales</h2>

    <div v-if="notes.length > 0" class="space-y-2">
      <ul>
        <li v-for="note in notes" :key="note.id" class="p-2 border rounded bg-white overflow-auto mb-3">
          <pre>{{ note.note }}</pre>
        </li>
      </ul>
    </div>
    <div v-else>
      <p class="text-gray-500">Aucune note pour ce patient.</p>
    </div>

    <h3 class="mt-4 font-semibold">Ajouter une note</h3>
    <textarea v-model="newNote" class="w-full border rounded p-2 focus:outline-none focus:ring-2 focus:ring-blue-400"
              placeholder="Saisissez votre note..."
              rows="4"></textarea>
    <button :disabled="newNote.trim() === ''"
            class="mt-2 bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600 disabled:opacity-50 transition"
            @click="addNote">
      Enregistrer
    </button>
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
          note: this.newNote
        });
        this.newNote = "";
        await this.fetchNotes(); // recharge après ajout
        this.$emit("note-added"); // avertit le parent
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
