package com.example.points_of_sales.entity


import jakarta.persistence.*

@Entity
data class Assurance(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val nom: String,
    val plafondAnnuel: Double,
    val tauxCouverture: Double
)

