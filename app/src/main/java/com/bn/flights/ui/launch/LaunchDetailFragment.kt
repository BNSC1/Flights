package com.bn.flights.ui.launch

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.bn.flights.R
import com.bn.flights.data.model.spaceX.Launch
import com.bn.flights.databinding.FragmentLaunchDetailBinding
import com.bn.flights.ui.base.NavigationFragment
import com.bn.flights.ui.base.OnItemClickListener
import com.bn.flights.ui.launch.adapter.LaunchDetail
import com.bn.flights.ui.launch.adapter.LaunchDetailSection
import com.bn.flights.ui.launch.adapter.LaunchDetailSectionAdapter
import com.bn.flights.util.TimeUtil
import com.bumptech.glide.Glide

class LaunchDetailFragment : NavigationFragment<FragmentLaunchDetailBinding>() {
    private val args: LaunchDetailFragmentArgs by navArgs()
    private lateinit var launch: Launch

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launch = args.launch

        with(binding) {
            setupBasicDetail()
            setupDetailSectionList()
        }
    }

    private fun FragmentLaunchDetailBinding.setupBasicDetail() {
        Glide.with(this@LaunchDetailFragment).load(launch.links.missionPatchSmall)
            .into(missionPatchImage)
        flightNumberText.text = launch.flightNumber.toString()
        missionNameText.text = launch.missionName
        launchTimeText.text = TimeUtil.getDateTime(launch.launchTime)
        launchSiteText.text = launch.launchSite.name
    }


    private fun FragmentLaunchDetailBinding.setupDetailSectionList() {
        detailSectionList.adapter = LaunchDetailSectionAdapter(requireContext()).apply {
            with(launch) {
                replaceItems(
                    listOf(
                        LaunchDetailSection(
                            getString(R.string.cores),
                            rocket.firstStage.cores.firstOrNull()?.serial,
                            listOf(
                                LaunchDetail.LaunchTextDetail(
                                    getString(R.string.block),
                                    tryAddTextDetail(rocket.firstStage.cores.firstOrNull()?.block)
                                ),
                                LaunchDetail.LaunchTextDetail(
                                    getString(R.string.flight),
                                    tryAddTextDetail(rocket.firstStage.cores.firstOrNull()?.flight)
                                ),
                                LaunchDetail.LaunchTextDetail(
                                    getString(R.string.reused),
                                    tryAddTextDetail(rocket.firstStage.cores.firstOrNull()?.reused)
                                ),
                                LaunchDetail.LaunchTextDetail(
                                    getString(R.string.landing),
                                    tryAddTextDetail(rocket.firstStage.cores.firstOrNull()?.landSuccess)
                                )
                            )
                        ),
                        LaunchDetailSection(
                            getString(R.string.payloads),
                            rocket.secondStage.payloads.firstOrNull()?.id,
                            listOf(
                                LaunchDetail.LaunchTextDetail(
                                    getString(R.string.nationality),
                                    tryAddTextDetail(rocket.secondStage.payloads.firstOrNull()?.nationality)
                                ),
                                LaunchDetail.LaunchTextDetail(
                                    getString(R.string.manufacturer),
                                    tryAddTextDetail(rocket.secondStage.payloads.firstOrNull()?.manufacturer)
                                ),
                                LaunchDetail.LaunchTextDetail(
                                    getString(R.string.reused),
                                    tryAddTextDetail(rocket.secondStage.payloads.firstOrNull()?.reused)
                                ),
                                LaunchDetail.LaunchTextDetail(
                                    getString(R.string.type),
                                    tryAddTextDetail(rocket.secondStage.payloads.firstOrNull()?.type)
                                )
                            )
                        ),
                        LaunchDetailSection(
                            getString(R.string.links),
                            details = listOf(
                                LaunchDetail.LaunchLinkDetail(
                                    getString(R.string.article_link),
                                    launch.links.article
                                ),
                                LaunchDetail.LaunchLinkDetail(
                                    getString(R.string.wikipedia),
                                    launch.links.wikipedia
                                ),
                                LaunchDetail.LaunchLinkDetail(
                                    getString(R.string.video_link),
                                    launch.links.video
                                )
                            )
                        )
                    )
                )
            }
            setOnLinkClickListener(object : OnItemClickListener<LaunchDetail.LaunchLinkDetail> {
                override fun onItemClick(item: LaunchDetail.LaunchLinkDetail) {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.url!!)))
                }
            })
        }
    }

    private fun tryAddTextDetail(item: Any?) = item?.let {
        if (it is String)
            it.ifBlank { getString(R.string.no_info) }
        else if (it is Boolean) {
            if (it) getString(R.string.yes)
            else getString(R.string.no)
        } else it.toString()
    } ?: getString(R.string.no_info)
}