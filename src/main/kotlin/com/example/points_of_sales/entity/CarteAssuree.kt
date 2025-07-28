package com.example.points_of_sales.entity


import jakarta.persistence.*
import java.time.LocalDate

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
