package com.kakapo.squiz.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.kakapo.squiz.R
import com.kakapo.squiz.interfaceFile.IOnRecyclerItemClickListener
import com.kakapo.squiz.model.Category

class CategoryAdapter(
        private var context: Context,
        private var data: List<Category>
) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{

        var txtCategoryName: TextView
        var cardCategory: CardView
        private lateinit var iOnRecyclerItemClickListener: IOnRecyclerItemClickListener

        fun setIOnRecyclerItemClickListener(iOnRecyclerItemClickListener: IOnRecyclerItemClickListener){
            this.iOnRecyclerItemClickListener = iOnRecyclerItemClickListener
        }

        init {
            txtCategoryName = itemView.findViewById(R.id.txt_category_name) as TextView
            cardCategory = itemView.findViewById(R.id.card_category) as CardView

            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
           iOnRecyclerItemClickListener.onClick(v, adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.layout_category_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtCategoryName.text = data[position].name
        holder.setIOnRecyclerItemClickListener(object : IOnRecyclerItemClickListener{
            override fun onClick(view: View, position: Int) {
                Toast.makeText(
                        context,
                        "Click on ${data[position].name}",
                        Toast.LENGTH_SHORT
                ).show()
            }

        })
    }

    override fun getItemCount(): Int = data.size
}