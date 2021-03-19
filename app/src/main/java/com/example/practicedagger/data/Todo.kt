package com.example.practicedagger.data

import androidx.room.*

@Entity
data class Todo(
    @PrimaryKey(autoGenerate = true) val uid: Long?,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "timeLimit") val timeLimit: String?,
    @ColumnInfo(name = "done") val done: Boolean,
) {
    val timeList
        get() = timeLimit ?: "なし"
}

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo ORDER BY uid DESC")
    suspend fun fetchAll(): List<Todo>

    @Query("SELECT * FROM Todo WHERE done = 1")
    suspend fun fetchDone(): List<Todo>

    @Query("SELECT * FROM Todo WHERE done = 0")
    suspend fun fetchNotDone(): List<Todo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo: Todo): List<Long>

    @Delete
    suspend fun delete(todo: Todo)


}