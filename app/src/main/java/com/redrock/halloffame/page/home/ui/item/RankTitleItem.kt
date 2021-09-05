package com.redrock.halloffame.page.home.ui.item

import com.redrock.halloffame.R
import com.redrock.halloffame.base.SimpleRvAdapter
import com.redrock.halloffame.databinding.RecyclerItemHomeRankTitleBinding
import com.redrock.halloffame.page.home.ui.bean.RankTitle

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2021/9/5
 * @time 17:54
 */
class RankTitleItem(
    map: Map<Int, RankTitle>
) : SimpleRvAdapter.DBItem<RecyclerItemHomeRankTitleBinding, RankTitle>(
    map, R.layout.recycler_item_home_rank_title
) {
    override fun onCreate(
        binding: RecyclerItemHomeRankTitleBinding,
        holder: SimpleRvAdapter.BindingVH,
        map: Map<Int, RankTitle>
    ) {
        TODO("Not yet implemented")
    }

    override fun onRefactor(
        binding: RecyclerItemHomeRankTitleBinding,
        holder: SimpleRvAdapter.BindingVH,
        position: Int,
        value: RankTitle
    ) {
        TODO("Not yet implemented")
    }
}