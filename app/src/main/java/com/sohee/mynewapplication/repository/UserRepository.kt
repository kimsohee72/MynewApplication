package com.sohee.mynewapplication.repository

import com.sohee.mynewapplication.data.UserDao
import com.sohee.mynewapplication.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val readAllData : Flow<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user : User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user : User){
        userDao.deleteUser(user)
    }

    fun searchDatabase(searchQuery: String): Flow<List<User>> {
        return userDao.searchDatabase(searchQuery)
    }
}