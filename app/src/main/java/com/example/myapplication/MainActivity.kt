package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private val todoAdapter by lazy {
        TodoAdapter(TodoDiffCallback(), object : ItemClickListener {
            override fun onItemClick(todo: Todo) {
                presenter.updateList(todo)
            }
        })
    }

    private val presenter by lazy {
        MainPresenter(this)
    }

    private lateinit var todoList: MutableList<Todo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoList = presenter.getData("https://jsonplaceholder.typicode.com/todos")
        todoAdapter.submitList(todoList)
        recyclerView.apply {
            adapter = todoAdapter
            setHasFixedSize(true)
        }
    }

    override fun showData(todoList: MutableList<Todo>) {
        todoAdapter.submitList(todoList)
    }

    override fun updateList(todoList: MutableList<Todo>) {
        todoAdapter.submitList(todoList)
    }
}
