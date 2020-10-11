package com.shubham.techugo.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.shubham.techugo.adapters.ImageCarouselAdapter
import com.shubham.techugo.R
import com.shubham.techugo.adapters.CouponAdapter
import com.shubham.techugo.model.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_description.*
import kotlinx.android.synthetic.main.fragment_offers.*
import android.content.Intent
import android.app.AlertDialog
import android.net.Uri
import android.widget.Toast


/**
 * A simple [Fragment] subclass.
 */
class TechugoFragment : Fragment() {
    private lateinit var result: Result
    private lateinit var adapter: CouponAdapter

    companion object {
        var position: Int = 0
        const val argumentName = "result"

        fun newInstance(position: Int, result: Result): TechugoFragment {
            val fragment = TechugoFragment()
            val bundle = Bundle().apply {
                putParcelable(argumentName, result)
            }
            fragment.arguments = bundle
            this.position = position
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        result = arguments?.getParcelable(argumentName) ?: return null
        return if (position == 0) {
            inflater.inflate(R.layout.fragment_offers, container, false)
        } else inflater.inflate(R.layout.fragment_description, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUI(position)
    }

    fun setUI(position: Int) {

        when (position) {

            0 -> {
                setImageViewPagerAndIndicator()
                setCouponsAdapter()
                setOffersUI()
            }

            1 -> {
                setDescriptionUI()
            }

        }

    }

    private fun setOffersUI() {
        map.setOnClickListener {
            AlertDialog.Builder(context).setTitle("Do you want to open this location in map?")
                .setPositiveButton("OK") { dialog, which ->
                    val strUri =
                        "http://maps.google.com/maps?q=loc:${result.latitudes},${result.longitude} (Label which you want)"
                    val intent = Intent(android.content.Intent.ACTION_VIEW, Uri.parse(strUri))

                    intent.setClassName(
                        "com.google.android.apps.maps",
                        "com.google.android.maps.MapsActivity"
                    )

                    startActivity(intent)
                }.setNegativeButton("NO") { dialog, which ->
                    dialog.dismiss()
                }.show()
        }

        call.setOnClickListener {
            Toast.makeText(context, "Call pressed", Toast.LENGTH_SHORT).show()
        }

        menu.setOnClickListener {
            Toast.makeText(context, "Menu pressed", Toast.LENGTH_SHORT).show()
        }

    }

    private fun setCouponsAdapter() {
        adapter = CouponAdapter(result.cupons)
        coupons_list.layoutManager = LinearLayoutManager(activity)
        coupons_list.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun setDescriptionUI() {
        Picasso.get().load(result.decription_image).into(description_image)
        description_heading.text = result.description_title
        description.text = result.description_body
    }

    private fun setImageViewPagerAndIndicator() {
        val adapter = ImageCarouselAdapter(result.banner)
        image_view_pager.adapter = adapter
        TabLayoutMediator(image_tab_layout, image_view_pager) { tab, position -> }.attach()
    }

}
