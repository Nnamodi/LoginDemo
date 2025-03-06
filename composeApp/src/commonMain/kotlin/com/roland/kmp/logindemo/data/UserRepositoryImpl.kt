package com.roland.kmp.logindemo.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.roland.kmp.logindemo.domain.model.PhoneNumber
import com.roland.kmp.logindemo.domain.model.User
import com.roland.kmp.logindemo.domain.repo.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

private val USERNAME_KEY = stringPreferencesKey("firstname_key")
private val PASSWORD_KEY = stringPreferencesKey("password_key")

class UserRepositoryImpl : UserRepository, KoinComponent {
	private val dataStore by inject<DataStore<Preferences>>()

	override fun getUserInfo(): Flow<User> {
		return dataStore.data
			.catch { emptyFlow<User>() }
			.map { preferences ->
				User(
					firstName = "",
					lastName = "",
					username = preferences[USERNAME_KEY] ?: "",
					phoneNumber = PhoneNumber("", ""),
					password = preferences[PASSWORD_KEY] ?: ""
				)
			}
	}

	override suspend fun registerUser(user: User): Boolean {
		return try {
			dataStore.edit { preferences ->
				preferences[USERNAME_KEY] = user.username
				preferences[PASSWORD_KEY] = user.password
			}
			true
		} catch (e: Exception) {
			println("registerUser() Error: $e")
			false
		}
	}

	override suspend fun logoutUser(): Boolean {
		return try {
			dataStore.edit { preferences ->
				preferences[USERNAME_KEY] = ""
				preferences[PASSWORD_KEY] = ""
			}
			true
		} catch (e: Exception) {
			println("registerUser() Error: $e")
			false
		}
	}
}