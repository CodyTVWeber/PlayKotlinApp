import kotlinx.serialization.Serializable

@Serializable
data class HymnObject(
    val objectID: Int,
    val title: String,
    val author: String,
    val year: Int,
    val url: String,
    val lyrics: List<String>,
    val tune: String,
    val meter: String,
    val theme: String
)