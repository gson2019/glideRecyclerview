package com.example.bubble.gliderecyclerview.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bubble.gliderecyclerview.R
import com.example.bubble.gliderecyclerview.model.Monster
import com.example.bubble.gliderecyclerview.model.MonsterGroup
import kotlinx.android.synthetic.main.monster_grid_item.view.*

class MonsterAdapter(val context: Context, val monsterGroup: MonsterGroup, val itemListener: MonsterItemListener) : RecyclerView.Adapter<MonsterAdapter.MonsterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonsterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.monster_grid_item, parent, false);
        return MonsterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return monsterGroup.size
    }

    override fun onBindViewHolder(holder: MonsterViewHolder, position: Int) {
        val monster = monsterGroup[position]
        with(holder){
            nameTx?.let {
                it.text = monster.monsterName
                it.contentDescription = monster.monsterName
            }
            Glide.with(context).load(monster.thumbnailUrl).into(monsterImg)
            ratingBar?.rating = monster.scariness.toFloat()
            holder.itemView.setOnClickListener {
                itemListener.onMonsterItemClick(monster)
            }
        }
    }

    inner class MonsterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameTx = itemView.nameText
        val monsterImg = itemView.monsterImage
        val ratingBar = itemView.ratingBar
    }

    interface MonsterItemListener{
        fun onMonsterItemClick(monster: Monster)
    }
}