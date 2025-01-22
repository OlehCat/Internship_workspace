package com.example.testapp1

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class Adapter(
    private val onClick: (itemText: String) -> Unit
): RecyclerView.Adapter<Adapter.CustomViewHolder>() {

    class CustomViewHolder(itemView: View) : ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val text = ""
            onClick(text)
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}