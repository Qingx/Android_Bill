package com.xiang.newbill.ui.activity

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import cn.wl.android.lib.ui.BaseActivity
import cn.wl.android.lib.utils.GlideApp
import com.xiang.newbill.R
import com.xiang.newbill.config.BillDaoManager
import com.xiang.newbill.config.MessageEvent
import com.xiang.newbill.entity.BillEntity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.dialog_delete_notice.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class DetailActivity : BaseActivity(), View.OnClickListener {
    private var billDaoManager: BillDaoManager? = null
    private lateinit var entity: BillEntity

    companion object {
        fun start(context: Context?, time: Long) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("id", time)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context!!.startActivity(intent)
        }
    }

    override fun initViewCreated(savedInstanceState: Bundle?) {
        EventBus.getDefault().register(this)
        billDaoManager = BillDaoManager.getInstance(mActivity)

        val time: Long = intent.getLongExtra("id", 0)
        entity = billDaoManager!!.findDetailByTime(time)

        setUI()

        img_top_left.setOnClickListener(this)
        layout_change.setOnClickListener(this)
        layout_remove.setOnClickListener(this)
    }

    override fun getLayoutResource(): Any {
        return R.layout.activity_detail
    }

    private fun setUI() {
        text_type.text = entity.type
        text_money.text = entity.money.toString()
        text_date.text = entity.date
        text_remark.text = entity.remark

        when (entity.type) {
            "家电" -> GlideApp.display(R.drawable.ic_appliance_select, img_type)
            "餐饮" -> GlideApp.display(R.drawable.ic_food_select, img_type)
            "书籍" -> GlideApp.display(R.drawable.ic_book_select, img_type)
            "衣物" -> GlideApp.display(R.drawable.ic_clothes_select, img_type)
            "化妆品" -> GlideApp.display(R.drawable.ic_cosmetic_select, img_type)
            "日用品" -> GlideApp.display(R.drawable.ic_daily_select, img_type)
            "数码" -> GlideApp.display(R.drawable.ic_digital_select, img_type)
            "饮品" -> GlideApp.display(R.drawable.ic_drink_select, img_type)
            "教育" -> GlideApp.display(R.drawable.ic_education_select, img_type)
            "娱乐" -> GlideApp.display(R.drawable.ic_entertainment_select, img_type)
            "水果" -> GlideApp.display(R.drawable.ic_fruit_select, img_type)
            "家具" -> GlideApp.display(R.drawable.ic_furniture_select, img_type)
            "游戏" -> GlideApp.display(R.drawable.ic_game_select, img_type)
            "礼物" -> GlideApp.display(R.drawable.ic_gift_select, img_type)
            "育儿" -> GlideApp.display(R.drawable.ic_kid_select, img_type)
            "恋爱" -> GlideApp.display(R.drawable.ic_love_select, img_type)
            "医药" -> GlideApp.display(R.drawable.ic_medical_select, img_type)
            "运动" -> GlideApp.display(R.drawable.ic_motion_select, img_type)
            "电影" -> GlideApp.display(R.drawable.ic_movie_select, img_type)
            "音乐" -> GlideApp.display(R.drawable.ic_music_select, img_type)
            "宠物" -> GlideApp.display(R.drawable.ic_pet_select, img_type)
            "房租" -> GlideApp.display(R.drawable.ic_rent_select, img_type)
            "修理" -> GlideApp.display(R.drawable.ic_repair_select, img_type)
            "零食" -> GlideApp.display(R.drawable.ic_snacks_select, img_type)
            "旅游" -> GlideApp.display(R.drawable.ic_tourism_select, img_type)
            "交通" -> GlideApp.display(R.drawable.ic_traffic_select, img_type)
            else -> GlideApp.display(R.drawable.ic_default_select, img_type)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun updateData(messageEvent: MessageEvent) {
        when (messageEvent.message) {
            "changeDetail" -> setUI()
        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.img_top_left -> onBackPressed()
            R.id.layout_change -> CreateActivity.start(mActivity, "change", entity.time)
            R.id.layout_remove -> createDeleteDialog()
        }
    }

    private fun createDeleteDialog() {
        val builder = AlertDialog.Builder(mActivity)
        val view = View.inflate(mActivity, R.layout.dialog_delete_notice, null)
        builder.setView(view)
        builder.setCancelable(true)
        val dialog = builder.create()
        dialog.show()

        view.text_title.text = "确认删除"
        view.text_notice.text = "删除后数据不可恢复！"
        view.text_remove.text = "删除"
        view.text_cancel.text = "取消"

        view.layout_remove.setOnClickListener {
            billDaoManager?.deleteBill(entity.time)
            EventBus.getDefault().post(MessageEvent("updateDate"))
            dialog.dismiss()
            onBackPressed()
        }

        view.layout_cancel.setOnClickListener {
            dialog.dismiss()
        }
    }
}
