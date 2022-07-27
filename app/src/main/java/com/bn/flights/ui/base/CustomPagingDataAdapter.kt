package com.bn.flights.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class CustomPagingDataAdapter<Binding : ViewBinding, Item : Any>(
    comparator: DiffUtil.ItemCallback<Item>
) : PagingDataAdapter<Item,BaseViewHolder<Binding, Item>>(comparator) {
    protected abstract val bindAction: (binding: Binding, item: Item) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BaseViewHolder<Binding, Item> {
        return BaseViewHolder(
            initViewBinding(parent), bindAction
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Binding, Item>, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

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