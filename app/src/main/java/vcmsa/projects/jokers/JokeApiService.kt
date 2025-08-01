package vcmsa.projects.jokers

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface JokeApiService {
    @GET("joke/Any")
    fun getJoke(
        @Query("categories") categories: String?,
        @Query("type") type: String?,
        @Query("blacklistFlags") blacklistFlags: String?
    ): Call<JokeResponse>
}
