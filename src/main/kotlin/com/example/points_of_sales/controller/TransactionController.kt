package com.example.points_of_sales.controller


import com.example.points_of_sales.entity.Transaction
import com.example.points_of_sales.services.TransactionService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin
class TransactionController(
    private val transactionService: TransactionService
) {

    @PostMapping
    fun enregistrer(@RequestBody transaction: Transaction): Transaction {
        return transactionService.save(transaction)
    }
}
