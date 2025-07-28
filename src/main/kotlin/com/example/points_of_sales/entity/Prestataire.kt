package com.example.points_of_sales.entity

import jakarta.persistence.*

@Entity
data class Prestataire(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val nom: String,
    val email: String,
    val motDePasse: String // à crypter avec BCrypt
)

