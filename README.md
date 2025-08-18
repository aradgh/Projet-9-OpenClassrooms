# Projet-9-OpenClassrooms
# Mediscreen - Application de Gestion des Patients Diabétiques

## 📋 Description du Projet

Mediscreen est une application de gestion des patients permettant le suivi et l'évaluation des risques de diabète. L'application utilise une architecture microservices avec Spring Boot et une interface utilisateur moderne développée avec Vue.js.

## 🏗️ Architecture

L'application est composée de 5 services :

- **Patient Service** (Port 8081) : Gestion des informations patients (MySQL/H2)
- **Note Service** (Port 8082) : Gestion des notes médicales (MongoDB)
- **Diabetes Report Service** (Port 8083) : Évaluation des risques diabétiques
- **Gateway Service** (Port 8080/8090) : Point d'entrée unique pour tous les microservices
- **Front Service** (Port 5173) : Interface utilisateur Vue.js avec Nginx

## 🛠️ Technologies Utilisées

### Backend
- **Java 17** & **Spring Boot 3.x**
- **Spring Cloud Gateway** - API Gateway
- **Spring Security** - Authentification HTTP Basic
- **Spring Data JPA** - Accès aux données (Patient Service)
- **Spring Data MongoDB** - Accès aux données (Note Service)
- **H2 Database** - Base de données en mémoire pour les patients
- **MongoDB** - Base de données NoSQL pour les notes

### Frontend
- **Vue.js 3** - Framework JavaScript
- **Vue Router** - Routage côté client
- **Axios** - Client HTTP
- **Tailwind CSS** - Framework CSS

### Infrastructure
- **Docker** & **Docker Compose** - Containerisation
- **Nginx** - Serveur web pour le frontend

## 🚀 Installation et Démarrage

### Prérequis

- **Docker** et **Docker Compose** installés
- **Java 17** (pour le développement local)
- **Node.js 20+** (pour le développement local)
- **Maven 3.8+** (pour le développement local)

### Démarrage avec Docker (Recommandé)

1. **Cloner le repository**
   ```bash
   git clone <url-du-repository>
   cd projet9
   ```

2. **Démarrer l'application**
   ```bash
   docker-compose up --build
   ```

3. **Accéder à l'application**
   - Interface web : http://localhost:5173
   - Gateway API : http://localhost:8090

### Démarrage en mode développement local

1. **Démarrer MongoDB**
   ```bash
   docker run -d -p 27017:27017 --name mongo mongo:6.0
   ```

2. **Démarrer les services backend** (dans des terminaux séparés)
   ```bash
   # Patient Service
   cd patient-service
   mvn spring-boot:run

   # Note Service  
   cd note-service
   mvn spring-boot:run

   # Diabetes Report Service
   cd diabetes-report-service
   mvn spring-boot:run

   # Gateway Service
   cd gateway-service
   mvn spring-boot:run
   ```

3. **Démarrer le frontend**
   ```bash
   cd front-service
   npm install
   npm run dev
   ```

## 👤 Authentification

L'application utilise l'authentification HTTP Basic avec les comptes suivants :

| Utilisateur | Mot de passe | Rôle  |
|-------------|--------------|-------|
| `user`      | `userpass`   | USER  |
| `admin`     | `adminpass`  | ADMIN |

## 🗄️ Données de Test

### Patients (H2 - chargés automatiquement)
L'application démarre avec des patients de test pré-chargés.

### Notes MongoDB
Des notes de test sont automatiquement insérées au démarrage :
- Notes pour les patients avec patId 1, 2, 3, et 4
- Contiennent des termes déclencheurs pour l'évaluation du risque diabétique

## 🔧 Configuration

### Ports utilisés

| Service | Port Local | Port Docker |
|---------|------------|-------------|
| Patient Service | 8081 | 8081 |
| Note Service | 8082 | 8082 |
| Diabetes Report Service | 8083 | 8083 |
| Gateway Service | 8080 | 8090 (externe) |
| Front Service | 3000 | 5173 (externe) |
| MongoDB | 27017 | 27017 |

### Variables d'environnement importantes

- `MONGO_INITDB_DATABASE=mediscreen` - Nom de la base MongoDB
- Les services communiquent via les noms de containers Docker

## 🧪 Tests et Validation

### Endpoints API principaux

```bash
# Patients
GET    /patients           - Liste des patients
GET    /patients/{id}      - Détail d'un patient
POST   /patients           - Créer un patient
PUT    /patients/{id}      - Modifier un patient
DELETE /patients/{id}      - Supprimer un patient

# Notes
GET    /notes/patient/{patientId}  - Notes d'un patient
POST   /notes                      - Ajouter une note

# Rapports diabète
GET    /reports/{patientId}        - Évaluation risque diabétique
```

### Test avec curl
```bash
# Test avec authentification
curl -u user:userpass http://localhost:8090/patients
curl -u user:userpass http://localhost:8090/reports/1
```

## 🐛 Dépannage

### Erreur de connexion refusée
- Vérifier que Docker Desktop est démarré
- Vérifier que tous les containers sont running : `docker-compose ps`
- Vérifier les logs : `docker-compose logs <service-name>`

### Erreurs CORS
- S'assurer que la configuration CORS du gateway inclut l'origine correcte
- Pour local : `http://localhost:5173`
- Pour Docker : `http://localhost:5173`

### Base MongoDB vide
```bash
# Supprimer le volume et redémarrer
docker-compose down --volumes
docker-compose up --build
```

### Page 404 lors du rechargement
- Vérifier que le fichier `nginx.conf` contient la directive `try_files`
- Rebuilder le front-service : `docker-compose build front-service`

## 📁 Structure du Projet

```
projet9/
├── patient-service/          # Service de gestion des patients
├── note-service/            # Service de gestion des notes
├── diabetes-report-service/ # Service d'évaluation des risques
├── gateway-service/         # API Gateway
├── front-service/           # Interface utilisateur Vue.js
├── init-mongo.js           # Script d'initialisation MongoDB
├── docker-compose.yml      # Configuration Docker
└── README.md
```

## 🚦 Statut des Services

Pour vérifier l'état des services :

```bash
# Status des containers
docker-compose ps

# Logs d'un service spécifique
docker-compose logs gateway-service

# Logs en temps réel
docker-compose logs -f
```

## 📝 Fonctionnalités

- ✅ Authentification sécurisée
- ✅ Gestion CRUD des patients
- ✅ Gestion des notes médicales
- ✅ Évaluation automatique du risque diabétique
- ✅ Interface responsive
- ✅ Architecture microservices
- ✅ Containerisation Docker
- ✅ Données de test pré-chargées

## 📄 Licence

Ce projet est développé dans le cadre du parcours OpenClassrooms "Développeur d'application Java".
