package com.xiang.newbill.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.recyclerview.widget.GridLayoutManager
import cn.wl.android.lib.ui.BaseActivity
import cn.wl.android.lib.utils.Times
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.bigkoo.pickerview.view.TimePickerView
import com.xiang.newbill.R
import com.xiang.newbill.config.*
import com.xiang.newbill.entity.BillEntity
import com.xiang.newbill.entity.TypeEntity
import com.xiang.newbill.ui.adapter.TypeAdapter
import kotlinx.android.synthetic.main.activity_create.*
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import org.greenrobot.eventbus.EventBus
import java.util.*

class CreateActivity : BaseActivity(), View.OnClickListener {
    private var billDaoManager: BillDaoManager? = null
    private var jumpType: String? = ""
    private var time: Long = 0
    private var id: Long = 0
    private var date: String = ""
    private var type: String = ""
    private lateinit var adapter: TypeAdapter
    private lateinit var billEntity: BillEntity

    companion object {
        fun start(context: Context?, jumpType: String, @Nullable billId: Long?) {
            val intent = Intent(context, CreateActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("jumpType", jumpType)
            intent.putExtra("billId", billId)
            context!!.startActivity(intent)

            (context as Activity).overridePendingTransition(
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
        }
    }

    @SuppressLint("SetTextI18n")
    override fun initViewCreated(savedInstanceState: Bundle?) {
        billDaoManager = BillDaoManager.getInstance(mActivity)
        jumpType = intent?.getStringExtra("jumpType")

        val entityList: MutableList<TypeEntity>? = DataStorage.get().getList()
        var list: MutableList<TypeEntity> = mutableListOf()

        if (entityList != null) {
            list = entityList
        } else {
            Tools.setTypeList(list)
            DataStorage.get().setList(list)
        }

        when (jumpType) {
            "create" -> {
                img_calendar.visibility = View.VISIBLE
                text_time.text = "今天"
                time = Times.current()
                date = Tools.getDateString(time)

                adapter = TypeAdapter(mActivity, list, "")
            }
            "change" -> {
                val billId: Long = intent.getLongExtra("billId", 0)
                billEntity = billDaoManager!!.findDetailByTime(billId)
                id = billEntity.time
                time = billEntity.time
                date = billEntity.date
                type = billEntity.type

                edit_remark.setText(billEntity.remark)
                edit_money.setText(billEntity.money.toString())

                if (date == Tools.getDateString(Times.current())) {
                    img_calendar.visibility = View.VISIBLE
                    text_time.text = "今天"
                } else {
                    img_calendar.visibility = View.INVISIBLE
                    var s = date.substring(0, 10)
                    s = s.replace("年", "/")
                    s = s.replace("月", "/")
                    text_time.text = s
                }

                adapter = TypeAdapter(mActivity, list, type)
            }
        }

        rv_content.adapter = adapter
        rv_content.layoutManager = object : GridLayoutManager(mActivity, 4) {}

        adapter.setOnItemListener(object : TypeAdapter.OnItemListener {
            override fun onItemClick(name: String) {
                type = name
            }
        })

        OverScrollDecoratorHelper.setUpOverScroll(
            rv_content,
            OverScrollDecoratorHelper.ORIENTATION_VERTICAL
        )

        btn_commit.setOnClickListener(this)
        text_time.setOnClickListener(this)
        img_top_left.setOnClickListener(this)
    }

    override fun getLayoutResource(): Any {
        return R.layout.activity_create
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_commit -> commitBill()
            R.id.text_time -> {
                val startDate = Tools.setDate(2010, 0, 1)
                val endDate = Tools.setDate(2030, 11, 31)
                timePicker(startDate, endDate)
            }
            R.id.img_top_left -> onBackPressed()
        }
    }

    private fun isInputTrue(): Boolean {
        if (edit_remark.text.isEmpty()) {
            Tools.myToast("备注不能为空哦")
            return false
        }
        if (edit_money.text.isEmpty() || edit_money.text.toString().toDouble() == 0.0) {
            Tools.myToast("支出不能为空哦")
            return false
        }
        if (type == "") {
            Tools.myToast("请选择一种类型")
            return false
        }
        return true
    }

    private fun timePicker(startDate: Calendar, endDate: Calendar) {

        val timePicker: TimePickerView =
            object : TimePickerBuilder(mActivity,
                OnTimeSelectListener { date, _ ->
                    if (date.time > Times.current()) {
                        Tools.myToast("选择的日期超出了预期")
                    } else {
                        time = date.time
                        this.date = Tools.getDateString(date.time)
                        changeTimeText(date)
                    }
                }) {}
                .setType(booleanArrayOf(true, true, true, false, false, false))
                .setTitleText("选择时间")
                .setContentTextSize(14)
                .setSubCalSize(14)
                .setTitleSize(14)
                .setRangDate(startDate, endDate)
                .setDate(Calendar.getInstance())
                .setSubmitColor(Color.parseColor("#131313"))
                .setCancelColor(Color.parseColor("#131313"))
                .setTitleColor(Color.parseColor("#131313"))
                .setBgColor(Color.parseColor("#f4f5f7"))
                .setTitleBgColor(Color.parseColor("#f4f5f7"))
                .setTextColorCenter(Color.parseColor("#F4606C"))
                .setOutSideCancelable(true)
                .build()
        timePicker.show()
    }

    @SuppressLint("SetTextI18n")
    private fun changeTimeText(date: Date) {
        val calendar = Calendar.getInstance()
        calendar.time = date

        if (Tools.isSameDay(calendar)) {
            img_calendar.visibility = View.VISIBLE
            text_time.text = "今天"
        } else {
            img_calendar.visibility = View.INVISIBLE
            text_time.text = DateFormat.dateToYear(time) +
                    "/" + DateFormat.dateToMonth(time) +
                    "/" + DateFormat.dateToDay(time)
        }
    }

    private fun commitBill() {
        if (isInputTrue()) {
            when (jumpType) {
                "create" -> {
                    val billEntity = BillEntity(
                        time,
                        date,
                        edit_remark.text.toString(),
                        type,
                        edit_money.text.toString().toDouble()
                    )
                    billDaoManager!!.insertBill(billEntity)

                    EventBus.getDefault().post(MessageEvent("updateDate"))
                    onBackPressed()
                }
                "change" -> {
                    billDaoManager!!.updateBill(
                        id,
                        date,
                        edit_remark.text.toString(),
                        type,
                        edit_money.text.toString().toDouble()
                    )

                    EventBus.getDefault().post(MessageEvent("updateDate"))
                    EventBus.getDefault().post(MessageEvent("changeDetail"))
                    onBackPressed()
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(
            android.R.anim.fade_in,
            android.R.anim.fade_out
        )
    }
}
