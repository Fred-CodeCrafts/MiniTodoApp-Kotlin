package com.fredcodecrafts.minitodoapp.data


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todo_table")
data class Todo(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String
)
