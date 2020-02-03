package com.example.bubble.gliderecyclerview.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.bubble.gliderecyclerview.LOG_TAG
import com.example.bubble.gliderecyclerview.R
import com.example.bubble.gliderecyclerview.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment(){
    private lateinit var navController: NavController
    private lateinit var viewModel: SharedViewModel
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
        viewModel = ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)

        nameTv.text = viewModel.selectedMonster!!.monsterName
        descriptionTv.text = viewModel.selectedMonster!!.description
        ratingBar.rating = viewModel.selectedMonster!!.scariness.toFloat()
        priceTv.text = viewModel.selectedMonster!!.price.toString()
        Glide.with(context!!).load(viewModel.selectedMonster!!.imageUrl).fitCenter().into(monsterIv)

//        viewModel.selectedMonster.observe(this, Observer {
//            Log.i(LOG_TAG, "Observed selected monster")
//            nameTv.text = it.monsterName
//            descriptionTv.text = it.description
//            ratingBar.rating = it.scariness.toFloat()
//            priceTv.text = it.price.toString()
//            Glide.with(context!!).load(it.imageUrl).fitCenter().into(monsterIv)
//        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == android.R.id.home){
            navController.navigateUp()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}