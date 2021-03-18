package com.example.practicedagger.ui.edit_text

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practicedagger.data.Todo
import com.example.practicedagger.data.TodoDao
import com.example.practicedagger.ui.TodoListViewModel
import javax.inject.Inject

class EditTextViewModel @Inject constructor() : ViewModel() {
    @Inject
    lateinit var todoListViewModel: TodoListViewModel
    @Inject
    lateinit var dao: TodoDao
    val text = MutableLiveData<String>().apply { value = "" }

    fun add() {

        // TODO: 2021/03/18 timeLimitを設定できるようにする
        val todo = Todo(null, text.value ?: "", null, false)

        Log.d(TAG, "add: $todo")

        // TODO: 2021/03/18 データベースに値を追加する処理を書く
        // dao.insertAll(todo)
        todoListViewModel.insert(todo)
    }

    companion object {
        const val TAG = "EditTextViewModel"
    }

}