package com.test.data.model

import com.test.domain.model.Profile

data class ProfileResponse(
    val res: String? = null
)

fun ProfileResponse.toProfile() = Profile(
    name = res ?: ""
)

