package com.demo.bank.remote


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable



@Serializable
data class BankResponse (

    @SerializedName("name")
    val name: String,
    @SerializedName("isCA")
    val isCA: Int? = null,
    @SerializedName("accounts")
    var accounts: List<AccountResponse>?
)

@Serializable
data class AccountResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("bankId")
    val bankId : Long,
    @SerializedName("order")
    val order: Int,
    @SerializedName("holder")
    val holder: String,
    @SerializedName("role")
    val role: Int,
    @SerializedName("contract_number")
    val contractNumber: String?,
    @SerializedName("label")
    val label: String,
    @SerializedName("product_code")
    val productCode: String?,
    @SerializedName("balance")
    val balance: Double,
    @SerializedName("operations")
    val operations : List<OperationsResponse>

)
@Serializable
data class OperationsResponse(
    @SerializedName("id")
    val id       : String,
    @SerializedName("accountId")
    val accountId : String?,
    @SerializedName("title")
    val title    : String,
    @SerializedName("amount")
    val amount   : String,
    @SerializedName("category")
    val category : String,
    @SerializedName("date")
    val date     : String

)

