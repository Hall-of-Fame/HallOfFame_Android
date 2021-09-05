package com.redrock.halloffame.page.home.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.ViewModelProvider
import com.ndhzs.slideshow.viewpager2.transformer.ScaleInTransformer
import com.redrock.halloffame.base.BaseBindingVmFragment
import com.redrock.halloffame.bean.BannerBean
import com.redrock.halloffame.databinding.FragmentHomeBinding
import com.redrock.halloffame.page.app.viewmodel.MainViewModel
import com.redrock.halloffame.page.home.viewmodel.HomeViewModel
import com.redrock.halloffame.ui.activity.PhotoActivity
import com.redrock.halloffame.utils.imgFromUr

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2021/9/2
 * @time 16:06
 */
class HomeFragment : BaseBindingVmFragment<HomeViewModel, FragmentHomeBinding>() {

    private val activityViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initObserve()
    }

    private fun initObserve() {
        viewModel.bannerData.observeNotNull {
            refreshBanner(it)
        }
        activityViewModel.stickersRankTreeSet.observeNotNull {
            refreshStickersRanking()
        }
    }

    private fun refreshBanner(bean: BannerBean) {
        val ssBanner = binding.slideShowFragmentHomeBanner
        if (!ssBanner.hasBeenSetAdapter()) {
            ssBanner
                .addTransformer(ScaleInTransformer())
                .setAutoSlideEnabled(true)
                .setImgAdapter(bean.data,
                    create = { holder ->
                        holder.view.setOnClickListener {
                            val position = holder.layoutPosition
                            val options =
                                ActivityOptionsCompat.makeSceneTransitionAnimation(
                                    requireActivity(), Pair(ssBanner, "bannerImg")
                                )

                            val list = ArrayList<String>(bean.data.size)
                            for (i in list.indices) { list.add(bean.data[i].url) }
                            PhotoActivity.activityStart(
                                requireContext(), list,
                                // 因为开启了循环滑动, 所以必须使用 getRealPosition() 得到你所看到的位置
                                ssBanner.getRealPosition(position),
                                options.toBundle()
                            )
                        }

                    },
                    refactor = { data, imageView, holder, position ->
                        imageView.imgFromUr(data.url)
                    }
                )
        }else {
            ssBanner.notifyImgDataChange(bean.data)
        }
    }
    
    private fun refreshStickersRanking() {
        val ssPage = binding.slideShowFragmentHomePage
    }
}