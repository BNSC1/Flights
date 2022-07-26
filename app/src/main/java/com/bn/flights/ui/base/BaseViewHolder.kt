package com.bn.flights.ui.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BaseViewHolder<Binding : ViewBinding, Item : Any>(
    private val binding: Binding,
    private val bindAction: (binding: Binding, item: Item) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    open fun bind(item: Item) {
        bindAction(binding, item)
    }
}