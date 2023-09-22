package com.code.submissionawalfundamental.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.code.submissionawalfundamental.R
import com.code.submissionawalfundamental.data.response.ItemsItem
import com.code.submissionawalfundamental.databinding.ActivityMainBinding
import com.code.submissionawalfundamental.ui.FavoriteActivity
import com.code.submissionawalfundamental.ui.adapter.GithubAdapter
import com.code.submissionawalfundamental.ui.viewmodel.DetailViewModel
import com.code.submissionawalfundamental.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel by viewModels<MainViewModel>()
    private val detailViewModel by viewModels<DetailViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val layoutManager = LinearLayoutManager(this)
        binding.rvItem.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvItem.addItemDecoration(itemDecoration)

        binding.searchBar.inflateMenu(R.menu.menu)
        binding.searchBar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId){
                R.id.menu1 -> {
                    val intents = Intent(this@MainActivity, FavoriteActivity::class.java)
                    startActivity(intents)
                    true
                }
                R.id.menu2 -> {
                    val intents = Intent(this@MainActivity, SettingActivity::class.java)
                    startActivity(intents)
                    true
                }
                else -> false
            }

        }

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView
                .editText
                .setOnEditorActionListener { _,_,_ ->
                    val searchBar = searchView.text
                    searchView.hide()
                    mainViewModel.usernameGithub(searchBar.toString())
                    detailViewModel.githubDetail(searchBar.toString())
                    Toast.makeText(this@MainActivity, searchView.text, Toast.LENGTH_SHORT).show()
                    false
                }
        }

        mainViewModel.githubResponse.observe(this){githubuser->
            setDataGithub(githubuser)
        }

        mainViewModel.isLoading.observe(this){
            showLoading(it)
        }

    }

    fun setDataGithub(user: List<ItemsItem>){
        val adapter = GithubAdapter()
        adapter.submit(user)
        binding.rvItem.adapter = adapter
    }

    private fun showLoading(state: Boolean) { binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE }
}
