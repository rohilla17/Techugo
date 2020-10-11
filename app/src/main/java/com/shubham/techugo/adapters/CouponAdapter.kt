package com.shubham.techugo.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.shubham.techugo.R
import com.shubham.techugo.model.Cupons

class CouponAdapter(val couponList: ArrayList<Cupons>) :
    RecyclerView.Adapter<CouponAdapter.CouponViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CouponViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CouponViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return couponList.size
    }

    override fun onBindViewHolder(holder: CouponViewHolder, position: Int) {
        val coupon: Cupons = couponList[position]
        holder.bind(coupon)
    }

    class CouponViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
        inflater.inflate(R.layout.coupon_item, parent, false)
    ) {
        private var couponTitle: AppCompatTextView? = null
        private var couponRedeem: AppCompatTextView? = null
        private var couponDescription: AppCompatTextView? = null

        init {
            couponTitle = itemView.findViewById(R.id.coupon_title)
            couponDescription = itemView.findViewById(R.id.coupon_description)
            couponRedeem = itemView.findViewById(R.id.redeem_coupon)
        }

        @SuppressLint("SetTextI18n")
        fun bind(cupon: Cupons) {
            couponTitle?.text = cupon.title
            couponDescription?.text = cupon.description
            couponRedeem?.text = "Redeem ${cupon.price} \u20B9 /-"

            couponRedeem?.setOnClickListener {
                Toast.makeText(itemView.context, "Redeem Clicked", Toast.LENGTH_SHORT).show()
            } ?: return
        }

    }
}
