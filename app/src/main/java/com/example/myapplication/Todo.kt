package com.example.myapplication

import androidx.recyclerview.widget.DiffUtil
import org.json.JSONObject

data class Todo(
    val id: Int,
    val userId: Int,
    val title: String,
    val completed: Boolean
) {
    constructor(jsonObject: JSONObject) : this(
        jsonObject.optInt("id"),
        jsonObject.optInt("userId"),
        jsonObject.optString("title"),
        jsonObject.optBoolean("completed")
    )
}

class TodoDiffCallback : DiffUtil.ItemCallback<Todo>() {

    override fun areItemsTheSame(oldItem: Todo, newItem: Todo) = oldItem === newItem

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo) = oldItem == newItem
}
