package com.example.points_of_sales.controller

import com.example.points_of_sales.entity.Prestataire
import com.example.points_of_sales.services.PrestataireServices
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["http://localhost:19000", "exp://192.168.1.100:19000"])
@RequestMapping("/prestataires")
class PrestataireController(private val prestataireService: PrestataireServices) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPrestataire(@RequestBody prestataire: Prestataire): Prestataire {
        return prestataireService.CreatePrestataire(prestataire)
    }
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllPrestataire(): List<Prestataire> {
        return prestataireService.getAllPrestataires()}

    @GetMapping("/{id}")
fun getPrestataireById(@PathVariable id: Long): Prestataire {
        return prestataireService.getPrestataireById(id)}
}
