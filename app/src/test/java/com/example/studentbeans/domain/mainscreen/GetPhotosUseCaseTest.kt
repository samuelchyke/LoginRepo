package com.example.studentbeans.domain.mainscreen

import android.util.Log.ASSERT
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.studentbeans.model.PhotoItem
import com.example.studentbeans.repository.NetworkRepository
import com.example.studentbeans.repository.mock.FakeNetworkRepoImpl
import com.google.common.truth.Truth.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat


@RunWith(AndroidJUnit4::class)
class GetPhotosUseCaseTest {

    private lateinit var fakeNetworkRepo: NetworkRepository
    private lateinit var getPhotosUseCase: GetPhotosUseCase

    @Before
    fun setUp() {
        fakeNetworkRepo = FakeNetworkRepoImpl()
        getPhotosUseCase = GetPhotosUseCase(fakeNetworkRepo)
    }

    @Test
    operator fun invoke() = runBlocking {

        //When
        val result = getPhotosUseCase.invoke()

        val expected = listOf(
            PhotoItem(
                albumId = 0,
                id = 0,
                thumbnailUrl = "",
                title = "",
                url = ""
            )
        )

        assertThat(result).isEqualTo(expected)
    }
}