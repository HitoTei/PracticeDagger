package com.example.practicedagger

import android.app.Application
import com.example.practicedagger.data.TodoDao
import com.example.practicedagger.ui.TodoListViewModel
import com.example.practicedagger.ui.edit_text.EditTextViewModel
import dagger.Component
import javax.inject.Singleton

@Component(modules = [RoomModule::class])
@Singleton
interface ApplicationComponent {
    fun inject(application: Application)
    fun appDatabase(): AppDatabase
    fun todoDao(): TodoDao
    fun todoListViewModel(): TodoListViewModel
    fun editTextViewModel(): EditTextViewModel
}

class MyApplication : Application() {
    lateinit var appComponent: ApplicationComponent
    override fun onCreate() {
        appComponent = DaggerApplicationComponent.builder().roomModule(RoomModule(this)).build()
        appComponent.inject(this)
        super.onCreate()
    }
}