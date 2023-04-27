package com.shakiv.husain.kisannetwork.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.shakiv.husain.kisannetwork.data.model.Contact
import com.shakiv.husain.kisannetwork.data.network.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.FileNotFoundException
import javax.inject.Inject

class ContactRepository @Inject constructor(private val context: Context) {

    private suspend fun readJsonFromAssets(fileName: String): String {
        return withContext(Dispatchers.IO) {
            val assetManager = context.assets
            val inputStream = assetManager.open(fileName)
            inputStream.bufferedReader().use { it.readText() }
        }
    }

    fun getContacts() = flow {
        emit(Resource.Loading())
        try {
            val jsonString = readJsonFromAssets("contacts.json")
            val gson = Gson()
            val jsonObject = JsonParser().parse(jsonString).asJsonObject
            val contactArray = jsonObject.getAsJsonArray("contacts")
            val contacts = gson.fromJson<List<Contact>>(
                contactArray, object : TypeToken<List<Contact>>() {}.type
            )
            emit(Resource.Success(contacts))
        } catch (e: FileNotFoundException) {
            Log.e("Repository", "File not found: ${e.message}")
            emit(Resource.Failure())
        } catch (e: JsonParseException) {
            Log.e("Repository", "JSON parsing error: ${e.message}")
            emit(Resource.Failure())
        } catch (e: Exception) {
            Log.e("Repository", "Error: ${e.message}")
            emit(Resource.Failure())
        }
    }
}