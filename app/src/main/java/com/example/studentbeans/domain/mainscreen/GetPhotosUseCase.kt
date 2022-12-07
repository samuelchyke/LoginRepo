package com.example.studentbeans.domain.mainscreen

import android.content.ContentValues.TAG
import android.util.Log
import com.example.studentbeans.model.PhotoResponse
import com.example.studentbeans.repository.NetworkRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
){
    suspend fun invoke() : PhotoResponse {
        try {
            val response = networkRepository.getListOfPhotos()
            response.body()?.let { photoResponse ->
                return photoResponse
            }
        } catch (e: Exception) {
            Log.d(TAG, "An error has occurred: $e")
        }
        return PhotoResponse()
    }
}
