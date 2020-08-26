package com.atlas.ride.presentation.feature.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.item_header.view.*
import kotlinx.android.synthetic.main.item_thing.view.*

import com.atlas.ride.domain.entity.IThing
import com.atlas.ride.presentation.R
import com.atlas.ride.presentation.util.GroupedListAdapter

class ThingsAdapter : GroupedListAdapter<IThing, RecyclerView.ViewHolder>(ItemCallback()) {
    class ItemCallback : DiffUtil.ItemCallback<IThing>() {
        override fun areItemsTheSame(oldItem: IThing, newItem: IThing): Boolean {
            return (oldItem == newItem)
        }

        override fun areContentsTheSame(oldItem: IThing, newItem: IThing): Boolean {
            return (oldItem.name == newItem.name && oldItem.description == newItem.description)
        }
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.header_title_text
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.thing_name_text
        val description: TextView = view.thing_description_text
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return if (isHeaderViewType(viewType)) {
            val view = inflater.inflate(R.layout.item_header, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.item_thing, parent, false)
            ItemViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isHeaderPosition(position)) {
            val header = getItem(position) as Row.Header
            with (holder as HeaderViewHolder) {
                title.text = header.title
            }
        } else {
            val item = getItem(position) as Row.Item<IThing>
            with (holder as ItemViewHolder) {
                name.text = item.data.name
                description.text = item.data.description
            }
        }
    }
}
