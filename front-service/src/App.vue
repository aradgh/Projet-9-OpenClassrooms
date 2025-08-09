<template>
  <div id="app">
    <header>
      <h1>🏥 Gestion des Patients</h1>
      <nav>
        <router-link to="/patients">Liste des patients</router-link>
        <router-link to="/patients/new">Ajouter un patient</router-link>
      </nav>
    </header>

    <main>
      <router-view/>
    </main>
  </div>
</template>

<script>
export default {
  name: 'App'
};
</script>

<style>
#app {
  font-family: Arial, sans-serif;
  margin: 20px;
}

header {
  background: #f4f4f4;
  padding: 10px;
  border-radius: 8px;
}

nav {
  display: flex;
  gap: 15px;
}

nav a {
  text-decoration: none;
  color: #333;
}

nav a.router-link-exact-active {
  font-weight: bold;
  color: #007BFF;
}
</style>
