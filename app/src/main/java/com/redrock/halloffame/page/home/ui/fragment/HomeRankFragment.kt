package com.redrock.halloffame.page.home.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.redrock.halloffame.R
import com.redrock.halloffame.base.BaseFragment
import com.redrock.halloffame.page.home.ui.bean.Person
import com.redrock.halloffame.page.app.viewmodel.MainViewModel
import com.redrock.halloffame.page.home.ui.item.RankListItem
import com.redrock.halloffame.page.home.ui.item.RankTitleItem
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
            if (mRecyclerView.adapter == null) { setAdapter(it) }
            else { refreshAdapter(it) }
        }
    }

    private lateinit var mRankTitleItem: RankTitleItem
    private lateinit var mRankListItem: RankListItem
    private fun setAdapter(treeSet: TreeSet<Person>) {
//        mRankTitleItem = RankTitleItem()
//        mRankListItem = RankListItem()
    }

    private fun refreshAdapter(treeSet: TreeSet<Person>) {
        mRankTitleItem.layoutId
    }

//    private val mRankTitleMap = HashMap<Int, >
    private fun resetData(treeSet: TreeSet<Person>) {

    }
}