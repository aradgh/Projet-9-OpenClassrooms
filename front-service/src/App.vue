<template>
  <div id="app" class="min-h-screen bg-gray-100">
    <header class="bg-white shadow-md p-4 flex justify-evenly items-center">
      <h1 class="text-2xl font-bold text-blue-600">🏥 Gestion des Patients</h1>
      <nav v-if="isLoggedIn">
        <router-link class="text-gray-700 hover:text-blue-600 mr-8" to="/patients">Liste des patients</router-link>
        <router-link class="text-gray-700 hover:text-blue-600 mr-8" to="/patients/new">Ajouter un patient
        </router-link>
        <button class="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600 transition"
                @click="doLogout(); router.push('/')">
          Déconnexion
        </button>
      </nav>
    </header>

    <main class="p-6">
      <router-view/>
    </main>
  </div>
</template>

<script>
import router from "@/router/index.js";
import {isLoggedIn, logout as doLogout} from "@/services/auth.js";

export default {
  name: 'App',
  setup() {
    return {isLoggedIn, doLogout, router};
  }
};
</script>
