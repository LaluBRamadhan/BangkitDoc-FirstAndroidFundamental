package com.code.submissionawalfundamental.ui.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.code.submissionawalfundamental.R
import com.code.submissionawalfundamental.data.response.DetailUserResponse
import com.code.submissionawalfundamental.databinding.ActivityDetailProfileBinding
import com.code.submissionawalfundamental.ui.adapter.SectionsPagerAdapter
import com.code.submissionawalfundamental.ui.viewmodel.DetailViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class DetailProfileActivity : AppCompatActivity() {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
        const val EXTRA_NAME = "extra"

    }

    private lateinit var binding: ActivityDetailProfileBinding
    private val detailViewModel by viewModels<DetailViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_NAME)
        val bundle = Bundle()
        bundle.putString(EXTRA_NAME,"$username")

        if (username != null) {
            detailViewModel.githubDetail(username)
        }

        detailViewModel.githubDetail.observe(this){githubDetail->
            setDetailData(githubDetail)
        }

        detailViewModel.isLoading.observe(this){
            showLoading(it)
        }

        val sectionPagerAdapter = SectionsPagerAdapter(this@DetailProfileActivity, bundle)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f

    }

    private fun setDetailData(githubDetail: DetailUserResponse){
        binding.apply {
            tvDetailUsername.text = githubDetail.login
            tvDetailName.text = githubDetail.name
            tvfollower.text = "${githubDetail.followers.toString()} Followers "
            tvfollowing.text = "${githubDetail.following.toString()} Following"
            Glide.with(this@DetailProfileActivity)
                .load(githubDetail.avatarUrl)
                .into(binding.imgProfileDetail)
        }
    }
    private fun showLoading(state: Boolean) { binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE }

}