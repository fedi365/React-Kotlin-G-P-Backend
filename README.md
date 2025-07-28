# Points of Sales - Documentation Kotlin

## Aperçu du Projet

Points of Sales est une application de gestion pour les prestataires de services médicaux. Elle permet de gérer les patients, les actes médicaux, les assurances, les transactions et les factures.

## Structure du Projet

Le projet est développé en Kotlin avec Spring Boot et utilise JPA pour la persistance des données.

### Entités

#### Patient
```kotlin
@Entity
@Table(name = "patient")
data class Patient(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    val nom: String,
    val prenom: String,
    val cin: String,
    val codeAssure: String
)
```
Représente un patient dans le système avec ses informations personnelles.

#### Prestataire
```kotlin
@Entity
data class Prestataire(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val nom: String,
    val email: String,
    val motDePasse: String // à crypter avec BCrypt
)
```
Représente un prestataire de services médicaux (médecin, clinique, etc.).

#### Assurance
```kotlin
@Entity
data class Assurance(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val nom: String,
    val plafondAnnuel: Double,
    val tauxCouverture: Double
)
```
Représente une compagnie d'assurance avec son plafond annuel et son taux de couverture.

#### CarteAssuree
```kotlin
@Entity
data class CarteAssuree(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val numeroCarte: String,
    val dateExpiration: LocalDate,
    @ManyToOne
    val patient: Patient,
    @ManyToOne
    val assurance: Assurance
)
```
Représente une carte d'assurance liée à un patient et à une compagnie d'assurance.

#### ActeMedical
```kotlin
@Entity
data class ActeMedical(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val designation: String,
    val prix: Double
)
```
Représente un acte médical avec sa désignation et son prix.

#### Transaction
```kotlin
@Entity
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val date: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    val carteAssuree: CarteAssuree,
    @ManyToMany
    val actes: List<ActeMedical> = emptyList(),
    val montantTotal: Double,
    val montantAssurance: Double,
    val montantPatient: Double,
    @ManyToOne
    val prestataire: Prestataire
)
```
Représente une transaction médicale avec les actes effectués, les montants et les parties impliquées.

#### Facture
```kotlin
@Entity
data class Facture(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @OneToOne
    val transaction: Transaction,
    val pdfUrl: String
)
```
Représente une facture liée à une transaction avec l'URL du PDF de la facture.

### Repositories

#### PatientRepository
```kotlin
interface PatientRepository : JpaRepository<Patient, Long> {
    fun findByPatientId(id: Long): MutableList<Patient>
    fun findPatientsByNombre(nombre: String) : MutableList<Patient>
}
```
Gère l'accès aux données des patients.

#### FactureRepository
```kotlin
interface FactureRepository : JpaRepository<Facture, Long> {
}
```
Gère l'accès aux données des factures.

#### TransactionRepository
```kotlin
interface TransactionRepository : JpaRepository<Transaction, Long> {
    fun findAllByPrestataire(prestataire: Prestataire): List<Transaction>
}
```
Gère l'accès aux données des transactions avec une méthode pour trouver toutes les transactions d'un prestataire.

#### CarteAssureeRepository
```kotlin
interface CarteAssureeRepository : JpaRepository<CarteAssuree, Long> {
    fun findByNumeroCarte(numeroCarte: String): Optional<CarteAssuree>
    fun findByDateExpiration(date: LocalDate): Optional<CarteAssuree>
}
```
Gère l'accès aux données des cartes d'assurance avec des méthodes pour rechercher par numéro ou date d'expiration.

#### AssuranceRepository
```kotlin
interface AssuranceRepository : JpaRepository<Assurance, Long>{
    fun findByPatientId(patientId: Long): List<Assurance>
}
```
Gère l'accès aux données des assurances avec une méthode pour trouver les assurances d'un patient.

### Services

#### CarteAssureeService
```kotlin
@Service
class CarteAssureeService(
    private val carteAssureeRepository: CarteAssureeRepository
) {
    fun findByNumero(numero: String):
            CarteAssuree = carteAssureeRepository.findByNumeroCarte(numero)
            .orElseThrow { RuntimeException("Carte assurée introuvable") }

    fun findByDate(dateExpiration: LocalDate):
            CarteAssuree = carteAssureeRepository.findByDateExpiration(dateExpiration)
        .orElseThrow(){ RuntimeException("Carte assurée introuvable") }
}
```
Fournit des services pour trouver des cartes d'assurance par numéro ou date d'expiration.

## Configuration et Utilisation

### Prérequis
- JDK 11 ou supérieur
- Maven
- Base de données (configurée dans application.properties)

### Installation
1. Cloner le dépôt
2. Configurer la base de données dans `src/main/resources/application.properties`
3. Exécuter `mvn clean install` pour construire le projet
4. Exécuter `mvn spring-boot:run` pour démarrer l'application

### Utilisation
L'application expose des API REST pour interagir avec les différentes entités. Consultez la documentation de l'API pour plus de détails.

## Bonnes Pratiques Kotlin

### Data Classes
Kotlin permet de créer des classes de données concises avec la fonctionnalité `data class`. Cela génère automatiquement les méthodes `equals()`, `hashCode()`, `toString()` et `copy()`.

```kotlin
data class Patient(val nom: String, val prenom: String)
```

### Null Safety
Kotlin offre une sécurité contre les valeurs nulles. Utilisez `?` pour indiquer qu'une variable peut être nulle et `!!` pour affirmer qu'une variable n'est pas nulle.

```kotlin
val patient: Patient? = findPatient(id)
val nom = patient?.nom ?: "Inconnu"
```

### Extensions de Fonctions
Kotlin permet d'étendre des classes existantes sans héritage.

```kotlin
fun Patient.nomComplet(): String = "$prenom $nom"
```

### Coroutines
Pour les opérations asynchrones, utilisez les coroutines Kotlin.

```kotlin
suspend fun fetchPatient(id: Long): Patient {
    return withContext(Dispatchers.IO) {
        patientRepository.findById(id).orElseThrow()
    }
}
```

### Collections Fonctionnelles
Utilisez les opérations fonctionnelles sur les collections pour un code plus concis.

```kotlin
val patientsAssures = patients.filter { it.codeAssure.isNotEmpty() }
```