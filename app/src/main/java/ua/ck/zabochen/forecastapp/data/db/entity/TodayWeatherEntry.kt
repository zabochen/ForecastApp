package ua.ck.zabochen.forecastapp.data.db.entity

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "current")
data class TodayWeatherEntry(
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
    val condition: Condition,
    @SerializedName("wind_mph")
    val windMph: Double,
    @SerializedName("wind_kph")
    val windKph: Double,
    @SerializedName("wind_degree")
    val windDegree: Double,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("pressure_mb")
    val pressureMb: Double,
    @SerializedName("pressure_in")
    val pressureIn: Double,
    @SerializedName("precip_mm")
    val precipMm: Double,
    @SerializedName("precip_in")
    val precipIn: Double,
    val humidity: Double,
    val cloud: Double,
    @SerializedName("feelslike_c")
    val feelslikeC: Double,
    @SerializedName("feelslike_f")
    val feelslikeF: Double,
    @SerializedName("vis_km")
    val visKm: Double,
    @SerializedName("vis_miles")
    val visMiles: Double,
    val uv: Double
)