package com.example.testapp1

import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat

class Extensions {


    companion object {

        fun EditText.validatePhone() {

        }

        fun TextView.colorText(colorRes: Int) {
            text = "hello world"
            setTextColor(ContextCompat.getColor(this.context, colorRes))

            var enum = Enums.EXAMPLE_1
            when (enum) {
                Enums.EXAMPLE_1 -> {}
                Enums.EXAMPLE_2 -> {}
                Enums.EXAMPLE_3 -> {}
            }

            var state: State = State.Success(listOf("qwe"))

            when (state) {
                is State.Error -> state.message
                State.Loading -> TODO()
                is State.Success -> TODO()
            }

        }
    }

    enum class Enums(val type: String) {
        EXAMPLE_1(""),
        EXAMPLE_2(""),
        EXAMPLE_3(""),
    }

    sealed interface State {
        data object Loading : State
        class Error(val message: String) : State
        class Success(val data: List<String>) : State
    }

    data class User(
        val name: String,
        val name2: String,
    )



}