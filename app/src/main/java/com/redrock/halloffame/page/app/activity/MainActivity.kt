package com.redrock.halloffame.page.app.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.redrock.halloffame.R
import com.redrock.halloffame.base.BaseActivity
import com.redrock.halloffame.base.BaseOnlyBindingActivity
import com.redrock.halloffame.databinding.ActivityMainBinding
import com.redrock.halloffame.page.home.ui.fragment.HomeFragment
import com.redrock.halloffame.page.search.ui.fragment.SearchFragment
import com.redrock.halloffame.page.third.ui.fragment.ThirdFragment

class MainActivity : BaseOnlyBindingActivity<ActivityMainBinding>(){

    override fun onCreated(savedInstanceState: Bundle?) {
        initSlideShow()
    }

    private fun initSlideShow() {
        val fragments = ArrayList<Fragment>(3)
        fragments.add(HomeFragment())
        fragments.add(SearchFragment())
        fragments.add(ThirdFragment())
    }
}