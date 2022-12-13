package com.example.studentbeans.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.studentbeans.MainCoroutineRule
import com.example.studentbeans.domain.MainViewModel
import com.example.studentbeans.domain.mainscreen.GetPhotosUseCase
import com.example.studentbeans.domain.mainscreen.MainScreenEvent.*
import com.example.studentbeans.model.PhotoItem
import com.example.studentbeans.repository.NetworkRepository
import com.example.studentbeans.repository.mock.FakeNetworkRepoImpl
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Unit tests for the implementation of [MainViewModel]
 */
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var fakeNetworkRepo: NetworkRepository
    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var getPhotosUseCase: GetPhotosUseCase

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        fakeNetworkRepo = FakeNetworkRepoImpl()
        getPhotosUseCase = GetPhotosUseCase(fakeNetworkRepo)
        savedStateHandle = SavedStateHandle()
        mainViewModel = MainViewModel(getPhotosUseCase, savedStateHandle)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Load photos from network repository - Non empty photos`() = runBlocking {

        //Given an initialized MainViewModel

        //Then
        val uiState = mainViewModel.uiState.first()

        val expected = listOf(
            PhotoItem(
                albumId = 0,
                id = 0,
                thumbnailUrl = "",
                title = "",
                url = ""
            )
        )

        assertThat(uiState.photos.isEmpty()).isFalse()
        assertThat(uiState.photos).isEqualTo(expected)
    }

    @Test
    fun onEmailChangedEvent() = runBlocking {

        val uiState = mainViewModel.uiState.first()

        // Verify that the email is empty initially
        assertThat(uiState.email).isEmpty()

        //When OnEmailChangedEvent() is triggered
        mainViewModel.onTriggerEvent(OnEmailChangedEvent("John@email.com"))

        val expected = "John@email.com"

        //Then email is the same as the expected value
        assertThat(uiState.email).isEqualTo(expected)

    }

    @Test
    fun onPasswordChangedEvent() = runBlocking {

        val uiState = mainViewModel.uiState.first()

        // Verify that the password is empty initially
        assertThat(uiState.password).isEmpty()

        // When OnPasswordChangedEvent() is triggered
        mainViewModel.onTriggerEvent(OnPasswordChangedEvent("Password123"))

        val expected = "Password123"

        // Then password is the same as the expected value
        assertThat(uiState.password).isEqualTo(expected)

    }

}