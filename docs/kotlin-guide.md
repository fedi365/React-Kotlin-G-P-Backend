# Guide Kotlin pour Points of Sales

Ce guide fournit des informations détaillées sur l'utilisation de Kotlin dans le projet Points of Sales.

## Caractéristiques de Kotlin utilisées dans le projet

### Data Classes

Les data classes sont largement utilisées dans ce projet pour représenter les entités JPA. Elles offrent plusieurs avantages :

```kotlin
@Entity
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

Avantages :
- Génération automatique des méthodes `equals()`, `hashCode()`, et `toString()`
- Fonction `copy()` pour créer des copies modifiées
- Déstructuration pour extraire les propriétés : `val (nom, prenom) = patient`

### Propriétés et Champs

Kotlin simplifie la déclaration des propriétés :

```kotlin
// Propriété en lecture seule (val)
val nom: String

// Propriété modifiable (var)
var id: Long = 0
```

### Valeurs par défaut pour les paramètres

Les constructeurs peuvent avoir des valeurs par défaut :

```kotlin
data class Transaction(
    val id: Long = 0,
    val date: LocalDateTime = LocalDateTime.now(),
    // ...
)
```

### Null Safety

Kotlin offre une sécurité contre les valeurs nulles :

```kotlin
// Variable qui ne peut pas être nulle
val patient: Patient

// Variable qui peut être nulle
val patient: Patient?

// Opérateur d'appel sécurisé
val nom = patient?.nom

// Opérateur Elvis pour fournir une valeur par défaut
val nom = patient?.nom ?: "Inconnu"

// Assertion non-null (à utiliser avec précaution)
val nom = patient!!.nom
```

### Extensions de fonctions

Vous pouvez étendre les classes existantes sans héritage :

```kotlin
// Extension pour obtenir le nom complet d'un patient
fun Patient.nomComplet(): String = "$prenom $nom"

// Extension pour vérifier si une carte d'assurance est expirée
fun CarteAssuree.estExpiree(): Boolean = dateExpiration.isBefore(LocalDate.now())
```

### Fonctions d'ordre supérieur et lambdas

Kotlin permet d'utiliser des fonctions comme paramètres et de créer des lambdas :

```kotlin
// Filtrer les patients assurés
val patientsAssures = patients.filter { it.codeAssure.isNotEmpty() }

// Mapper les patients à leurs noms
val nomsPatients = patients.map { "${it.prenom} ${it.nom}" }

// Trouver un patient par ID
val patient = patients.find { it.id == patientId }
```

### Coroutines

Pour les opérations asynchrones, utilisez les coroutines Kotlin :

```kotlin
// Fonction suspendue pour récupérer un patient de manière asynchrone
suspend fun fetchPatient(id: Long): Patient {
    return withContext(Dispatchers.IO) {
        patientRepository.findById(id).orElseThrow()
    }
}

// Lancement d'une coroutine
fun processPatientAsync(id: Long) {
    viewModelScope.launch {
        try {
            val patient = fetchPatient(id)
            // Traiter le patient
        } catch (e: Exception) {
            // Gérer l'erreur
        }
    }
}
```

## Bonnes pratiques pour ce projet

### Utiliser les propriétés immuables (val) autant que possible

Préférez `val` à `var` pour améliorer la prévisibilité et la sécurité du code.

### Exploiter les fonctionnalités de JPA avec Kotlin

```kotlin
// Utiliser les annotations JPA avec les data classes
@Entity
@Table(name = "patients")
data class Patient(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val nom: String,
    val prenom: String
)

// Relations entre entités
@ManyToOne
val assurance: Assurance
```

### Utiliser les fonctions d'extension pour la logique métier

Placez la logique métier dans des fonctions d'extension pour garder les entités propres et faciliter les tests.

### Implémenter des repositories personnalisés

```kotlin
interface PatientRepository : JpaRepository<Patient, Long> {
    // Méthodes de requête personnalisées
    fun findByNom(nom: String): List<Patient>
}
```

### Utiliser les coroutines pour les opérations de base de données

Les opérations de base de données peuvent être longues. Utilisez les coroutines pour éviter de bloquer le thread principal.

## Ressources d'apprentissage

- [Documentation officielle de Kotlin](https://kotlinlang.org/docs/home.html)
- [Kotlin Koans](https://play.kotlinlang.org/koans/overview)
- [Spring Boot avec Kotlin](https://spring.io/guides/tutorials/spring-boot-kotlin/)
- [JPA avec Kotlin](https://www.baeldung.com/kotlin/jpa)
