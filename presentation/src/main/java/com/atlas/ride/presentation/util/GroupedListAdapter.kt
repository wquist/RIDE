package com.atlas.ride.presentation.util

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A specialized list adapter that allows "sectioned" lists to be managed. This handles the tracking
 * of header and item views, which a specified through view types as in the parent [ListAdapter].
 * The template parameter specifies the type within a single "item" row, but the parent overrides
 * are in terms of the inner [GroupedListAdapter.Row] class.
 */
abstract class GroupedListAdapter<T, H : RecyclerView.ViewHolder>(
    diff: DiffUtil.ItemCallback<T>
) : ListAdapter<GroupedListAdapter.Row<T>, H>(RowCallback<T>(diff)) {
    /**
     * Distinguish the different types of rows within the grouped list. The parent adapter maintains
     * a list of this type.
     */
    sealed class Row<out T> {
        // The ordinal values are used as the view types within the parent adapter.
        internal enum class Type {
            HEADER,
            ITEM
        }

        /** A header contains a string title created from mapped keys within [T] in [submitList]. */
        data class Header(val title: String) : Row<Nothing>()
        /** An item contains the data type specified as the template argument of the adapter. */
        data class Item<out T>(val data: T) : Row<T>()
    }

    // Provide a custom [ItemCallback] to handle the initial layer wrapped over the list items. This
    // callback will invoke the actual callback for [T] that is passed in the constructor only when
    // both old and new are [Row.Item] objects.
    private class RowCallback<T>(
        private val inner: DiffUtil.ItemCallback<T>
    ) : DiffUtil.ItemCallback<Row<T>>() {
        private fun areTheSame(old: Row<T>, new: Row<T>, compare: (T, T) -> Boolean): Boolean {
            val oldData = (old as? Row.Item<T>)?.data
            val newData = (new as? Row.Item<T>)?.data

            return if (oldData != null && newData != null)
                compare(oldData, newData)
            else if (oldData == null && newData == null)
                (old == new) // [Row.Header] is a data class and can be compared directly.
            else
                false
        }

        override fun areItemsTheSame(oldItem: Row<T>, newItem: Row<T>): Boolean {
            return areTheSame(oldItem, newItem) { old, new ->
                inner.areItemsTheSame(old, new)
            }
        }

        override fun areContentsTheSame(oldItem: Row<T>, newItem: Row<T>): Boolean {
            return areTheSame(oldItem, newItem) { old, new ->
                inner.areContentsTheSame(old, new)
            }
        }
    }

    /**
     * Update the adaptor's list, grouping by a key field extractred from [T] with [keySelector].
     * If the key type is not a string, it will be automatically converted as to the header title
     * with the [toString] method.
     */
    fun <K> submitList(list: List<T>?, keySelector: (T) -> K) {
        submitList(list, keySelector) { it.toString() }
    }

    /**
     * Update the adaptor's list like above, but with an additional method to convert the key
     * grouping value into an appropriate header row title.
     */
    fun <K> submitList(list: List<T>?, keySelector: (T) -> K, keyMapper: (K) -> String) {
        if (list == null)
            return super.submitList(null)

        CoroutineScope(Dispatchers.Default).launch {
            val groups = list.groupBy(keySelector)
            val groupedList = groups.entries.map {
                val header = Row.Header(keyMapper(it.key))
                val items = it.value.map { item -> Row.Item(item) }

                listOf(header) + items
            }

            withContext(Dispatchers.Main) {
                super.submitList(groupedList.flatten())
            }
        }
    }

    /**
     * Check if the given view type (from an overriden [ListAdapter] callback) is a header. If this
     * value is true, the item can be cast to a [Row.Header]. Otherwise, the row is an item and can
     * only be cast to a [Row.Item].
     */
    fun isHeaderViewType(viewType: Int): Boolean {
        return (viewType == Row.Type.HEADER.ordinal)
    }

    /**
     * Check if the given item position (from a [ListAdapter] callback) is a header. The casting
     * rules here are the same as for [isHeaderViewType].
     */
    fun isHeaderPosition(position: Int): Boolean {
        return (getItem(position) is Row.Header)
    }

    /**
     * Get the row type for a given position, either a header or an item. This is checked by trying
     * to cast the given list item into each of the [Row] inner classes.
     */
    final override fun getItemViewType(position: Int): Int {
        val type = if (isHeaderPosition(position))
            Row.Type.HEADER
        else
            Row.Type.ITEM

        return type.ordinal
    }
}
