package com.example.practicedagger

import android.app.Application
import com.example.practicedagger.data.TodoDao
import dagger.Component
import javax.inject.Singleton

@Component(modules = [RoomModule::class])
@Singleton
interface ApplicationComponent {
    fun inject(application: Application)
    fun appDatabase(): AppDatabase
    fun todoDao(): TodoDao
}

class MyApplication : Application() {
    val appComponent: ApplicationComponent = DaggerApplicationComponent.create()
    override fun onCreate() {
        appComponent.inject(this)
        super.onCreate()
    }
}