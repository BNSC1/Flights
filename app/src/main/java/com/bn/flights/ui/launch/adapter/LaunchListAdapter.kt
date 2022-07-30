package com.bn.flights.ui.launch.adapter

import androidx.recyclerview.widget.DiffUtil
import com.bn.flights.data.model.spaceX.Launch
import com.bn.flights.databinding.ItemLaunchInfoBinding
import com.bn.flights.ui.base.CustomPagingDataAdapter
import com.bn.flights.ui.base.OnItemClickListener
import com.bn.flights.util.TimeUtil
import com.bumptech.glide.Glide
import timber.log.Timber

class LaunchListAdapter(private val clickListener: OnItemClickListener<Launch>) :
    CustomPagingDataAdapter<ItemLaunchInfoBinding, Launch>(
        object : DiffUtil.ItemCallback<Launch>() {
            override fun areItemsTheSame(oldItem: Launch, newItem: Launch) =
                oldItem.missionName == newItem.missionName

            override fun areContentsTheSame(oldItem: Launch, newItem: Launch) =
                oldItem == newItem

        }) {

    override val bindAction: (binding: ItemLaunchInfoBinding, item: Launch) -> Unit =
        { binding, item ->
            with(binding) {
                flightNumberText.text = item.flightNumber.toString()
                missionNameText.text = item.missionName
                launchTimeText.text =
                    "${TimeUtil.getDateTime(item.launchTime)} ${TimeUtil.getTimeZoneOffset()}"

                item.links.missionPatchSmall?.let {
                    Timber.d("loading ${item.missionName} with image $it")
                    Glide.with(root)
                        .load(it)
                        .into(missionPatchImage)
                } ?: let {
                    Timber.d("no image for ${item.missionName}")
                    Glide.with(root).clear(missionPatchImage)
                }
                root.setOnClickListener { clickListener.onItemClick(item) }
            }
        }
}