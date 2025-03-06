package com.roland.kmp.logindemo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.roland.kmp.logindemo.ui.screens.home.HomeScreen
import com.roland.kmp.logindemo.ui.screens.home.HomeViewModel
import com.roland.kmp.logindemo.ui.screens.login.LoginScreen
import com.roland.kmp.logindemo.ui.screens.login.LoginType
import com.roland.kmp.logindemo.ui.screens.register.RegisterScreen
import com.roland.kmp.logindemo.ui.screens.register.RegisterViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun NavGraph(
	navController: NavHostController,
	startDestination: String = AppRoute.Login.route
) {
	val navActions = NavActions(navController)
	val homeViewModel: HomeViewModel = koinViewModel()
	val registerViewModel: RegisterViewModel = koinViewModel()

	NavHost(
		navController = navController,
		startDestination = startDestination
	) {
		composable(AppRoute.Login.route) {
			LoginScreen(navActions::navigate)
		}
		composable(AppRoute.Home.route) {
			LaunchedEffect(homeViewModel.loggedOut) {
				navActions.navigate(Screens.BackToLogin)
			}

			HomeScreen(
				username = homeViewModel.username,
				actions = homeViewModel::actions
			)
		}
		composable(AppRoute.Register.route) { backStackEntry ->
			val type = backStackEntry.arguments?.getString("type") ?: ""

			RegisterScreen(
				loginType = LoginType.valueOf(type),
				requestState = registerViewModel.requestState,
				inputCheck = registerViewModel.inputCheck,
				actions = registerViewModel::actions,
				navigate = navActions::navigate
			)
		}
	}
}