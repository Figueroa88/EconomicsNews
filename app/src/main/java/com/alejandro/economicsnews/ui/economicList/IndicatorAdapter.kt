package com.alejandro.economicsnews.ui.economicList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alejandro.economicsnews.core.BaseViewHolder
import com.alejandro.economicsnews.core.toNumberStringFormat
import com.alejandro.economicsnews.data.model.entity.IndicatorEntity
import com.alejandro.economicsnews.databinding.ItemRowIndicatorBinding

class IndicatorAdapter(private val mIndicatorList: List<IndicatorEntity>, private val mRowListener: OnIndicatorClickListener) : RecyclerView.Adapter<BaseViewHolder<*>>()
{
    //Override

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*>
    {
        val itemBinding = ItemRowIndicatorBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return IndicatorViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int)
    {
        when (holder)
        {
            is IndicatorViewHolder -> holder.bind(mIndicatorList[position])
        }
    }

    override fun getItemCount(): Int = mIndicatorList.size

    //ViewHolder

    private inner class IndicatorViewHolder(val mBinding: ItemRowIndicatorBinding) : BaseViewHolder<IndicatorEntity>(mBinding.root)
    {
        override fun bind(itemView: IndicatorEntity)
        {
            mBinding.tvRowIndicatorName.text = itemView.name
            mBinding.tvRowIndicatorValue.text = "$ ${itemView.value.toNumberStringFormat()}"

            mBinding.llRowContent.setOnClickListener {

                mRowListener.onIndicatorClick(itemView)
            }
        }
    }

    //Interface

    interface OnIndicatorClickListener
    {
        fun onIndicatorClick(indicator: IndicatorEntity)
    }
}