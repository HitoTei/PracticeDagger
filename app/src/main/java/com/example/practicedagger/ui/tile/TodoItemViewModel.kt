package com.example.practicedagger.ui.tile

import androidx.lifecycle.ViewModel
import com.example.practicedagger.data.Todo
import com.example.practicedagger.ui.TodoListViewModel

class TodoItemViewModel(
    val todo: Todo,
    private val todoListViewModel: TodoListViewModel
) : ViewModel() {
    fun changeDone() {
        todoListViewModel.change(
            todo.copy(done = !todo.done)
        )
    }

    fun remove() {
        todoListViewModel.delete(todo)
    }

}