package com.example.testapp1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.testapp1.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("qwe MainFragment onCreateView")
        childFragmentManager
        parentFragmentManager
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onPause() {
        super.onPause()
        println("qwe MainFragment onPause")
        Log.d("FragmentLifecycle", "onPause called in MainFragment")
    }

    override fun onResume() {
        super.onResume()
        println("qwe MainFragment onResume")
    }

    override fun onStart() {
        super.onStart()
        println("qwe MainFragment onStart")
    }

    override fun onStop() {
        super.onStop()
        println("qwe MainFragment onStop")
    }

    // Trigger DialogFragment
    private fun showDialogFragment() {
        val dialog = OverlayDialogFragment()
        dialog.show(parentFragmentManager, "OverlayDialog")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("qwe MainFragment onDestroy")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<Button>(R.id.show_dialog_button).setOnClickListener {
//
//            CoroutineScope(Dispatchers.Main).launch {
//                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com")))
//                delay(2500)
//                startActivity(Intent(requireActivity(), MainActivity::class.java).apply {
//                    setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
//                })
//            }
//            requireActivity().supportFragmentManager.also {
//                it.beginTransaction()
//                    .add(R.id.fragment_container, FirstFragment::class.java, null)
//                    .addToBackStack(null)
//                    .commit()
//            }

            findNavController().navigate(MainFragmentDirections.actionMainFragmentToFirstFragment())

//            val dialog = Dialog(requireContext())
//            dialog.setContentView(R.layout.dialog_custom)
//            dialog.setCancelable(true)
//
//            // Close dialog when button is clicked
//            dialog.findViewById<Button>(R.id.close_button)?.setOnClickListener {
//                dialog.dismiss()
//            }
//
//            dialog.show()
//        }
    }
    

}