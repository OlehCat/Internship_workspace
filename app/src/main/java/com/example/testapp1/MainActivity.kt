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
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.isVisible
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.testapp1.Extensions.Companion.colorText
import com.example.testapp1.Extensions.Companion.validatePhone
import com.example.testapp1.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val errorText by lazy { getString(R.string.next) }
    private val viewModel: FirstViewModel by viewModels()

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
        viewModel.liveData.observe(this) {

        }

        lifecycleScope.launch {
            viewModel.channel.send(1)
            viewModel.channel.send(2)
            viewModel.channel.send(3)
        }

        lifecycleScope.launch {
            viewModel.channel.receive().also {
                println("qweqwe channel receive 1 $it")
            }
            delay(Random.nextLong(50, 500))
            viewModel.channel.receive().also {
                println("qweqwe channel receive 1 $it")
            }
        }

        lifecycleScope.launch {
            viewModel.channel.receive().also {
                println("qweqwe channel receive 2 $it")
            }
            delay(Random.nextLong(50, 500))
            viewModel.channel.receive().also {
                println("qweqwe channel receive 2 $it")
            }
        }

        lifecycleScope.launch {

            viewModel.flow.collectLatest {
                println("qweqwe flow $it")
            }

            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.stateFlow.collectLatest {
                    println("qweqwe liveData $it")
                }
            }
        }


        binding.fragmentContainer.setOnClickListener {
            val data = viewModel.stateFlow.value
            data!!.name = Random.nextInt(0, 100).toString()
            lifecycleScope.launch {
                viewModel.stateFlow.emit(data.copy(name = Random.nextInt(0, 100).toString()))
                println("qweqwe emit $data")

            }
        }
        Adapter(::onClick)

//        supportFragmentManager.commit {
//            replace(R.id.transparent_view, FirstFragment())
//        }


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


