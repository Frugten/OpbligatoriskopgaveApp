package com.example.opbligatoriskopgaveapp

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class ItemsAdapter(
    private val items: List<Item>,
    private val onItemClicked: (position: Int) -> Unit
) :
    RecyclerView.Adapter<ItemsAdapter.MyViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MyViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.fragment_item, viewGroup, false)
        return MyViewHolder(view, onItemClicked)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, position: Int) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textViewStudentId.text = items[position].id.toString()
        viewHolder.textViewStudentName.text = items[position].name
    }

    class MyViewHolder(itemView: View, private val onItemClicked: (position: Int) -> Unit) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val textViewStudentId: TextView = itemView.findViewById(R.id.ItemId)
        val textViewStudentName: TextView = itemView.findViewById(R.id.ItemTitle)



        init {
            itemView.setOnClickListener(this)
        }
        class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)

        override fun onClick(view: View) {
            val position = bindingAdapterPosition
            // gradle     implementation "androidx.recyclerview:recyclerview:1.2.1"
            onItemClicked(position)
        }
    }
}