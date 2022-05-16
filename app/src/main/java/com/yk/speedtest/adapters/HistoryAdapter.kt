package com.inn.sitemanagement.sitedashboard.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.speedchecker.android.sdk.speedtest.R
import com.speedchecker.android.sdk.speedtest.databinding.HistoryItemLayoutBinding
import com.yk.speedtest.models.TestHistory
import com.yk.speedtest.util.AppUtil
import java.text.DecimalFormat

class HistoryAdapter(
    private var list: MutableList<TestHistory>,
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: HistoryItemLayoutBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            HistoryItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                val history = list[position]
                binding.llParent.visibility = View.VISIBLE
                binding.nivDetail.setImageResource(if (expand) R.drawable.ic_up_arrow else R.drawable.ic_down_arrow)
                val decimalFormat = DecimalFormat("#.##")
                binding.tvAvgDlSpeed.text = decimalFormat.format(history.downloadSpeed)
                binding.tvAvgUlSpeed.text = decimalFormat.format(history.uploadSpeed)
                binding.tvPing.text = history.ping.toString()
                binding.tvPacket.text = decimalFormat.format(history.accuracy)
                binding.tvNetworkInfo.text = history.connectionTypeHuman
                binding.tvServerInfo.text = history.serverInfo
                AppUtil.setTimeOnTextView(context, history.dateInMillis, binding.tvTime)
                binding.tvLocationInfo.text = AppUtil.getAddress(
                    context,
                    history.getLatitude().toDouble(),
                    history.getLongitude().toDouble()
                )
                binding.llViewMore.visibility = if (this.expand) View.VISIBLE else View.GONE
                binding.nivDetail.setOnClickListener {
                    this.expand = !this.expand
                    notifyDataSetChanged()
                }
            }
            binding.executePendingBindings()
        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    fun setList(list: MutableList<TestHistory>) {
        this.list = list
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}