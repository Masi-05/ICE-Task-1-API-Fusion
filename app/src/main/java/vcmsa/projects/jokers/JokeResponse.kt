package vcmsa.projects.jokers

data class JokeResponse(
    val type: String,
    val joke: String?,
    val setup: String?,
    val delivery: String?
)
