package com.roland.kmp.logindemo.data

import com.roland.kmp.logindemo.data.sharedPref.AppSharedPrefProvider
import com.roland.kmp.logindemo.domain.repo.UtilRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UtilRepositoryImpl : UtilRepository, KoinComponent {
    private val sharedPref: AppSharedPrefProvider by inject()

    override fun getLoginStatus(): Boolean {
        val result = sharedPref.get(LOGIN_STATUS_KEY)
        return result == "true"
    }

    override suspend fun saveLoginStatus(isLaunched: Boolean): Boolean {
        return sharedPref.set(LOGIN_STATUS_KEY, isLaunched.toString())
    }

    companion object {
        const val LOGIN_STATUS_KEY = "login_status"
    }
}