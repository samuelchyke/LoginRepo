package com.example.studentbeans.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.studentbeans.model.PhotoResponse
import com.example.studentbeans.network.PhotosServiceApi
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
class NetworkRepositoryImplTest {

    private lateinit var repository : NetworkRepositoryImpl

    @Mock
    lateinit var api: PhotosServiceApi

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = NetworkRepositoryImpl(api)
    }

    @Test
    fun `get photo response`() {
        runBlocking {
            //Mockito
            Mockito.`when`(api.getListOfPhotos()).thenReturn(Response.success(PhotoResponse()))

            //When
            val response = repository.getListOfPhotos()
            val expected = Response.success(PhotoResponse())

            //Then
            assertThat(expected.body() == response.body())
        }
    }
}