package com.example.practicedagger.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicedagger.data.Todo
import com.example.practicedagger.data.TodoDao
import kotlinx.coroutines.launch
import javax.inject.Inject

class TodoListViewModel @Inject constructor(
    private val dao: TodoDao
) : ViewModel() {
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

    init {
        viewModelScope.launch {
            todoList.addAll(dao.fetchAll())
        }
    }

    private val _allChanged = MutableLiveData<List<Todo>>()
    val allChanged: LiveData<List<Todo>>
        get() = _allChanged

    fun insert(todo: Todo, position: Int = 0) {
        viewModelScope.launch {
            val uid = dao.insertAll(todo)[0]
            todoList.add(position, todo.copy(uid = uid))
            _insertedPosition.value = position
        }
    }

    fun delete(todo: Todo) {
        viewModelScope.launch {
            val position = getPosition(todo)
            dao.delete(todo)
            todoList.remove(todo)
            _deletedPosition.value = position
        }
    }

    fun change(todo: Todo) {
        viewModelScope.launch {
            dao.insertAll(todo)
            val position = getPosition(todo)
            todoList[position] = todo
            _changedPosition.value = position
        }
    }

    private fun getPosition(todo: Todo): Int {
        val position = todoList.indexOfFirst { it.uid == todo.uid }
        return if (position != -1) position else throw Exception("todo list has not this todo(${todo})")
    }

}