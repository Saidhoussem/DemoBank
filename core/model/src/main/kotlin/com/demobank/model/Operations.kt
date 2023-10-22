package com.demobank.model

data class Operations(

    val id       : String,
    val accountId : String?,
    val title    : String,
    val amount   : String,
    val category : String,
    val date     : String

)
