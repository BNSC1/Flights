package com.bn.flights.ui.launch.adapter

import com.bn.flights.databinding.ItemLaunchLinkDetailBinding
import com.bn.flights.ktx.setVisible
import com.bn.flights.ui.base.BaseListAdapter
import com.bn.flights.ui.base.OnItemClickListener

class LaunchLinkDetailAdapter(onLinkClickListener: OnItemClickListener<LaunchDetail.LaunchLinkDetail>?) :
    BaseListAdapter<ItemLaunchLinkDetailBinding, LaunchDetail.LaunchLinkDetail>() {
    override val bindAction: (binding: ItemLaunchLinkDetailBinding, item: LaunchDetail.LaunchLinkDetail) -> Unit =
        { binding, item ->
            with(binding.root) {
                item.url?.let {
                    setVisible()
                    text = item.title
                    onLinkClickListener?.let { listener ->
                        setOnClickListener {
                            listener.onItemClick(item)
                        }
                    }
                }
            }
        }
}