package com.roland.kmp.logindemo.di

import com.roland.kmp.logindemo.data.PhoneRepositoryImpl
import com.roland.kmp.logindemo.data.UserRepositoryImpl
import com.roland.kmp.logindemo.domain.repo.PhoneRepository
import com.roland.kmp.logindemo.domain.repo.UserRepository
import com.roland.kmp.logindemo.ui.screens.home.HomeViewModel
import com.roland.kmp.logindemo.ui.screens.register.RegisterViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
	single<UserRepository> { UserRepositoryImpl() }
	single<PhoneRepository> { PhoneRepositoryImpl() }
	viewModel { HomeViewModel() }
	viewModel { RegisterViewModel() }
}

fun initializeKoin() {
	startKoin {
		modules(appModule)
	}
}