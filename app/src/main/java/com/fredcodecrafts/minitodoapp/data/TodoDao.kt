package com.fredcodecrafts.minitodoapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(todo: Todo)

    @Query("SELECT * FROM todo_table ORDER BY updatedAt DESC")
    fun getAllTodos(): LiveData<List<Todo>>

    @Delete
    suspend fun delete(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Query("DELETE FROM todo_table WHERE id = :id")
    suspend fun deleteById(id: Int)
}