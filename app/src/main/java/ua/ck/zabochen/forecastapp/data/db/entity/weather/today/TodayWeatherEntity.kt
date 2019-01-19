package ua.ck.zabochen.forecastapp.data.db.entity.weather.today

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import ua.ck.zabochen.forecastapp.utils.TABLE_TODAY_WEATHER_COLUMN_ID
import ua.ck.zabochen.forecastapp.utils.TABLE_TODAY_WEATHER_TABLE_NAME

@Entity(tableName = TABLE_TODAY_WEATHER_TABLE_NAME)
data class TodayWeatherEntity(
    @SerializedName("last_updated_epoch")
    val lastUpdatedEpoch: Double,
    @SerializedName("last_updated")
    val lastUpdated: String,
    @SerializedName("temp_c")
    val tempC: Double,
    @SerializedName("temp_f")
    val tempF: Double,
    @SerializedName("is_day")
    val isDay: Double,
    @Embedded(prefix = "condition_")
    val condition: Condition,
    @SerializedName("wind_mph")
    val windMph: Double, // Wind speed in miles per hour
    @SerializedName("wind_kph")
    val windKph: Double, // Wind speed in kilometer per hour
    @SerializedName("wind_degree")
    val windDegree: Double, // Wind direction in degrees
    @SerializedName("wind_dir")
    val windDir: String, // Wind direction as 16 point compass. e.g.: NSW
    @SerializedName("pressure_mb")
    val pressureMb: Double, // Pressure in millibars
    @SerializedName("pressure_in")
    val pressureIn: Double, // Pressure in inches
    @SerializedName("precip_mm")
    val precipMm: Double, // Precipitation amount in millimeters
    @SerializedName("precip_in")
    val precipIn: Double, // Precipitation amount in inches
    val humidity: Double,
    val cloud: Double,
    @SerializedName("feelslike_c")
    val feelslikeC: Double,
    @SerializedName("feelslike_f")
    val feelslikeF: Double,
    @SerializedName("vis_km")
    val visKm: Double, // Visibility distance in km
    @SerializedName("vis_miles")
    val visMiles: Double, // Visibility distance in miles
    val uv: Double
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = TABLE_TODAY_WEATHER_COLUMN_ID
}