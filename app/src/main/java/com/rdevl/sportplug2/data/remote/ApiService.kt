package com.rdevl.sportplug2.data.remote

import com.rdevl.sportplug2.data.entries.*
import retrofit2.http.*

interface ApiService {
    @Headers("Content-Type: application/json", "X-Token: 237e51cfe32e79c51c1e0a9297c8de5d")
    @POST ("api/champs")
    suspend fun getChamps(@Body postModel: PostChampsBody
    ): Champs

    @GET("apiru/prematch/champ/")
    suspend fun getChampMatches(@Query("id") id: Int): Matches

    @GET("apiru/prematch/event/")
    suspend fun getDetailMatch(@Query("id") id: Int): DetailMatch

    @GET("apiru/live/sport?id=1")
    suspend fun getLiveMatches(): Live
}