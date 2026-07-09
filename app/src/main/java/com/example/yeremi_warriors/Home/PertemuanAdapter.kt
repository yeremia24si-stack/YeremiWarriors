package com.example.yeremi_warriors.Home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yeremi_warriors.R

class PertemuanAdapter(
    private val items: List<PertemuanItem>,
    private val onItemClick: (PertemuanItem) -> Unit
) : RecyclerView.Adapter<PertemuanAdapter.PertemuanViewHolder>() {

    inner class PertemuanViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card: View = view.findViewById(R.id.cardPertemuan)
        val title: TextView = view.findViewById(R.id.title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PertemuanViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pertemuan, parent, false)
        return PertemuanViewHolder(view)
    }

    override fun onBindViewHolder(holder: PertemuanViewHolder, position: Int) {
        val item = items[position]
        holder.title.text = item.title
        holder.title.setTypeface(null, if (item.isHighlighted) android.graphics.Typeface.BOLD else android.graphics.Typeface.NORMAL)

        holder.card.background = if (item.isHighlighted) {
            androidx.core.content.ContextCompat.getDrawable(holder.itemView.context, R.drawable.bg_card_highlight)
        } else {
            holder.card.background
        }

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = items.size
}