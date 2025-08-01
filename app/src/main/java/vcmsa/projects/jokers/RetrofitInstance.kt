package vcmsa.projects.jokers

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://v2.jokeapi.dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: JokeApiService by lazy {
        retrofit.create(JokeApiService::class.java)
    }
}