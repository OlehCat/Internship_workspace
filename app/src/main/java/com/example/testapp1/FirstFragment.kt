package com.example.testapp1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.testapp1.databinding.FragmentFirstBinding
import kotlin.random.Random

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: FirstViewModel by viewModels()
    private val viewModel2: FirstViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        println("qweqwe onCreateView FirstFragment")
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.liveData.observe(viewLifecycleOwner) {
            println("qweqwe liveData $it")
        }

        binding.button1.setOnClickListener {
            val data = viewModel.liveData.value
            data!!.name = Random.nextInt(0, 100).toString()
            viewModel.liveData.postValue(data)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}