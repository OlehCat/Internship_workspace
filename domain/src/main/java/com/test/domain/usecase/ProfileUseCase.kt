package com.test.domain.usecase

import com.test.domain.repository.ProfileRepo
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val profileRepo: ProfileRepo,
) {

    suspend fun loadProfile() = profileRepo.loadProfile()
//    val flow = DIReplacer.profileRepo.getProfileFlow()

}