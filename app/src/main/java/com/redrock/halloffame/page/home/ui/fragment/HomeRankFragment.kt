package com.redrock.halloffame.page.home.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.redrock.halloffame.R
import com.redrock.halloffame.base.BaseFragment
import com.redrock.halloffame.base.SimpleRvAdapter
import com.redrock.halloffame.page.home.ui.bean.Person
import com.redrock.halloffame.page.app.viewmodel.MainViewModel
import com.redrock.halloffame.page.home.ui.bean.RankTitle
import com.redrock.halloffame.page.home.ui.item.RankListItem
import java.util.*
import kotlin.collections.HashMap

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2021/9/5
 * @time 16:56
 */
class HomeRankFragment : BaseFragment() {

    private val activityViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    private lateinit var mRecyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home_rank, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initObserve()
    }

    private fun initView(view: View) {
        mRecyclerView = view.findViewById(R.id.rv_fragment_home_rank)
    }

    private fun initObserve() {
        activityViewModel.stickersRankTreeSet.observeNotNull {
            resetData(it)
            if (mRecyclerView.adapter == null) { setAdapter() }
            else { refreshAdapter() }
        }
    }

    private lateinit var mRankListItem: RankListItem
    private fun setAdapter() {
        mRankListItem = RankListItem(mRankListMap)

        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = SimpleRvAdapter()
            .addItem(mRankListItem)
            .show()
        mRecyclerView.layoutAnimation =
            LayoutAnimationController(
                AnimationUtils.loadAnimation(
                    context,
                    R.anim.anim_slide_from_left_to_right_in
                )
            )
    }

    private fun refreshAdapter() {
        mRankListItem.refresh(mRankListMap)
    }

    private val mRankListMap = HashMap<Int, Person>()
    private fun resetData(treeSet: TreeSet<Person>) {
        mRankListMap.clear()
        for ((i, person) in treeSet.withIndex()) {
            mRankListMap[i] = person
        }
    }
}