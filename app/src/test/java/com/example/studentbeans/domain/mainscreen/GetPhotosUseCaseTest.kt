package com.example.studentbeans.domain.mainscreen

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.studentbeans.model.PhotoItem
import com.example.studentbeans.repository.NetworkRepository
import com.example.studentbeans.repository.mock.FakeNetworkRepoImpl
import com.example.studentbeans.util.Result
import com.google.common.truth.Truth.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GetPhotosUseCaseTest {

    private lateinit var fakeNetworkRepo: NetworkRepository
    private lateinit var getPhotosUseCase: GetPhotosUseCase

//    @get:Rule
//    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        fakeNetworkRepo = FakeNetworkRepoImpl()
        getPhotosUseCase = GetPhotosUseCase(fakeNetworkRepo)
    }

    @Test
    operator fun invoke() = runBlocking {

        // When
        val result = getPhotosUseCase.invoke()

        val expected = Result.Success(
            data = listOf(
                PhotoItem(
                    albumId = 0,
                    id = 0,
                    thumbnailUrl = "",
                    title = "",
                    url = ""
                )
            )
        )

        assertThat(result).isEqualTo(expected)
    }
}
