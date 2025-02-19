package com.example.testapp1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.testapp1.databinding.ItemUserLayoutBinding
import com.example.testapp1.databinding.ItemUserTitleLayoutBinding
import com.example.testapp1.model.FamilyName
import com.example.testapp1.model.Name
import com.example.testapp1.model.PersonName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Adapter(
    private val onClick: (itemText: String) -> Unit
): RecyclerView.Adapter<ViewHolder>() {

    val PERSON_VIEW_TYPE = 1
    val FAMILY_VIEW_TYPE = 2

    var items = mutableListOf<Name>()

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return items[position].hashCode().toLong()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(items: List<Name>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is PersonName -> PERSON_VIEW_TYPE
            is FamilyName -> FAMILY_VIEW_TYPE
            else -> throw Exception("INVALID TYPE")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            PERSON_VIEW_TYPE -> {
                val binding = ItemUserLayoutBinding.inflate(LayoutInflater.from(parent.context),  parent, false)
                PersonViewHolder(binding)
            }
            FAMILY_VIEW_TYPE -> {
                val binding = ItemUserTitleLayoutBinding.inflate(LayoutInflater.from(parent.context),  parent, false)
                TitleViewHolder(binding)
            }
            else -> throw Exception("INVALID TYPE")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (val item = items[position]) {

            is PersonName -> {
                val binding = (holder as PersonViewHolder).binding
                CoroutineScope(Dispatchers.Main).launch {
                    binding.tvTitle.text = item.name
                    binding.ivIcon.setImageDrawable(null)
                    delay(500)
                    Glide.with(holder.binding.ivIcon)
                        .load(R.drawable.logo)
                        .transition(DrawableTransitionOptions.withCrossFade(500))
                        .into(holder.binding.ivIcon)
                }
            }

            is FamilyName -> {
                val binding = (holder as TitleViewHolder).binding
                binding.tvTitle.text = item.familyName
            }

            else -> throw Exception("INVALID TYPE")
        }

    }

    override fun getItemCount() = items.size

    class PersonViewHolder(val binding: ItemUserLayoutBinding) : ViewHolder(binding.root)
    class TitleViewHolder(val binding: ItemUserTitleLayoutBinding) : ViewHolder(binding.root)

}