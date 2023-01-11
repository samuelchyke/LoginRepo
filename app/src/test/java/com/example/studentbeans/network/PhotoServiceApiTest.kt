package com.example.studentbeans.network
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.studentbeans.model.PhotoResponse
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(AndroidJUnit4::class)
class PhotoServiceApiTest {

    lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: PhotosServiceApi
    private lateinit var gson: Gson
    private lateinit var search: String

    @Before
    fun setup() {
//        MockitoAnnotations.openMocks(this)
        gson = Gson()
        mockWebServer = MockWebServer()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(PhotosServiceApi::class.java)
        search = "/photos"
    }

    @Test
    fun`get list of photos from api`() = runBlocking {
        // Given
        val mockResponse = MockResponse()

        // When
        mockWebServer.enqueue(
            mockResponse
                .setResponseCode(200)
                .setBody(gson.toJson(PhotoResponse()))
        )

        val response = apiService.getListOfPhotos()

        val expectedRequest = mockWebServer.takeRequest()

        // Then
        assertEquals(search, expectedRequest.path)
        assertThat(response.body()).isEqualTo(PhotoResponse())
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }
}
