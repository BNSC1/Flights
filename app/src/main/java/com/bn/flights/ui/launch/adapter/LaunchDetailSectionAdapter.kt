package com.bn.flights.ui.launch.adapter

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.view.isGone
import com.bn.flights.R
import com.bn.flights.databinding.ItemLaunchDetailSectionBinding
import com.bn.flights.ktx.setGone
import com.bn.flights.ktx.setVisible
import com.bn.flights.ui.base.BaseListAdapter
import com.bn.flights.ui.base.OnItemClickListener

data class LaunchDetailSection(
    val title: String,
    val value: String? = null,
    val details: List<LaunchDetail>
)

abstract class LaunchDetail {
    abstract val title: String

    data class LaunchTextDetail(
        override val title: String,
        val value: String?
    ) : LaunchDetail()

    data class LaunchLinkDetail(
        override val title: String,
        val url: String?
    ) : LaunchDetail()
}

class LaunchDetailSectionAdapter(context: Context) :
    BaseListAdapter<ItemLaunchDetailSectionBinding, LaunchDetailSection>() {
    private var onLinkClickListener: OnItemClickListener<LaunchDetail.LaunchLinkDetail>? = null

    fun setOnLinkClickListener(listener: OnItemClickListener<LaunchDetail.LaunchLinkDetail>) =
        listener.let { onLinkClickListener = it }

    override val bindAction: (binding: ItemLaunchDetailSectionBinding, item: LaunchDetailSection) -> Unit =
        { binding, item ->
            with(binding) {
                fun toggleExpand() {
                    with(detailsLayout) {
                        if (isGone) {
                            setVisible()
                            arrowImage.setImageDrawable(getDrawable(context, R.drawable.ic_arrow_up))
                        } else {
                            setGone()
                            arrowImage.setImageDrawable(getDrawable(context, R.drawable.ic_arrow_down))
                        }
                    }
                }

                expandClickArea.setOnClickListener {
                    toggleExpand()
                }
                titleText.text = item.title
                valueText.text = item.value
                val textDetails = mutableListOf<LaunchDetail.LaunchTextDetail>()
                val linkDetails = mutableListOf<LaunchDetail.LaunchLinkDetail>()
                item.details.forEach {
                    when (it) {
                        is LaunchDetail.LaunchTextDetail -> {
                            textDetails.add(it)
                        }
                        is LaunchDetail.LaunchLinkDetail -> {
                            linkDetails.add(it)
                        }
                    }
                }
                textDetails.also {
                    if (it.isNotEmpty()) {
                        detailTextList.adapter =
                            LaunchTextDetailAdapter().apply { replaceItems(it) }
                    }
                }
                linkDetails.also {
                    if (it.isNotEmpty()) {
                        detailLinkList.adapter =
                            LaunchLinkDetailAdapter(onLinkClickListener).apply {
                                replaceItems(it)
                            }
                    }
                }
            }
        }
}