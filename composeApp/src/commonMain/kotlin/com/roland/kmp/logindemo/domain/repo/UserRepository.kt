package com.roland.kmp.logindemo.domain.repo

import com.roland.kmp.logindemo.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

	fun getUserInfo(): Flow<User>

	suspend fun registerUser(user: User): Boolean

	suspend fun logoutUser(): Boolean

}