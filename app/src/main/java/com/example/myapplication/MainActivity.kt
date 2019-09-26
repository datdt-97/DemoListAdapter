package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val todoAdapter by lazy {
        TodoAdapter(TodoDiffCallback(), object : ItemClickListener {
            override fun onItemClick(todo: Todo) {
                updateList(todo)
            }
        })
    }

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.getTodoList(URL("https://jsonplaceholder.typicode.com/todos"))
        viewModel.todoList.observe(this, Observer {
            todoAdapter.submitList(it)
        })
        recyclerView.apply {
            adapter = todoAdapter
            setHasFixedSize(true)
        }
    }

    private fun updateList(todo: Todo) {
        val newTodoList = viewModel.todoList.value?.filter { it.id != todo.id }?.toMutableList()
        newTodoList?.forEach { Log.d("TAGGG", it.title) }
        newTodoList?.remove(todo)
        viewModel.todoList.value = newTodoList
    }
}
