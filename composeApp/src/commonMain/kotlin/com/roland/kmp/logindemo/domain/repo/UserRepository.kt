package com.roland.kmp.logindemo.domain.repo

import com.roland.kmp.logindemo.domain.model.User

interface UserRepository {

	fun getUserInfo(): User

	suspend fun registerUser(user: User): Boolean

}