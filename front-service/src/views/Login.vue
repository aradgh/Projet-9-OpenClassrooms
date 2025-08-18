<template>
  <div class="flex h-screen items-center justify-center bg-gray-100">
    <div class="bg-white p-6 rounded-xl shadow-md w-96">
      <h1 class="text-xl font-bold mb-4">Connexion</h1>
      <form @submit.prevent="login">
        <div class="mb-4">
          <label class="block text-sm font-medium mb-1">Utilisateur</label>
          <input v-model="username" class="w-full border rounded p-2" required type="text"/>
        </div>
        <div class="mb-4">
          <label class="block text-sm font-medium mb-1">Mot de passe</label>
          <input v-model="password" class="w-full border rounded p-2" required type="password"/>
        </div>
        <button class="w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600" type="submit">
          Se connecter
        </button>
      </form>
      <p v-if="error" class="text-red-500 text-sm mt-2">{{ error }}</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import router from "@/router";

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
        localStorage.setItem("authToken", token);

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
