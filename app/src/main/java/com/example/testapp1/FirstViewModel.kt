package com.example.testapp1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.domain.model.Profile
import com.test.domain.usecase.ProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FirstViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase,
//    private val profileRepoImpl: ProfileRepoImpl    // Don't so this! :-)
): ViewModel() {

    var flow = flow {
        var i = 0
        repeat(10) {
            delay(1500)
            var b = i++
            if (b % 2 == 0) emit(b)
        }
    }

    val stateFlow = MutableStateFlow(Model(32))
    val sharedFlow = MutableSharedFlow<Int>()
    val channel = Channel<Int>()
    val profileChannel = Channel<Profile>()
    val liveData = MutableLiveData(Model(0))

    fun loadProfile() {
        viewModelScope.launch {
            val result = profileUseCase.loadProfile()
//            val result2 = profileRepoImpl.loadProfile()     // Don't so this! :-)
            profileChannel.send(result)
        }
    }




}