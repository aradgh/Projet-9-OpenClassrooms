import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080', // Gateway
});

export default {
    getPatients() {
        return api.get('/patients');
    },
    getPatient(id) {
        return api.get(`/patients/${id}`);
    },
    createPatient(patient) {
        return api.post('/patients', patient);
    },
    updatePatient(id, patient) {
        return api.put(`/patients/${id}`, patient);
    },
    deletePatient(id) {
        return api.delete(`/patients/${id}`);
    }
};
