package com.example.points_of_sales.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "patient")
data class Patient(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0,
    val nom: String,
    val prenom: String,
    val cin: String,
    val codeAssure: String,
    val adress:String
)
