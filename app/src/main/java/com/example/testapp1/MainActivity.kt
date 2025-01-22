package com.example.testapp1

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import androidx.fragment.app.replace
import com.example.testapp1.Extensions.Companion.colorText
import com.example.testapp1.Extensions.Companion.validatePhone
import com.example.testapp1.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.math.max
import kotlin.math.min


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val errorText by lazy { getString(R.string.next) }

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private var job: Job? = null

    @set:Synchronized
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = scope.launch {
            counter++
            println("zxcxc onCreate START ")
            delay(5500)
            println("zxcxc onCreate END")
            job = null
            if (counter > 0) {
                counter--
            }
            if (counter > 0) {
                join()
            }
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.root.setOnClickListener {
            counter++
            doOnBackground()
        }

        Adapter(::onClick)
        println("asd MainActivity onCreate")


    }
    suspend fun join() {
        job?.join()
        job = scope.launch {
            println("zxcxc doOnBackground START ")
            delay(2500)
            println("zxcxc doOnBackground END")
            job = null
            if (counter > 0) {
                counter--
            }
            if (counter > 0) {
                join()
            }
        }
    }

    fun doOnBackground() {
        if (counter == 1) {
            scope.launch {
                join()
            }
        }

    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
    }

    fun onClick(text: String) {

    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onStart() {
        super.onStart()

    }

    override fun onStop() {
        super.onStop()

    }

}


