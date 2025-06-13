package com.roland.kmp.logindemo.ui.navigation

import androidx.navigation.NavHostController
import com.roland.kmp.logindemo.ui.screens.login.LoginType

class NavActions(private val navController: NavHostController) {
	fun navigate(screen: Screens) {
		when (screen) {
			Screens.Home -> {
				navController.graph.setStartDestination(AppRoute.Home.route)
				navController.navigate(AppRoute.Home.route) {
					launchSingleTop = true
					popUpTo(AppRoute.Login.route) {
						inclusive = true
					}
				}
			}
			Screens.Login -> navController.navigate(AppRoute.Login.route)
			Screens.BackToLogin -> {
				navController.graph.setStartDestination(AppRoute.Login.route)
				navController.navigate(AppRoute.Login.route) {
					launchSingleTop = true
					popUpTo(AppRoute.Home.route) {
						inclusive = true
					}
				}
			}
			is Screens.Register -> navController.navigate(
				AppRoute.Register.routeWithType(screen.loginType.name)
			)
		}
	}
}

sealed class AppRoute(val route: String) {
	data object Home : AppRoute("home")
	data object Login : AppRoute("login")
	data object Register : AppRoute("register/{type}") {
		fun routeWithType(loginType: String) = "register/$loginType"
	}
}

sealed class Screens {
	data object Home : Screens()
	data object Login : Screens()
	data object BackToLogin : Screens()
	data class Register(val loginType: LoginType) : Screens()
}