package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONArray
import java.net.URL
import java.util.concurrent.Executors

class MainViewModel : ViewModel() {

    val todoList by lazy {
        MutableLiveData<MutableList<Todo>>()
    }

    fun getTodoList(url: URL) {
        val executor = Executors.newScheduledThreadPool(5)
        doAsync(executorService = executor) {
            val result = url.readText()
            uiThread {
                convertData(result)
            }
        }
    }

    private fun convertData(data: String) {
        val todos = mutableListOf<Todo>()
        val array = JSONArray(data)
        for (index in 0 until array.length()) {
            val todo = Todo(array.getJSONObject(index))
            todos.add(todo)
        }
        todoList.value = todos
    }
}
