import {ref} from 'vue';

export const isLoggedIn = ref(!!localStorage.getItem('authToken'));

export function login(token) {
    localStorage.setItem('authToken', token);
    isLoggedIn.value = true;
}

export function logout() {
    localStorage.removeItem('authToken');
    isLoggedIn.value = false;
}