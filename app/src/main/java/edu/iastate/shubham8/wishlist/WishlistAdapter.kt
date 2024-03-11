package edu.iastate.shubham8.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WishlistAdapter(
    private val items: List<WishlistItem>,
    private val onItemClick: (WishlistItem) -> Unit
) : RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.text_view_item)
        val linkTextView: TextView = itemView.findViewById(R.id.text_view_link)
        val priceTextView: TextView = itemView.findViewById(R.id.text_view_price)

        init {
            nameTextView.setOnClickListener {
                onItemClick(items[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wishlist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.nameTextView.text = item.name
        holder.linkTextView.text = "Link: ${item.link}"
        holder.priceTextView.text = "Price: ${item.price}"
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
