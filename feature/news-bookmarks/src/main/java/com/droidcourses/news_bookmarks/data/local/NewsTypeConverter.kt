package com.droidcourses.news_bookmarks.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.droidcourses.news_bookmarks.domain.models.Source

@ProvidedTypeConverter
class NewsTypeConverter {

    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(string: String): Source {
       return string.split(',').let { data ->
           Source(data[0], data[1])
       }
    }
}