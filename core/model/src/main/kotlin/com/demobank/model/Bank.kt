package com.demobank.model


data class Bank (
    val id : Long = 0L,
    val name: String,
    val isCA: Int? = null,
    var accounts: List<Account>?,
    var balance : Double
)