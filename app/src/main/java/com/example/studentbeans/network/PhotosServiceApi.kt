package com.example.studentbeans.network

import com.example.studentbeans.model.PhotoResponse
import retrofit2.Response
import retrofit2.http.GET

interface PhotosServiceApi {

    @GET(PHOTO_SEARCH_PATH)
    suspend fun getListOfPhotos() : Response<PhotoResponse>

    companion object{

        const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        private const val PHOTO_SEARCH_PATH = "photos"

    }


}
