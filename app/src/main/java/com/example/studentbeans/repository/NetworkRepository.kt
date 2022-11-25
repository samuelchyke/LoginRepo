package com.example.studentbeans.repository

import com.example.studentbeans.model.PhotoResponse
import com.example.studentbeans.network.PhotosServiceApi
import retrofit2.Response
import javax.inject.Inject

interface NetworkRepository {

    suspend fun getListOfPhotos() : Response<PhotoResponse>

}

class NetworkRepositoryImpl @Inject constructor(
    private val photoServiceApi: PhotosServiceApi
):NetworkRepository{

    override suspend fun getListOfPhotos(): Response<PhotoResponse> {
        return photoServiceApi.getListOfPhotos()
    }

}