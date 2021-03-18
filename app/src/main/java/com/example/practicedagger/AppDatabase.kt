package com.example.practicedagger

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.practicedagger.data.Todo
import com.example.practicedagger.data.TodoDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Database(entities = [Todo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}

@Module
class RoomModule(application: Application) {
    private val database: AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, "todo-database").build()

    @Singleton
    @Provides
    fun provideAppDatabase() = database

    @Singleton
    @Provides
    fun provideTodoDao() = database.todoDao()

}