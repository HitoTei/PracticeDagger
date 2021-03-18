package com.example.practicedagger.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicedagger.data.Todo
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoListViewModel @Inject constructor() : ViewModel() {
    var todoList: MutableList<Todo> = mutableListOf()
    private val _deletedPosition = MutableLiveData<Int>()
    val deletedPosition: LiveData<Int>
        get() = _deletedPosition
    private val _changedPosition = MutableLiveData<Int>()
    val changedPosition: LiveData<Int>
        get() = _changedPosition
    private val _insertedPosition = MutableLiveData<Int>()
    val insertedPosition: LiveData<Int>
        get() = _insertedPosition

    val allChanged = MutableLiveData<List<Todo>>()

    fun insert(todo: Todo, position: Int = todoList.size) {
        todoList.add(position, todo)

        _insertedPosition.value = position
    }

    fun delete(todo: Todo) {
        val position = getPosition(todo)
        _deletedPosition.value = position
    }

    fun change(todo: Todo) {
        val position = getPosition(todo)
        todoList[position] = todo
        _changedPosition.value = position
    }

    private fun getPosition(todo: Todo): Int {
        val position = todoList.indexOf(todo)
        return if (position != -1) position else throw Exception("todo list has not this todo(${todo})")
    }

}