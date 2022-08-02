package com.bn.flights.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseListAdapter<Binding : ViewBinding, Item : Any> :
    RecyclerView.Adapter<BaseViewHolder<Binding, Item>>() {
    protected val items = mutableListOf<Item>()
    abstract val bindAction: (binding: Binding, item: Item) -> Unit
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<Binding, Item> {
        return BaseViewHolder(
            initViewBinding(parent), bindAction
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Binding, Item>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    fun addItems(i: List<Item>) = items.run {
        val oldSize = items.size
        addAll(i)
        notifyItemRangeChanged(oldSize, i.size)
    }

    fun clearItems() = items.run {
        if (isNotEmpty()) {
            clear()
            notifyDataSetChanged()
        }
    }

    fun replaceItems(i: List<Item>) = items.run {
        if (isNotEmpty()) {
            clear()
            notifyDataSetChanged()
        }
        addItems(i)
    }

    fun addItem(i: Item) = items.run {
        add(i)
        notifyItemInserted(items.size - 1)
    }

    fun replaceItem(item: Item, target: Item) = items.run {
        val targetIndex = indexOf(target)
        if (targetIndex != -1) {
            set(targetIndex, item)
            notifyItemChanged(targetIndex)
            true
        } else false
    }

    fun findItem(target: Item) =
        items.find { it == target }?.let { true } ?: false


    @Suppress("UNCHECKED_CAST")
    private fun initViewBinding(parent: ViewGroup): Binding {
        val type = javaClass.genericSuperclass as ParameterizedType
        val aClass = type.actualTypeArguments[0] as Class<*>
        val method = aClass.getDeclaredMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        )
        return method.invoke(null, LayoutInflater.from(parent.context), parent, false) as Binding
    }
}