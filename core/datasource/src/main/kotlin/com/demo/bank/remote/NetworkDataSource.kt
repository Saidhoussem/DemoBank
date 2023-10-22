package com.demo.bank.remote


interface NetworkDataSource {

    suspend fun getBanks(): List<BankResponse>
}