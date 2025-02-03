package com.test.data

import com.test.domain.model.Profile
import com.test.domain.repository.ProfileRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ProfileRepoImpl : ProfileRepo {

    private val flow: MutableStateFlow()

    override suspend fun getProfileFlow() = flow


    override suspend fun loadProfile(): Profile {
        // return profileApi.loadData().toProfile()

        return Profile("Oleh")
    }

}