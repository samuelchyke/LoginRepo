package com.example.studentbeans.repository.mock

import com.example.studentbeans.model.PhotoItem
import com.example.studentbeans.model.PhotoResponse
import com.example.studentbeans.repository.NetworkRepository
import retrofit2.Response

class FakeNetworkRepoImpl : NetworkRepository {

    override suspend fun getListOfPhotos(): Response<PhotoResponse> {
        photoResponseMock.clear()
        photoResponseMock += photoItemsMock
        return Response.success(photoResponseMock)
    }

    companion object {

        private val photoResponseMock = PhotoResponse()

        private val photoItemsMock = PhotoItem(
            albumId = 0,
            id = 0,
            thumbnailUrl = "",
            title = "",
            url = ""
        )
    }
}
