package com.redrock.halloffame.page.home.viewmodel

import androidx.lifecycle.ViewModel
import com.redrock.halloffame.network.HomeClient
import com.redrock.halloffame.utils.liveDataLaunch

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2021/9/2
 * @time 16:08
 */
class HomeViewModel : ViewModel() {

    var bannerData = liveDataLaunch { HomeClient.getBannerBean() }

    fun refreshBanner() {
        bannerData = liveDataLaunch{ HomeClient.getBannerBean() }
    }
}