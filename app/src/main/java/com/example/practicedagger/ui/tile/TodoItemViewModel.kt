package com.example.practicedagger.ui.tile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicedagger.data.Todo
import com.example.practicedagger.ui.TodoListViewModel

class TodoItemViewModel(
    todo_item: Todo,
    var todoListViewModel: TodoListViewModel
) : ViewModel() {

    private val _todo = MutableLiveData<Todo>()
    val todo: LiveData<Todo>
        get() = _todo

    init {
        _todo.value = todo_item
    }

    fun onCheckClicked() {
        val item = todo.value ?: throw NullPointerException("TodoItemViewModel has no data")
        _todo.value = item.copy(done = !item.done)
        todoListViewModel.change(item)
    }

}