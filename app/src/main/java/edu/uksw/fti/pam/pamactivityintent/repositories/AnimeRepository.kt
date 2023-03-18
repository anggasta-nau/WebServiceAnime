package edu.uksw.fti.pam.pamactivityintent.repositories

import edu.uksw.fti.pam.pamactivityintent.models.AnimeModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface AnimeRepository {

    @GET("List")
    suspend fun getAnime(): List<AnimeModel>

    companion object {
        var _apiClient: AnimeRepository? = null

        fun getClient(): AnimeRepository {
            if (_apiClient == null) {
                _apiClient = Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/anggasta-nau/DatabaseRepo/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(AnimeRepository::class.java)
            }

            return _apiClient!!
        }
    }

}