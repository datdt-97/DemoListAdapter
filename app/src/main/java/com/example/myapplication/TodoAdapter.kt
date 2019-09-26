package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(
    diffCallback: DiffUtil.ItemCallback<Todo>,
    private val itemClickListener: ItemClickListener
): ListAdapter<Todo, TodoAdapter.TodoViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        return TodoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    inner class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bindView(todo: Todo) {
            itemView.apply {
                textUserId.text = todo.id.toString()
                textTitle.text = todo.title
                if (todo.completed) textCompleted.text = "*"
                else textCompleted.text = "x"
            }
            itemView.setOnClickListener { itemClickListener.onItemClick(todo) }
        }
    }
}

interface ItemClickListener {
    fun onItemClick(todo: Todo)
}
