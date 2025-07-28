package com.example.points_of_sales.services


import com.example.points_of_sales.entity.Prestataire
import com.example.points_of_sales.entity.Transaction
import com.example.points_of_sales.repository.TransactionRepository
import org.springframework.stereotype.Service

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository
) {
    fun save(transaction: Transaction): Transaction {
        return transactionRepository.save(transaction)
    }

    fun findByPrestataire(prestataire: Prestataire): List<Transaction> {
        return transactionRepository.findAllByPrestataire(prestataire)
    }
}
