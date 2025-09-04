package com.fredcodecrafts.minitodoapp.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fredcodecrafts.minitodoapp.R
import com.fredcodecrafts.minitodoapp.data.Todo

/**
 * Very simple adapter; for production use DiffUtil/ListAdapter.
 */
class TodoAdapter(
    private val onClick: (Todo) -> Unit
) : RecyclerView.Adapter<TodoAdapter.TodoVH>() {

    private val items = mutableListOf<Todo>()

    fun submit(list: List<Todo>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoVH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        return TodoVH(v, onClick)
    }

    override fun onBindViewHolder(holder: TodoVH, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class TodoVH(
        itemView: View,
        private val onClick: (Todo) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.tvTitle)
        private val desc: TextView  = itemView.findViewById(R.id.tvDesc)
        private var current: Todo? = null

        init {
            itemView.setOnClickListener { current?.let(onClick) }
        }

        fun bind(todo: Todo) {
            current = todo
            title.text = todo.title
            desc.text  = todo.description
        }
    }
}
