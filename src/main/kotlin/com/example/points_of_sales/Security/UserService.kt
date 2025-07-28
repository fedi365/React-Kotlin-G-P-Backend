package com.example.points_of_sales.Security

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val passwordEncoder: PasswordEncoder
) {

    fun createUser(username: String, password: String, roles: List<String>): User {
        val roleEntities = roles.mapNotNull {
            roleRepository.findByName(it)
        }.toSet()

        val user = User(
            username = username,
            password = passwordEncoder.encode(password),
            roles = roleEntities
        )
        return userRepository.save(user)
    }
}
