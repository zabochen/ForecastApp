package ua.ck.zabochen.forecastapp.data.entity.weather.today

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import ua.ck.zabochen.forecastapp.internal.TABLE_TODAY_WEATHER
import ua.ck.zabochen.forecastapp.internal.TABLE_TODAY_WEATHER_COLUMN_ID

@Entity(tableName = TABLE_TODAY_WEATHER)
data class TodayWeatherEntity(
    @SerializedName("last_updated_epoch")
    val lastUpdatedEpoch: Double,
    @SerializedName("last_updated")
    val lastUpdated: String,

    // Temperature
    @SerializedName("temp_c")
    val tempC: Double,
    @SerializedName("temp_f")
    val tempF: Double,

    @SerializedName("is_day")
    val isDay: Double,

    @Embedded(prefix = "condition_")
    val condition: Condition,

    // Wind speed
    @SerializedName("wind_mph")
    val windMph: Double,  // miles per hour
    @SerializedName("wind_kph")
    val windKph: Double, // kilometer per hour

    // Wind direction
    @SerializedName("wind_degree")
    val windDegree: Double, // degrees
    @SerializedName("wind_dir")
    val windDir: String, // 16 point compass. e.g.: NSW

    // Pressure
    @SerializedName("pressure_mb")
    val pressureMb: Double, // millibars
    @SerializedName("pressure_in")
    val pressureIn: Double, // inches

    // Precipitation amount
    @SerializedName("precip_mm")
    val precipMm: Double, // millimeters
    @SerializedName("precip_in")
    val precipIn: Double, // inches

    val humidity: Double,

    val cloud: Double,

    // Feels like
    @SerializedName("feelslike_c")
    val feelslikeC: Double,
    @SerializedName("feelslike_f")
    val feelslikeF: Double,

    // Visibility distance
    @SerializedName("vis_km")
    val visKm: Double, // kilometer
    @SerializedName("vis_miles")
    val visMiles: Double, // miles

    val uv: Double
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = TABLE_TODAY_WEATHER_COLUMN_ID
}