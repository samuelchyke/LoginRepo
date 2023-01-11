package com.example.studentbeans.util

/**
 * A generic class that holds a value or error.
 * @param <T>
 */
sealed class Result<out R>(
    open val data: R? = null,
) {

    data class Success<out T>(override val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}

/**
 * `true` if [Result] is of type [Success] & holds non-null [Success.data].
 */
val Result<*>.succeeded
    get() = this is Result.Success && data != null
