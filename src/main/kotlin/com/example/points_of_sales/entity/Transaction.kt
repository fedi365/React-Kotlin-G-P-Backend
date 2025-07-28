package com.example.points_of_sales.entity

import jakarta.persistence.*
import java.time.LocalDateTime

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
