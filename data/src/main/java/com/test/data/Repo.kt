package com.test.data

import com.test.domain.DIReplacer

object Repo {

    init {
        println("qweqwe Repo init")
        DIReplacer.profileRepo = ProfileRepoImpl()
    }

    fun doSomeTest() {
        println("Qweqwe doSomeTest")
    }

}