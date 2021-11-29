package com.ulventech.appdemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ulventech.appdemo.R
import com.ulventech.appdemo.models.RepoItems
import kotlinx.android.synthetic.main.repo_item.view.*


class RepoAdapter (val repos : List<RepoItems?>?) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.repo_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(repos?.get(position))

    override fun getItemCount(): Int = repos?.size!!

    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        fun bind(repo: RepoItems?) {

            itemView.txtName.text = repo?.name
            itemView.txtUrl.text = repo?.htmlUrl

        }
    }
}