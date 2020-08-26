package com.atlas.ride.presentation.feature.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil

import kotlinx.android.synthetic.main.item_thing.view.*

import com.atlas.ride.domain.entity.IThing
import com.atlas.ride.presentation.R
import com.atlas.ride.presentation.util.GroupedListAdapter

class ThingsAdapter : GroupedListAdapter<IThing>(ItemCallback()) {
    class ItemCallback : DiffUtil.ItemCallback<IThing>() {
        override fun areItemsTheSame(oldItem: IThing, newItem: IThing): Boolean {
            return (oldItem == newItem)
        }

        override fun areContentsTheSame(oldItem: IThing, newItem: IThing): Boolean {
            return (oldItem.name == newItem.name && oldItem.description == newItem.description)
        }
    }

    class ItemViewHolder(view: View) : ViewHolder(view) {
        val name: TextView = view.thing_name_text
        val description: TextView = view.thing_description_text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        if (isHeaderViewType(viewType))
            return createHeaderViewHolder(parent)

        val inflater = LayoutInflater.from(parent.context)

        val view = inflater.inflate(R.layout.item_thing, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (isHeaderPosition(position))
            return bindHeaderViewHolder(holder, position)

        val item = getItem(position) as Row.Item<IThing>
        with (holder as ItemViewHolder) {
            name.text = item.data.name
            description.text = item.data.description
        }
    }
}
