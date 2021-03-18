package com.example.practicedagger.ui.edit_text

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicedagger.data.Todo
import com.example.practicedagger.ui.TodoListViewModel
import javax.inject.Inject


class EditTextViewModel @Inject constructor(
    var todoListViewModel: TodoListViewModel
) : ViewModel() {
    val text = MutableLiveData<String>().apply { value = "" }

    fun add() {

        // TODO: 2021/03/18 timeLimitを設定できるようにする
        val todo = Todo(null, text.value ?: "", null, false)

        Log.d(TAG, "add: $todo")
        todoListViewModel.insert(todo)
    }

    companion object {
        const val TAG = "EditTextViewModel"
    }

}