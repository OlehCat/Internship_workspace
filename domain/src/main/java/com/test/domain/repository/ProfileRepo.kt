package com.test.domain.repository

import com.test.domain.model.Profile
import kotlinx.coroutines.flow.Flow

interface ProfileRepo {

    suspend fun getProfileFlow(): Flow<Profile>

    suspend fun loadProfile(): Profile

}