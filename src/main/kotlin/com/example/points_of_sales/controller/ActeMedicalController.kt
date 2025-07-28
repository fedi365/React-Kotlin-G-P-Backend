package com.example.points_of_sales.controller


import com.example.points_of_sales.services.ActeMedicalService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/actes")
@CrossOrigin
class ActeMedicalController(
    private val acteMedicalService: ActeMedicalService
) {
    @GetMapping
    fun getAll() = acteMedicalService.getAll()
}