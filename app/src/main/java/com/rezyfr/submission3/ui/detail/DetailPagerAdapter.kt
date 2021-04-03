package com.rezyfr.submission3.ui.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.rezyfr.submission3.ui.detail.follow.FollowersFragment
import com.rezyfr.submission3.ui.detail.follow.FollowingFragment

class DetailPagerAdapter(
    fm: FragmentManager,
    username: String,
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val pages = listOf(
        FollowersFragment.newInstance("followers", username),
        FollowingFragment.newInstance("following", username)
    )

    override fun getItem(position: Int): Fragment = pages[position]

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Followers"
        else -> "Following"
    }

    override fun getCount(): Int = pages.size
}