package com.example.androidexternshipproject.dbInteractions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomDbDao {
    @Query("SELECT * FROM CredentialsEntity")
    fun getAll(): List<CredentialsEntity>

    @Query("SELECT * FROM CredentialsEntity WHERE email LIKE :inp LIMIT 1")
    fun getByEmail(inp: String): CredentialsEntity

    @Query("SELECT * FROM CredentialsEntity WHERE password LIKE :inp LIMIT 1")
    fun getByPassword(inp: String): CredentialsEntity

    @Insert
    suspend fun insert(inp: CredentialsEntity)
}