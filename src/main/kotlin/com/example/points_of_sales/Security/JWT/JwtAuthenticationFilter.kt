package com.example.points_of_sales.Security.JWT

import com.example.points_of_sales.Security.CustomUserDetailsService
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

// JwtAuthenticationFilter.kt
@Component
class JwtAuthenticationFilter(
    private val jwtService: JwtService,
    private val userDetailsService: CustomUserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authHeader = request.getHeader("Authorization")
        println("==> Authorization Header: $authHeader")

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            println("==> No Bearer token found, continuing filter chain...")
            filterChain.doFilter(request, response)
            return
        }

        val token = authHeader.substring(7)
        println("==> Extracted Token: $token")

        try {
            val username = jwtService.extractUsername(token)
            println("==> Extracted Username from Token: $username")

            if (username != null && SecurityContextHolder.getContext().authentication == null) {
                println("==> No existing authentication, loading user details...")
                val userDetails = userDetailsService.loadUserByUsername(username)
                println("==> Loaded UserDetails: username=${userDetails.username}, authorities=${userDetails.authorities}")

                if (jwtService.isTokenValid(token, userDetails)) {
                    println("==> Token is valid, setting authentication...")
                    val authToken = UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.authorities
                    )
                    SecurityContextHolder.getContext().authentication = authToken
                } else {
                    println("==> Token is invalid.")
                }
            } else {
                println("==> Username is null or authentication already exists.")
            }
        } catch (ex: Exception) {
            println("==> Exception in JWT filter: ${ex.message}")
            ex.printStackTrace()
            SecurityContextHolder.clearContext()
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT")
            return
        }

        println("==> Continuing filter chain...")
        filterChain.doFilter(request, response)
    }
}
