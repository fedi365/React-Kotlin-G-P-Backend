package com.example.points_of_sales.entity

import jakarta.persistence.*

@Entity
data class ActeMedical(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val designation: String,
    val prix: Double
)
