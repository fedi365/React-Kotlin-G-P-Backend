package com.example.points_of_sales.entity

import jakarta.persistence.*

@Entity
data class Facture(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @OneToOne
    val transaction: Transaction,

    val pdfUrl: String

)
