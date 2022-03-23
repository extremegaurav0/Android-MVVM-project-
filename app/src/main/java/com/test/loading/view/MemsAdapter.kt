package com.test.loading.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.loading.R
import com.test.loading.model.Memes

class MemsAdapter(var memesList: ArrayList<Memes>,var context: Context):RecyclerView.Adapter<MemsAdapter.MemesHolder>() {


    class MemesHolder(view: View):RecyclerView.ViewHolder(view){
        var memesUrl: ImageView = view.findViewById(R.id.memesUrl)
        var memesName: TextView = view.findViewById(R.id.memesName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemesHolder {
         return  MemesHolder(LayoutInflater.from(parent.context).inflate(
             R.layout.item_list,parent,false
         ))
    }

    override fun onBindViewHolder(holder: MemesHolder, position: Int) {

        holder.memesName.text = memesList[position].name
        Glide.with(context)
                .load(memesList[position].url)
                .into(holder.memesUrl)

    }

    override fun getItemCount(): Int {
        return  memesList.size
    }
}