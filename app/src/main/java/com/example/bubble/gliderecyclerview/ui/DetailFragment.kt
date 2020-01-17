package com.example.bubble.gliderecyclerview.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.bubble.gliderecyclerview.R
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(){
    val monster by lazy {
        DetailFragmentArgs.fromBundle(
            arguments!!
        ).monster
    }
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity() as AppCompatActivity).run{
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        setHasOptionsMenu(true)
       val view = inflater.inflate(R.layout.fragment_detail, container, false)
        navController = findNavController()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nameTv.text = monster.monsterName
        descriptionTv.text = monster.description
        Glide.with(context!!).load(monster.imageUrl).fitCenter().into(monsterIv)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == android.R.id.home){
            navController.navigateUp()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}