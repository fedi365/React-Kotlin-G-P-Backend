package com.example.points_of_sales.Security

import com.example.points_of_sales.Security.JWT.JwtService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    val authManager: AuthenticationManager,
    val jwtService: JwtService,
    val userDetailsService: CustomUserDetailsService
) {

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Map<String, Any>> {
        val authToken = UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
        authManager.authenticate(authToken)

        // Charger l'utilisateur
        val userDetails = userDetailsService.loadUserByUsername(loginRequest.username)
        val roles = userDetails.authorities.map { it.authority }

        // Générer le token avec les rôles inclus
        val jwt = jwtService.generateToken(userDetails.username, roles)

        return ResponseEntity.ok(
            mapOf(
                "token" to jwt,
                "username" to userDetails.username,
                "roles" to roles
            )
        )
    }
}

data class LoginRequest(val username: String, val password: String)
