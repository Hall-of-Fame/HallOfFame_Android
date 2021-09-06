package com.redrock.halloffame.page.home.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
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
        initPage()
        initTabLayout()
        initRefreshLayout()
    }

    private fun initObserve() {
        viewModel.bannerData.observeNotNull {
            refreshBanner(it)
        }
    }

    private fun refreshBanner(bean: BannerBean) {
        val ssBanner = binding.slideShowFragmentHomeBanner
        if (!ssBanner.hasBeenSetAdapter()) {
            ssBanner
                .addTransformer(ScaleInTransformer())
                .setAutoSlideEnabled(true)
                .setDelayTime(6000L)
                .setImgAdapter(bean.data,
                    create = { holder, beans ->
                        holder.view.setOnClickListener {
                            val position = holder.layoutPosition
                            val options =
                                ActivityOptionsCompat.makeSceneTransitionAnimation(
                                    requireActivity(), Pair(ssBanner, "bannerImg")
                                )

                            val list = ArrayList<String>(beans.size)
                            for (i in list.indices) { list.add(beans[i].url) }
                            PhotoActivity.activityStart(
                                requireContext(), list,
                                // 因为开启了循环滑动, 所以必须使用 getRealPosition() 得到你所看到的位置
                                ssBanner.getRealPosition(position),
                                options.toBundle()
                            )
                        }
                    },
                    refactor = { data, imageView, _, _ ->
                        imageView.imgFromUr(data.url)
                    }
                )
        }else {
            ssBanner.notifyImgDataChange(bean.data)
        }
    }
    
    private fun initPage() {
        val ssPage = binding.slideShowFragmentHomePage
        ssPage
            .setTransformer(ScaleInTransformer())
            .setFragmentAdapter(
                requireActivity(),
                listOf(
                    HomeRankFragment(),
                    HomeNullFragment()
                )
            )
    }

    private fun initTabLayout() {
        val tabLayout = binding.tlFragmentHome
        val tabs = arrayOf(
            "排名",
            "暂无"
        )
        TabLayoutMediator(
            tabLayout, binding.slideShowFragmentHomePage.getViewPager2()
        ) { tab, position -> tab.text = tabs[position] }.attach()
    }

    private fun initRefreshLayout() {
        val refreshLayout = binding.refreshLayoutFragmentHome
        /*
        * 官方刷新控件不能修改偏移的误差值, 在左右滑动时与 ViewPager2 出现滑动冲突问题
        * 修改 mTouchSlop 可以修改允许的滑动偏移值, 原因可以看 SwipeRefreshLayout 的 1081 行
        * */
        try {
            val field = refreshLayout.javaClass.getDeclaredField("mTouchSlop")
            field.isAccessible = true
            field.set(refreshLayout, 220)
        }catch (e: Exception) { }

        // 下面这个 setOnChildScrollUpCallback() 返回 false 就代表刷新控件可以拦截滑动
        refreshLayout.setOnChildScrollUpCallback { _, _ -> !binding.slideLayoutFragmentHome.isUnfold() }
        refreshLayout.setOnRefreshListener { activityViewModel.refreshDepartmentBean() }
        refreshLayout.isRefreshing = true // 默认开始加载时打开刷新动画
        activityViewModel.departmentBean.observeNotNull {
            binding.refreshLayoutFragmentHome.isRefreshing = false
        }
    }
}