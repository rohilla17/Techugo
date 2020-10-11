package com.shubham.techugo.adapters

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.shubham.techugo.activities.MainActivity
import com.shubham.techugo.fragments.TechugoFragment
import com.shubham.techugo.model.Result

class ViewPagerAdapter(activity: AppCompatActivity, private val itemsCount : Int, private val result : Result) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {
        return TechugoFragment.newInstance(position, result)
    }

}