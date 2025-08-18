<template>
  <div class="flex items-center justify-center h-[80vh] bg-gray-100">
    <div class="bg-white p-8 rounded-xl shadow-md w-96">
      <h1 class="text-2xl font-bold mb-6 text-center text-blue-600">Connexion</h1>
      <form class="" @submit.prevent="login">
        <div>
          <label class="block text-sm font-medium mb-1">Utilisateur</label>
          <input v-model="username"
                 class="w-full border rounded p-2 focus:outline-none focus:ring-2 focus:ring-blue-400" required
                 type="text"/>
        </div>
        <div>
          <label class="block text-sm font-medium mb-1">Mot de passe</label>
          <input v-model="password"
                 class="w-full border rounded p-2 focus:outline-none focus:ring-2 focus:ring-blue-400" required
                 type="password"/>
        </div>
        <button class="w-full bg-blue-500 text-white py-2 mt-5 rounded hover:bg-blue-600 transition" type="submit">
          Se connecter
        </button>
      </form>
      <p v-if="error" class="text-red-500 text-sm mt-2 text-center">{{ error }}</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router";
import {login as setLogin} from "@/services/auth.js"

export default {
  name: "Login",
  data() {
    return {
      username: "",
      password: "",
      error: ""
    };
  },
  methods: {
    async login() {
      try {
        const token = btoa(`${this.username}:${this.password}`);

        // Appel test au gateway
        await axios.get("http://localhost:8090/patients", {
          headers: {Authorization: `Basic ${token}`}
        });

        // Stocke le token
        setLogin(token);

        // Redirige
        await router.push("/patients");
      } catch (e) {
        this.error = "Identifiants incorrects";
        console.error(e);
      }
    }
  }
};
</script>
