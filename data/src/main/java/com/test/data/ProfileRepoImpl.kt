package com.test.data

import com.test.domain.model.Profile
import com.test.domain.repository.ProfileRepo
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton


class ProfileRepoImpl @Inject constructor(
//    private val profileApi: ProfileApi,
) : ProfileRepo {

//    private val flow: MutableStateFlow()

    override suspend fun getProfileFlow() = MutableStateFlow(Profile(""))


    override suspend fun loadProfile(): Profile {
        // return profileApi.loadData().toProfile()

        return Profile("Oleh")
    }

}