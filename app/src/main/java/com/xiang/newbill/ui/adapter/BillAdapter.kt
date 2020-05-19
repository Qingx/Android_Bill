package com.xiang.newbill.ui.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cn.wl.android.lib.utils.GlideApp
import com.chad.library.adapter.base.BaseViewHolder
import com.xiang.newbill.R
import com.xiang.newbill.config.BillDaoManager
import com.xiang.newbill.config.MessageEvent
import com.xiang.newbill.entity.BillEntity
import com.xiang.newbill.ui.activity.CreateActivity
import com.xiang.newbill.ui.activity.DetailActivity
import kotlinx.android.synthetic.main.dialog_bill.view.*
import kotlinx.android.synthetic.main.item_bill.view.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by Xiang on 2020/5/14 15:58
 *
 * @email Cymbidium@outlook.com
 */
class BillAdapter(private val context: Context, private val list: MutableList<BillEntity>) :
    RecyclerView.Adapter<BillAdapter.ItemViewHolder>() {

    private var billDaoManager: BillDaoManager? = null

    class ItemViewHolder(view: View) : BaseViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        billDaoManager = BillDaoManager.getInstance(context)

        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_bill, parent, false)
        return ItemViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val entity = list[position]

        when (entity.type) {
            "家电" -> GlideApp.display(R.drawable.ic_appliance_select, holder.itemView.img_type)
            "餐饮" -> GlideApp.display(R.drawable.ic_food_select, holder.itemView.img_type)
            "书籍" -> GlideApp.display(R.drawable.ic_book_select, holder.itemView.img_type)
            "衣物" -> GlideApp.display(R.drawable.ic_clothes_select, holder.itemView.img_type)
            "化妆品" -> GlideApp.display(R.drawable.ic_cosmetic_select, holder.itemView.img_type)
            "日用品" -> GlideApp.display(R.drawable.ic_daily_select, holder.itemView.img_type)
            "数码" -> GlideApp.display(R.drawable.ic_digital_select, holder.itemView.img_type)
            "饮品" -> GlideApp.display(R.drawable.ic_drink_select, holder.itemView.img_type)
            "教育" -> GlideApp.display(R.drawable.ic_education_select, holder.itemView.img_type)
            "娱乐" -> GlideApp.display(R.drawable.ic_entertainment_select, holder.itemView.img_type)
            "水果" -> GlideApp.display(R.drawable.ic_fruit_select, holder.itemView.img_type)
            "家具" -> GlideApp.display(R.drawable.ic_furniture_select, holder.itemView.img_type)
            "游戏" -> GlideApp.display(R.drawable.ic_game_select, holder.itemView.img_type)
            "礼物" -> GlideApp.display(R.drawable.ic_gift_select, holder.itemView.img_type)
            "育儿" -> GlideApp.display(R.drawable.ic_kid_select, holder.itemView.img_type)
            "恋爱" -> GlideApp.display(R.drawable.ic_love_select, holder.itemView.img_type)
            "医药" -> GlideApp.display(R.drawable.ic_medical_select, holder.itemView.img_type)
            "运动" -> GlideApp.display(R.drawable.ic_motion_select, holder.itemView.img_type)
            "电影" -> GlideApp.display(R.drawable.ic_movie_select, holder.itemView.img_type)
            "音乐" -> GlideApp.display(R.drawable.ic_music_select, holder.itemView.img_type)
            "宠物" -> GlideApp.display(R.drawable.ic_pet_select, holder.itemView.img_type)
            "房租" -> GlideApp.display(R.drawable.ic_rent_select, holder.itemView.img_type)
            "修理" -> GlideApp.display(R.drawable.ic_repair_select, holder.itemView.img_type)
            "零食" -> GlideApp.display(R.drawable.ic_snacks_select, holder.itemView.img_type)
            "旅游" -> GlideApp.display(R.drawable.ic_tourism_select, holder.itemView.img_type)
            "交通" -> GlideApp.display(R.drawable.ic_traffic_select, holder.itemView.img_type)
            else -> GlideApp.display(R.drawable.ic_default_select, holder.itemView.img_type)
        }
        holder.itemView.text_remark.text = entity.remark
        holder.itemView.text_money.text = entity.money.toString()

        /**
         * 查看详情
         */
        holder.itemView.setOnClickListener {
            DetailActivity.start(it.context, entity.time)
        }

        holder.itemView.setOnLongClickListener {
            holder.itemView.isSelected = true

            val builder = AlertDialog.Builder(it.context)
            val view = View.inflate(it.context, R.layout.dialog_bill, null)
            builder.setView(view)
            builder.setCancelable(true)
            val dialog = builder.create()
            dialog.show()

            /**
             * 查看详情
             */
            view.layout_view.setOnClickListener {
                DetailActivity.start(it.context, entity.time)
                dialog.dismiss()
            }

            /**
             * 编辑
             */
            view.layout_change.setOnClickListener {
                CreateActivity.start(it.context, "change", entity.time)
                dialog.dismiss()
            }

            /**
             * 删除
             */
            view.layout_remove.setOnClickListener {
                billDaoManager!!.deleteBill(entity.time)
                EventBus.getDefault().post(MessageEvent("updateDate"))
                dialog.dismiss()
            }

            dialog.setOnDismissListener {
                holder.itemView.isSelected = false
            }
            false
        }

    }
}