package com.redrock.halloffame.page.app.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.redrock.halloffame.R
import com.redrock.halloffame.base.BaseBindingVmActivity
import com.redrock.halloffame.databinding.ActivityMainBinding
import com.redrock.halloffame.page.app.viewmodel.MainViewModel
import com.redrock.halloffame.page.home.ui.fragment.HomeFragment
import com.redrock.halloffame.page.search.ui.fragment.SearchFragment
import com.redrock.halloffame.page.third.ui.fragment.ThirdFragment

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2021/9/2
 */
class MainActivity : BaseBindingVmActivity<MainViewModel, ActivityMainBinding>(
    false, false
){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSlideShow()
        initBottomNav()
    }

    private fun initSlideShow() {
        val fragments = ArrayList<Fragment>(3)
        fragments.add(HomeFragment())
        fragments.add(SearchFragment())
        fragments.add(ThirdFragment())

        binding.mainSlideShow
            .setOpenNestedScroll(true)
            .setPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    binding.mainBottomNavView.menu.getItem(position).isChecked = true
                }
            })
            .setFragmentAdapter(this, fragments)
    }

    private fun initBottomNav() {
        binding.mainBottomNavView.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.homeFragment -> {
                        binding.mainSlideShow.setCurrentItem(0)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.searchFragment -> {
                        binding.mainSlideShow.setCurrentItem(1)
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.thirdFragment -> {
                        binding.mainSlideShow.setCurrentItem(2)
                        return@OnNavigationItemSelectedListener true
                }
            }
            false
        })
    }
}