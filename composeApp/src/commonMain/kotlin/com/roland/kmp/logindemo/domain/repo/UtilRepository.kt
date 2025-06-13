package com.roland.kmp.logindemo.domain.repo

interface UtilRepository {

    fun getLoginStatus(): Boolean

    suspend fun saveLoginStatus(isLaunched: Boolean): Boolean

}