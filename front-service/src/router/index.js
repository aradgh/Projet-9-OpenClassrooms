import {createRouter, createWebHistory} from 'vue-router'
import ListView from '../views/ListView.vue'
import DetailView from '../views/DetailView.vue'
import FormView from '../views/FormView.vue'
import Login from '../views/Login.vue';

const routes = [
    {path: '/', component: Login},
    {path: '/patients', component: ListView},
    {path: '/patients/:id', component: DetailView, props: true},
    {path: '/patients/new', component: FormView},
    {path: '/patients/:id/edit', component: FormView, props: true}
]

export default createRouter({
    history: createWebHistory(),
    routes
})
