package com.example.cookbook.preferences

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.edit
import com.auth0.android.jwt.JWT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "user_preferences")

object UserPreferencesKeys {
    val TOKEN = stringPreferencesKey("token")
    val USER_ID = stringPreferencesKey("user_id")
    val USERNAME = stringPreferencesKey("username")
    val EMAIL = stringPreferencesKey("email")
}

suspend fun saveToken(context: Context, token: String) {
    context.dataStore.edit { preferences ->
        preferences[UserPreferencesKeys.TOKEN] = token
    }
}

fun getToken(context: Context): Flow<String?> {
    return context.dataStore.data.map { preferences ->
        preferences[UserPreferencesKeys.TOKEN]
    }
}

fun getUserIdFromToken(token: String): String? {
    val jwt = JWT(token)
    return jwt.getClaim("userId").asString()
}

fun getRollFromToken(token: String): String? {
    val jwt = JWT(token)
    return jwt.getClaim("roll").asString()
}




