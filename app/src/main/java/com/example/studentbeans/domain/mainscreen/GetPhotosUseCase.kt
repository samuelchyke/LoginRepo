package com.example.studentbeans.domain.mainscreen

import com.example.studentbeans.model.PhotoResponse
import com.example.studentbeans.repository.NetworkRepository
import com.example.studentbeans.util.Result
import com.example.studentbeans.util.Result.Error
import com.example.studentbeans.util.Result.Success
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class GetPhotosUseCase @Inject constructor(
    private val networkRepository: NetworkRepository
) {

    suspend fun invoke(): Result<PhotoResponse> = withContext(IO) {
        return@withContext try {
            val response = networkRepository.getListOfPhotos()
            response.body()?.let { photoResponse ->
                Success(photoResponse)
            }!!
        } catch (e: Exception) {
            Error(e)
        }
    }
}

//    suspend fun invoke() : Result<PhotoResponse> = withContext(IO) {
// //        return@withContext try {
// //            val response = networkRepository.getListOfPhotos()
// //            response.body()?.let { photoResponse ->
// //                return@withContext photoResponse
// //            }
// //        }catch (e: Exception) {
// //            Log.d(TAG, "An error has occurred: $e")
// //        }
// //    return@withContext networkRepository.getListOfPhotos().body()
// //        ?.let { photoResponse -> Success(photoResponse) }
//        return@withContext try {
//            val response = networkRepository.getListOfPhotos()
//            response.body()?.let{ photoResponse ->
//                Success(photoResponse)
//            }!!
//        }catch (e: Exception) {
//            Error(e)
//        }
// //        return@withContext Success(response)
// //        response.body()?.let{ photoResponse ->
// //            return@withContext photoResponse
// //        }
//
// }
// //        return@withContext try {
// //            networkRepository.getListOfPhotos()
// //        } catch (e: Exception) {
// //            Log.d(TAG, "An error has occurred: $e")
// //        }
