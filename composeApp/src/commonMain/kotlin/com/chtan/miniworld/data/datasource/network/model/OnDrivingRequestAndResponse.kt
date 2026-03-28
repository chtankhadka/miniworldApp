import kotlinx.serialization.Serializable


@Serializable
data class DriveControlDto(
    val cid: String = "",  // carId
    val s: Int = 0,     // steering
    val t: Int = 0,     // throttle
    val g: Int = 0,       // gear
    val b: Boolean = true,     // brake
    val c: Boolean = true,     // brake
    val m: String = "P",
    val isXY: Boolean = false,
    val cx: Int = 90,    // cameraView.x
    val cy: Int = 90,    // cameraView.y
)

@Serializable
data class ToggleFeatureDto(
    val cid: String,
    val f: String,   // "MIC", "SOUND", "HEADLIGHT"
    val e: Boolean
)