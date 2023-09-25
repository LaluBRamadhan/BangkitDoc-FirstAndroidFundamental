package com.code.submissionawalfundamental.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.code.submissionawalfundamental.R
import com.code.submissionawalfundamental.data.response.DetailUserResponse
import com.code.submissionawalfundamental.database.FavoriteDatabase
import com.code.submissionawalfundamental.database.FavoriteUser
import com.code.submissionawalfundamental.databinding.ActivityDetailProfileBinding
import com.code.submissionawalfundamental.ui.adapter.SectionsPagerAdapter
import com.code.submissionawalfundamental.ui.viewmodel.DetailViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class DetailProfileActivity : AppCompatActivity() {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_1,
            R.string.tab_text_2
        )
        const val EXTRA_NAME = "extra"
        const val EXTRA_URL = "url"

    }

    private lateinit var binding: ActivityDetailProfileBinding
    private val detailViewModel by viewModels<DetailViewModel>()
    private var isFavorite: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_NAME)
        val avatarUrl = intent.getStringExtra(EXTRA_URL)
        val bundle = Bundle()
        bundle.putString(EXTRA_NAME,"$username")
        bundle.putString(EXTRA_URL, "$avatarUrl")

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


        updateFabIcon(binding.fab, isFavorite)
        binding.fab.setOnClickListener{
            isFavorite = !isFavorite
            if(isFavorite){
                if(username != null && avatarUrl != null){
                    val user = FavoriteUser(name = username, url = avatarUrl)
                    saveToDatabase(user)
                }
            }else{
                if(username != null){
                    deleteUser(username)
                }
            }
            updateFabIcon(binding.fab, isFavorite)
        }
    }

    @SuppressLint("SetTextI18n")
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

    fun updateFabIcon(fab: FloatingActionButton, isFavorite: Boolean){
        val iconResource = if (isFavorite) {
            R.drawable.favorite_fill_black
        } else {
            R.drawable.favorite_black
        }
        fab.setImageResource(iconResource)
    }

    private fun saveToDatabase(user: FavoriteUser){
        val userDao = FavoriteDatabase.getDatabase(this).favoriteDao()
        CoroutineScope(Dispatchers.IO).launch {
            userDao.insertUser(user)
        }
    }

    private fun deleteUser(username: String){
        val userDao = FavoriteDatabase.getDatabase(this).favoriteDao()
        CoroutineScope(Dispatchers.IO).launch {
            val user = userDao.getUserByUsername(username)
            user?.let {
                userDao.deleteUser(it)
            }
        }
    }

}