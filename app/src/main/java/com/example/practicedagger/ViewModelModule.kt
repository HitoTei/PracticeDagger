package com.example.practicedagger

import com.example.practicedagger.data.TodoDao
import com.example.practicedagger.ui.TodoListViewModel
import com.example.practicedagger.ui.edit_text.EditTextViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {

    @Provides
    @Singleton
    fun todoListViewModel(dao: TodoDao): TodoListViewModel {
        return TodoListViewModel(dao)
    }

    @Provides
    fun editTextViewModel(todoListViewModel: TodoListViewModel): EditTextViewModel {
        return EditTextViewModel(todoListViewModel)
    }

}