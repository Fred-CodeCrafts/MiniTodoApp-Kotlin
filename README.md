# 📝 To-Do List App

A simple To-Do List Android application built using **Kotlin**, **Fragments**, and the **MVVM architecture pattern** with **Room Database**.  
This app is created as part of a learning project.

---

## ✨ Features
- Add new tasks to the list  
- View all tasks in a list format  
- Delete tasks from the database  
- Data is stored locally using **Room Database**  

---

## 🏗️ Architecture
The app follows the **MVVM + Repository** architecture for better separation of concerns and testability:

- **UI (Activity & Fragments)**  
  - `MainActivity` → host for fragments  
  - `ListFragment` → shows all To-Do items  
  - `DetailFragment` → add/edit task  

- **ViewModel**  
  - Provides data to UI and survives configuration changes  

- **Repository**  
  - Acts as a bridge between ViewModel and Database  

- **DAO (Data Access Object)**  
  - Defines SQL queries for interacting with the database  

- **Room Database**  
  - Local persistence layer for storing To-Do items  

---

## 📂 Project Structure
