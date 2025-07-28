package com.example.points_of_sales.repository


import com.example.points_of_sales.entity.Prestataire
import com.example.points_of_sales.entity.Transaction
import org.springframework.data.jpa.repository.JpaRepository

interface TransactionRepository : JpaRepository<Transaction, Long> {
    fun findAllByPrestataire(prestataire: Prestataire): List<Transaction>
}
