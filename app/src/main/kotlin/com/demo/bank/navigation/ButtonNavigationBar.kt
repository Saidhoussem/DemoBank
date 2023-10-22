package com.demo.bank.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.demo.bank.account.AccountScreen
import com.demo.bank.account.ConvertScreen
import com.demo.bank.account.SimulatorScreen
import com.demo.bank.account.navigation.detailsScreen


@Composable
fun ButtonNavigationBar(
    navbarItems : List<BottomNavigationItem>
    ){
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navbarItems.forEachIndexed { _, navigationItem ->
                    NavigationBarItem(
                        selected = navigationItem.route == currentDestination?.route,
                        label = {
                            Text(navigationItem.name)
                        },
                        icon = {
                            Icon(
                                navigationItem.icon,
                                contentDescription = navigationItem.name
                            )
                        },
                        onClick = {
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) {paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Account.route,
            modifier = Modifier.padding(paddingValues = paddingValues)) {


            composable(Screens.Account.route) {
                AccountScreen(navController)
            }

            composable(Screens.Converter.route) {
                ConvertScreen(
                    navController
                )
            }
            composable(Screens.Simulator.route) {
                SimulatorScreen(
                    navController
                )
            }
            detailsScreen { navController.popBackStack() }
        }
    }

}

