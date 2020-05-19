package com.xiang.newbill.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import cn.wl.android.lib.ui.BaseActivity
import cn.wl.android.lib.utils.Times
import com.bigkoo.pickerview.listener.OnTimeSelectListener
import com.xiang.newbill.R
import com.xiang.newbill.config.BillDaoManager
import com.xiang.newbill.config.DateFormat
import com.xiang.newbill.config.MessageEvent
import com.xiang.newbill.config.Tools
import com.xiang.newbill.entity.TimeEntity
import com.xiang.newbill.ui.adapter.TimeAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : BaseActivity(), View.OnClickListener {
    private var billDaoManager: BillDaoManager? = null
    private var time: Long = Times.current()

    override fun initViewCreated(savedInstanceState: Bundle?) {
        EventBus.getDefault().register(this)

        billDaoManager = BillDaoManager.getInstance(mActivity)
        setUI(time)

        layout_create.setOnClickListener(this)
        layout_search.setOnClickListener(this)
    }

    override fun getLayoutResource(): Any {
        return R.layout.activity_main
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.layout_create -> CreateActivity.start(mActivity, "create", null)
            R.id.layout_empty -> CreateActivity.start(mActivity, "create", null)
            R.id.layout_search -> changeDate()
        }
    }

    @SuppressLint("SetTextI18n")
    fun setUI(time: Long) {
        val date: String = DateFormat.dateToYear(time) +
                "年" + DateFormat.dateToMonth(time) + "月"
        text_search_time.text = date

        val timeList: MutableList<TimeEntity>? = billDaoManager?.findTimeList(date)

        if (timeList!!.isNotEmpty()) {
            layout_empty.visibility = View.INVISIBLE
            rv_content.visibility = View.VISIBLE

            val money: String = billDaoManager!!.findSumMoney(date).toString()
            text_money.text = "总支出：$money"

            val adapter = TimeAdapter(mActivity, timeList)

            rv_content.adapter = adapter
            rv_content.layoutManager = object : LinearLayoutManager(mActivity) {}
        } else {
            layout_empty.visibility = View.VISIBLE
            rv_content.visibility = View.INVISIBLE

            text_money.text = "总支出：0.0"

            layout_empty.setOnClickListener(this)
        }
    }

    private fun changeDate() {
        val startDate = Tools.setDate(2010, 0, 1)
        val endDate = Tools.setDate(2030, 11, 31)
        Tools.createTimePicker(
            mActivity,
            "选择月份",
            startDate,
            endDate,
            OnTimeSelectListener { date, _ ->
                if (date.time > Times.current()) {
                    Tools.myToast("选择的日期超出了预期")
                } else {
                    time = date.time
                    setUI(time)
                }
            }).show()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun updateAdapter(messageEvent: MessageEvent) {
        when (messageEvent.message) {
            "updateDate" -> setUI(time)
        }
    }
}
