package com.demobank.model

data class Account(
val id: String,
val bankId : Long,
val order: Int,
val holder: String,
val role: Int,
val contractNumber: String?,
val label: String,
val productCode: String?,
val balance: Double,
val operations : List<Operations>

)
