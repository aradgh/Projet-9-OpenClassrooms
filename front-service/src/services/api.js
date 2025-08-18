import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8090', // Port externe du gateway
});

export default {
    getPatients() {
        return api.get('/patients');
    },
    getPatient(patientId) {
        return api.get(`/patients/${patientId}`);
    },
    createPatient(patient) {
        return api.post('/patients', patient);
    },
    updatePatient(patientId, patient) {
        return api.put(`/patients/${patientId}`, patient);
    },
    deletePatient(patientId) {
        return api.delete(`/patients/${patientId}`);
    },
    getPatientNotes(patientId) {
        return api.get(`/notes/patient/${patientId}`);
    },
    addPatientNote(note) {
        return api.post('/notes', note);
    },
    getDiabetesReport(patientId) {
        return api.get(`/reports/${patientId}`);
    }
};
