package com.roland.kmp.logindemo.ui.navigation

import androidx.navigation.NavHostController
import com.roland.kmp.logindemo.ui.screens.login.LoginType

class NavActions(private val navController: NavHostController) {
	fun navigate(screen: Screens) {
		when (screen) {
			Screens.Home -> navController.navigate(AppRoute.Home.route)
			Screens.Login -> navController.navigate(AppRoute.Login.route)
			Screens.BackToLogin -> {
				navController.navigate(AppRoute.Login.route) {
					launchSingleTop = true
					popUpTo(AppRoute.Login.route) {
						inclusive = true
					}
				}
			}
			is Screens.Register -> navController.navigate(AppRoute.Register.route)
		}
	}
}

sealed class AppRoute(val route: String) {
	data object Home : AppRoute("home")
	data object Login : AppRoute("login")
	data object Register : AppRoute("register/{type}") {
		fun routeWithType(loginType: String) = String.format("register/%s", loginType)
	}
}

sealed class Screens {
	data object Home : Screens()
	data object Login : Screens()
	data object BackToLogin : Screens()
	data class Register(val loginType: LoginType) : Screens()
}