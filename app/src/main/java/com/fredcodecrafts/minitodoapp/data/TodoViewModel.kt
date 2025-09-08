package com.fredcodecrafts.minitodoapp.data
import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TodoRepository
    val allTodos: LiveData<List<Todo>>

    // MutableLiveData to hold currently selected todo
    private val _selectedTodo = MutableLiveData<Todo?>()
    val selectedTodo: LiveData<Todo?> get() = _selectedTodo

    init {
        val todoDao = TodoDatabase.getDatabase(application).todoDao()
        repository = TodoRepository(todoDao)
        allTodos = repository.allTodos
    }

    fun insert(todo: Todo) = viewModelScope.launch {
        repository.insert(todo)
    }
    fun addTodo(title: String, desc: String) {
        val todo = Todo(title = title, description = desc)
        insert(todo)
    }
    fun selectTodo(todo: Todo) {
        _selectedTodo.value = todo
    }

    fun delete(todo: Todo) = viewModelScope.launch {
        repository.delete(todo)
    }
}
