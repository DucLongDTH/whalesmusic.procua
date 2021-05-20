package com.devlongdth.musicapp.ui.categoryfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devlongdth.musicapp.R
import com.devlongdth.musicapp.databinding.FragmentCategoryBinding
import com.devlongdth.musicapp.ui.base.BaseFragment
import com.devlongdth.musicapp.ui.custom.ThemeActive
import com.devlongdth.musicapp.ui.main.MainActivity

class CategoryFragment : BaseFragment() {
    private var binding: FragmentCategoryBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       if(ThemeActive.checkTheme()){
           var img_1 = context!!.resources.getDrawable(R.drawable.round_stars_black_48dp)
           img_1.setBounds(10,0,100,90)
           binding!!.cate1.setCompoundDrawables(img_1,null,null,null)
           binding!!.cate2.setCompoundDrawables(img_1,null,null,null)
       }else{
           var img_1 = context!!.resources.getDrawable(R.drawable.round_star_rate_white_48dp)
           img_1.setBounds(10,0,100,90)
           binding!!.cate1.setCompoundDrawables(img_1,null,null,null)
           binding!!.cate2.setCompoundDrawables(img_1,null,null,null)
       }
    }
}