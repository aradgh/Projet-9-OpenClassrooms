# Projet-9-OpenClassrooms
# Mediscreen - Application de Gestion des Patients Diabétiques

## 📋 Description du Projet

Mediscreen est une application de gestion des patients permettant le suivi et l'évaluation des risques de diabète. L'application utilise une architecture microservices avec Spring Boot et une interface utilisateur moderne développée avec Vue.js.

## 🏗️ Architecture

L'application est composée de 5 services :

- **Patient Service** (Port 8081) : Gestion des informations patients (SQL - H2)
- **Note Service** (Port 8082) : Gestion des notes médicales (NoSQL - MongoDB)
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
   git clone https://github.com/aradgh/Projet-9-OpenClassrooms.git
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

## 🌱 Green Code - Recommandations d'Amélioration

Dans le cadre de la politique environnementale du client, voici les recommandations pour rendre le projet Mediscreen plus écoresponsable :

### 🎯 Objectifs du Green Code
Le Green Code vise à réduire l'impact environnemental des logiciels en optimisant la consommation d'énergie, l'utilisation des ressources et en prolongeant la durée de vie des équipements.

### 📊 Audit Énergétique Actuel
**Points d'amélioration identifiés :**
- Architecture microservices multipliant les instances JVM
- Utilisation de H2 en mémoire (consommation RAM)
- Pas de mise en cache des données
- Images Docker non optimisées
- Absence de monitoring des ressources

### 🛠️ Recommandations Techniques

#### **1. Optimisation des Microservices Java/Spring Boot**
```bash
# Configuration JVM optimisée pour la production
JAVA_OPTS="-Xms256m -Xmx512m -XX:+UseG1GC -XX:+UseStringDeduplication"
```
Cette config dit à la JVM :
- commence avec 256 Mo de heap,
- n’utilise jamais plus de 512 Mo,
- utilise le Garbage Collector G1 pour de bonnes perfs,
- économise de la RAM en fusionnant les chaînes de caractères identiques.

**Actions à mener :**
- Configurer des limites mémoire appropriées pour chaque service
- Utiliser GraalVM Native Image pour réduire l'empreinte mémoire de 70%
- Implémenter Spring Boot 3 avec AOT (Ahead-of-Time compilation)
- Activer la compression GZIP sur les API responses

#### **2. Optimisation Base de Données**
```sql
-- Exemple d'optimisation MongoDB
db.notes.createIndex({ "patId": 1, "createdDate": -1 })
```

**Actions à mener :**
- Remplacer H2 par une base légère persistante (SQLite)
- Optimiser les requêtes MongoDB avec des index appropriés
- Implémenter la pagination pour limiter les transferts de données
- Mettre en place un cache Redis pour les données fréquemment consultées

#### **3. Optimisation Frontend Vue.js**
**Actions à mener :**
- Implémenter le lazy loading des composants
- Optimiser les bundles avec Tree Shaking
- Utiliser des images optimisées (WebP, compression)
- Mettre en place un Service Worker pour le cache

#### **4. Optimisation Docker**
```dockerfile
# Exemple d'image multi-stage optimisée
FROM amazoncorretto:17-alpine AS builder
# ... build steps ...

FROM amazoncorretto:17-alpine-jre
COPY --from=builder /app/target/app.jar app.jar
CMD ["java", "-XX:+UseContainerSupport", "-jar", "app.jar"]
```

**Actions à mener :**
- Utiliser des images Alpine Linux (90% plus légères)
- Implémenter des images multi-stage pour réduire la taille
- Configurer des health checks pour éviter les redémarrages inutiles
- Utiliser des profils Spring pour optimiser selon l'environnement

#### **5. Architecture et Code**
**Actions à mener :**
- Implémenter des circuit breakers pour éviter la sur-consommation
- Optimiser les algorithmes d'évaluation du risque diabétique
- Utiliser des structures de données efficaces (ArrayList vs LinkedList)
- Implémenter des pools de connexions optimisés

### 📈 Monitoring et Métriques
**Outils recommandés :**
- **Micrometer + Prometheus** : Monitoring des métriques JVM
- **Grafana** : Visualisation de la consommation énergétique
- **Spring Boot Actuator** : Health checks et métriques
- **Docker stats** : Monitoring des ressources containers

### 🎯 Objectifs Quantifiés
| Métrique | Actuel | Objectif | Impact |
|----------|--------|----------|--------|
| Mémoire JVM | ~512MB/service | ~256MB/service | -50% |
| Taille images Docker | ~200MB | ~50MB | -75% |
| Temps démarrage | ~30s | ~10s | -67% |
| Consommation CPU | N/A | <50% utilisation | Mesurable |

### 🔄 Plan d'Action Progressif

#### **Phase 1 - Quick Wins (1-2 semaines)**
- Optimisation des Dockerfiles (images Alpine)
- Configuration JVM avec limites mémoire
- Mise en place du monitoring Actuator

#### **Phase 2 - Optimisations Moyennes (3-4 semaines)**
- Migration vers GraalVM Native Image
- Implémentation du cache Redis
- Optimisation des requêtes base de données

#### **Phase 3 - Transformations Majeures (2-3 mois)**
- Migration vers une architecture event-driven
- Implémentation de microservices serverless
- Optimisation complète du frontend

### 📊 Outils d'Évaluation
```bash
# Mesure de l'empreinte carbone
docker run -it --rm codecarbon/codecarbon

# Profiling JVM
java -XX:+FlightRecorder -XX:StartFlightRecording=duration=60s,filename=profile.jfr

# Analyse des bundles frontend
npm install --save-dev webpack-bundle-analyzer
```

### 💡 Bonnes Pratiques de Développement Green
1. **Code Review orienté performance** : Vérifier l'efficacité énergétique
2. **Tests de charge réguliers** : Mesurer l'impact des changements
3. **Refactoring continu** : Supprimer le code mort et optimiser
4. **Documentation des métriques** : Traçabilité de l'amélioration

### 🌍 Impact Environnemental Estimé
Avec l'application de ces recommandations :
- **Réduction de 60% de la consommation mémoire**
- **Diminution de 40% du temps de démarrage**
- **Réduction de 50% de la taille des déploiements**
- **Économie énergétique estimée : 30-40%**

---

## 📄 Licence

Ce projet est développé dans le cadre du parcours OpenClassrooms "Développeur d'application Java".
