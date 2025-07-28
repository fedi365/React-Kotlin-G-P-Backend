package com.example.points_of_sales.controller

import com.example.points_of_sales.entity.CarteAssuree
import com.example.points_of_sales.services.CarteAssureeService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/cartes")
@CrossOrigin
class CarteAssureeController(
    private val carteAssureeService: CarteAssureeService
) {

    @GetMapping("/{numero}")
    fun getCarte(@PathVariable numero: String): CarteAssuree {
        return carteAssureeService.getCarteByNumero(numero)
    }
}
