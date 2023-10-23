package com.demo.domain

import com.demo.banck.data.repository.test.TestBankRepository
import com.demo.domain.test.MainDispatcherRule
import org.junit.Assert.*
import org.junit.Rule

class GetBanksUpdatesUseCaseTest{


    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val bankRepository = TestBankRepository()




}