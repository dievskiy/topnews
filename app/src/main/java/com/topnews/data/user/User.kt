package com.topnews.data.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.TypeConverters
import com.topnews.data.db.UserTypeConverter

/**
 * User class
 *
 * @param countryType user's country tag [CountryType]
 * @param categories user's favourites news categories [NewsCategory]]
 */
@TypeConverters(UserTypeConverter::class)
@Entity(primaryKeys = ["id"])
data class User(
    @ColumnInfo(name = "id")
    val userId: Int,
    @ColumnInfo(name = "country")
    val countryType: CountryType,
    @ColumnInfo(name = "categories")
    val categories: List<NewsCategory>
)

enum class CountryType {
    AE, AR, AT, AU, BE, BG, BR, CA, CH, CN, CO, CU, CZ, DE, EG, FR, GB, GR, HK, HU, ID, IE, IL, IN, IT, JP, KR, LT, LV, MA, MX, MY, NG, NL, NO, NZ, PH, PL, PT, RO, RS, RU, SA, SE, SG, SI, SK, TH, TR, TW, UA, US, VE, ZA
}

enum class NewsCategory {
    TECHNOLOGY, BUSINESS, ENTERTAINMENT, GENERAL, HEALTH, SCIENCE, SPORTS
}