package com.redrock.halloffame.page.home.ui.item

import androidx.recyclerview.widget.GridLayoutManager
import com.redrock.halloffame.R
import com.redrock.halloffame.base.SimpleRvAdapter
import com.redrock.halloffame.page.home.ui.bean.Person
import com.redrock.halloffame.databinding.RecyclerItemHomeRankListBinding
import com.redrock.halloffame.page.home.ui.adapter.StickerRvAdapter
import com.redrock.halloffame.utils.imgFromUr

/**
 * ...
 * @author 985892345 (Guo Xiangrui)
 * @email 2767465918@qq.com
 * @date 2021/9/5
 * @time 17:59
 */
class RankListItem(
    map: Map<Int, Person>
) : SimpleRvAdapter.DBItem<RecyclerItemHomeRankListBinding, Person>(
    map, R.layout.recycler_item_home_rank_list
) {

    fun refresh(map: Map<Int, Person>) {
        diffRefreshAllItemMap(map,
            isSameName = { oldData, newData ->
                oldData.name == newData.name
            },
            isSameData = { oldData, newData ->
                oldData == newData
            }
        )
    }

    override fun onCreate(
        binding: RecyclerItemHomeRankListBinding,
        holder: SimpleRvAdapter.BindingVH,
        map: Map<Int, Person>
    ) {
        val rvPhoto = binding.rvRecyclerItemHomeRankListStickers
        rvPhoto.layoutManager = GridLayoutManager(rvPhoto.context, 2)
        rvPhoto.adapter = StickerRvAdapter(listOf())
    }

    override fun onRefactor(
        binding: RecyclerItemHomeRankListBinding,
        holder: SimpleRvAdapter.BindingVH,
        position: Int,
        value: Person
    ) {
        binding.bean = value
        binding.ivRecyclerItemHomeRankListHead
            .imgFromUr("https://q4.qlogo.cn/g?b=qq&nk=${value.avatar}&s=640") // 必须要 640 才行
        binding.tvRecyclerItemHomeRankListRanking
            .setTextColor(
                when (position) {
                    0 -> 0xFFBA1111
                    1 -> 0xFFFFC107
                    2 -> 0xFF4CAF50
                    else -> 0xFFAEAEAE
                }.toInt()
            )
        binding.tvRecyclerItemHomeRankListRanking
            .text = (position + 1).toString()
        val adapter = binding.rvRecyclerItemHomeRankListStickers.adapter
        if (adapter is StickerRvAdapter) {
            adapter.refresh(value.stickers)
        }
    }
}