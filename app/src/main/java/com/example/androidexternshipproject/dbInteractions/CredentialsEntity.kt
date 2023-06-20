package com.example.androidexternshipproject.dbInteractions

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CredentialsEntity(
    @PrimaryKey val email: String,
    val password:String
)