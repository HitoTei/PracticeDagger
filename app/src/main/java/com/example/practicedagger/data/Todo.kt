package com.example.practicedagger.data

import androidx.room.*

@Entity
data class Todo(
    @PrimaryKey val uid: Int?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "timeLimit") val timeLimit: String?,
    @ColumnInfo(name = "done") val done: Boolean,
) {
    val timeList
        get() = timeLimit ?: "なし"
}

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo")
    fun fetchAll(): List<Todo>

    @Query("SELECT * FROM Todo WHERE done = 1")
    fun fetchDone(): List<Todo>

    @Query("SELECT * FROM Todo WHERE done = 0")
    fun fetchNotDone(): List<Todo>

    @Insert
    fun insertAll(vararg todo: Todo)

    @Delete
    fun delete(todo: Todo)


}