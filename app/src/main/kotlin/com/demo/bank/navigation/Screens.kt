package com.demo.bank.navigation

sealed class Screens(val route : String) {
    data object Account : Screens("account_screen")
    data object Converter : Screens("converter_screen")
    data object Simulator : Screens("simulator_screen")
    data object AccountDetails : Screens("account_details")

}