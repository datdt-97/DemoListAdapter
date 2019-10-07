package com.example.myapplication

interface MainContract {

    interface View {
        fun showData(todoList: MutableList<Todo>)

        fun updateList(todoList: MutableList<Todo>)
    }

    interface Presenter {
        fun getData(dataUrl: String): MutableList<Todo>

        fun updateList(todo: Todo)
    }
}
