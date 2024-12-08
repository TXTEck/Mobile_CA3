package com.example.homeguard.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.homeguard.data.database.AppDatabase
import com.example.homeguard.data.datastore.UserPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao = AppDatabase.getDatabase(application).userDao()
    private val userPreferences = UserPreferences(application)

    private val _userName = MutableStateFlow<String?>(null)
    val userName: StateFlow<String?> = _userName

    private val _userAddress = MutableStateFlow<String?>(null)
    val userAddress: StateFlow<String?> = _userAddress

    init {
        loadUser()
    }

    private fun loadUser() {
        viewModelScope.launch {
            try {
                val dbUser = userDao.getUser()
                println("Database User Retrieved: $dbUser")

                val name = dbUser?.name ?: "No Name Found"
                val address = dbUser?.address ?: "No Address Found"

                _userName.value = name
                _userAddress.value = address

                println("Loaded User - Name: $name, Address: $address")
            } catch (e: Exception) {
                println("Error loading user: ${e.message}")
            }
        }
    }

    fun saveUser(name: String, address: String) {
        viewModelScope.launch {
            userDao.insertUser(com.example.homeguard.data.database.User(name = name, address = address))
            userPreferences.saveUser(name, address)
        }
    }
}
