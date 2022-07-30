package com.bn.flights.ui.launch.adapter

import com.bn.flights.databinding.ItemLaunchTextDetailBinding
import com.bn.flights.ui.base.BaseListAdapter

class LaunchTextDetailAdapter : BaseListAdapter<ItemLaunchTextDetailBinding, LaunchDetail.LaunchTextDetail>() {
    override val bindAction: (binding: ItemLaunchTextDetailBinding, item: LaunchDetail.LaunchTextDetail) -> Unit =
        { binding, item ->
            with(binding) {
                titleText.text = item.title
                valueText.text = item.value
            }
        }
}