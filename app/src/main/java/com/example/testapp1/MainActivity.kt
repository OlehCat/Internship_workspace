package com.example.testapp1

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp1.databinding.ActivityMainBinding
import com.example.testapp1.model.FamilyName
import com.example.testapp1.model.Name
import com.example.testapp1.model.PersonName
import com.test.domain.usecase.ProfileUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var profileUseCase: ProfileUseCase

    private lateinit var binding: ActivityMainBinding               // TODO use this for supportFragmentManager.commit  navigation
//    private lateinit var binding: ContentMainBinding                 // TODO use this for proper navigation
    private val errorText by lazy { getString(R.string.next) }
    private val viewModel: FirstViewModel by viewModels()

    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private var job: Job? = null

    @set:Synchronized
    private var counter = 0

    private val adapter = Adapter(::onItemClick)

    private fun onItemClick(text: String) {

    }

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

        println("qweqwe 0x3F.toChar() ${0x3F.toChar()}")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUsers.adapter = adapter
        adapter.submitList(listOf(
            FamilyName("qwe2"),
            PersonName("qwe"),
            PersonName("qwe23"),
            FamilyName("qwe24"),
            PersonName("qwe25"),
        ))
        binding.btnRefresh.setOnClickListener {
            val newList = mutableListOf<Name>()
            repeat(Random.nextInt(6)) {
                newList.add(
                    if (Random.nextBoolean()) PersonName(Random.nextInt(25000).toString())
                    else FamilyName(Random.nextInt(25000).toString())
                )
            }
            repeat(Random.nextInt(1, 3)) {
                newList.add(adapter.items.random())
            }
            adapter.submitList(newList)
        }



//        viewModel.liveData.observe(this) {
//
//        }

//        lifecycleScope.launch {
//            profileUseCase.loadProfile().name.also {
//                println("qweqwe profileUseCase.loadProfile().name.also: $it")
//            }
//            delay(1500)
//            viewModel.loadProfile()
//            viewModel.profileChannel.receive().also {
//                println("qweqwe PROFILE DATA: $it")
//            }
//        }
//
//
//        lifecycleScope.launch {
//            viewModel.channel.send(1)
//            viewModel.channel.send(2)
//            viewModel.channel.send(3)
//        }
//
//        lifecycleScope.launch {
//            viewModel.channel.receive().also {
//                println("qweqwe channel receive 1 $it")
//            }
//            delay(Random.nextLong(50, 500))
//            viewModel.channel.receive().also {
//                println("qweqwe channel receive 1 $it")
//            }
//        }
//
//        lifecycleScope.launch {
//            viewModel.channel.receive().also {
//                println("qweqwe channel receive 2 $it")
//            }
//            delay(Random.nextLong(50, 500))
//            viewModel.channel.receive().also {
//                println("qweqwe channel receive 2 $it")
//            }
//        }
//
//        lifecycleScope.launch {
//
//            viewModel.flow.collectLatest {
//                println("qweqwe flow $it")
//            }
//
//            repeatOnLifecycle(Lifecycle.State.RESUMED) {
//                viewModel.stateFlow.collectLatest {
//                    println("qweqwe liveData $it")
//                }
//            }
//        }


//        binding.fragmentContainer.setOnClickListener {
//            val data = viewModel.stateFlow.value
//            data!!.name = Random.nextInt(0, 100).toString()
//            lifecycleScope.launch {
//                viewModel.stateFlow.emit(data.copy(name = Random.nextInt(0, 100).toString()))
//                println("qweqwe emit $data")
//
//            }
//        }
        Adapter(::onClick)

//        supportFragmentManager.commit {
//            add(R.id.fragment_container, MainFragment::class.java, null, null)
//            addToBackStack(null)
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


