package com.example.myapplication

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONArray
import java.net.URL
import java.util.concurrent.Executors

class MainPresenter(private val view: MainContract.View) : MainContract.Presenter {

    private var todoList = mutableListOf<Todo>()

    override fun getData(dataUrl: String): MutableList<Todo> {
        val executor = Executors.newScheduledThreadPool(5)
        doAsync(executorService = executor) {
            val result = URL(dataUrl).readText()
            uiThread {
                todoList = convertData(result)
                view.showData(todoList)
            }
        }
        return todoList
    }

    override fun updateList(todo: Todo) {
        todoList = todoList.filter { it.id != todo.id }.toMutableList()
        todoList.remove(todo)
        view.updateList(todoList)
    }

    private fun convertData(data: String): MutableList<Todo> {
        val todos = mutableListOf<Todo>()
        val array = JSONArray(data)
        for (index in 0 until array.length()) {
            val todo = Todo(array.getJSONObject(index))
            todos.add(todo)
        }
        return todos
    }
}
