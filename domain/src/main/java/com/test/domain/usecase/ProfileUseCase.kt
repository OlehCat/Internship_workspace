package com.test.domain.usecase

import com.test.domain.DIReplacer

class ProfileUseCase(

) {

    suspend fun loadProfile() = DIReplacer.profileRepo.loadProfile()
    val flow = DIReplacer.profileRepo.getProfileFlow()

}