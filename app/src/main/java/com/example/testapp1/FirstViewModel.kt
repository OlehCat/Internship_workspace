package com.example.testapp1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class FirstViewModel: ViewModel() {

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
    val liveData = MutableLiveData(Model(0))




}