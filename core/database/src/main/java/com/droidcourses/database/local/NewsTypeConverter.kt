package com.droidcourses.database.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.droidcourses.database.entity.SourceEntity

@ProvidedTypeConverter
class NewsTypeConverter {

    @TypeConverter
    fun sourceToString(source: SourceEntity): String {
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(string: String): SourceEntity {
       return string.split(',').let { data ->
           SourceEntity(data[0], data[1])
       }
    }
}