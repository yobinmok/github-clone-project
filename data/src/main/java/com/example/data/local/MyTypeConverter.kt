package com.example.data.local

import android.util.Log
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.data.model.SearchUserEntity
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@ProvidedTypeConverter
class MyTypeConverter(
    private val moshi: Moshi
) {

    @TypeConverter
    fun ownerToJson(owner: SearchUserEntity): String{
        val adapter: JsonAdapter<SearchUserEntity> = moshi.adapter(SearchUserEntity::class.java)
        Log.d("typeConverterToJson", adapter.toJson(owner).toString())
        return adapter.toJson(owner)
    }

    @TypeConverter
    fun jsonToOwner(str: String): SearchUserEntity?{
        val adapter: JsonAdapter<SearchUserEntity> = moshi.adapter(SearchUserEntity::class.java)
        Log.d("typeConverterFromJson", adapter.fromJson(str).toString())
        return adapter.fromJson(str)
    }

    @TypeConverter
    fun listToJson(list: List<String>): String{
        val listType = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(listType)
        return adapter.toJson(list)
    }

    @TypeConverter
    fun jsonToList(str: String): List<String>?{
        val listType = Types.newParameterizedType(List::class.java, String::class.java)
        val adapter: JsonAdapter<List<String>> = moshi.adapter(listType)
        return adapter.fromJson(str)
    }
}