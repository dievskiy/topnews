package com.topnews.data.news

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.topnews.data.db.NewsTypeConverter

@TypeConverters(NewsTypeConverter::class)
@Entity(
    tableName = "news",
    indices = [
        Index("id")
    ],
    primaryKeys = ["id"]
)
data class News(
    val id: Int,
    @field:SerializedName("status")
    val status: String?,
    @field:SerializedName("articles")
    val articles: List<Article>
) {

    data class Article(
        @field:SerializedName("source") val source: Source?,
        @field:SerializedName("author") val author: String?,
        @field:SerializedName("title") val title: String?,
        @field:SerializedName("description") val description: String?,
        @field:SerializedName("url") val url: String,
        @field:SerializedName("urlToImage") val urlToImage: String?,
        @field:SerializedName("publishedAt") val publishedAt: String?,
        @field:SerializedName("content") val content: String?
    ) {

        override fun hashCode(): Int {
            return url.hashCode()
        }

        override fun equals(other: Any?): Boolean {
            return when (other) {
                is Article -> this.url == other.url
                else -> false
            }
        }
    }

    data class Source(
        @field:SerializedName("id") val id: String?,
        @field:SerializedName("name") val name: String?
    )
}

object NewsTitleFormatter {
    /**
     * Remove sources from title
     */
    fun formatTitle(title: String): String {
        // there are two different dashes that occur in titles
        val regex = " [-—] .*".toRegex()
        return title.replace(regex, "")
    }
}
