package com.atlas.ride.domain.util

/**
 * A variant type representing loading-content-error states. The variant specifies a data type to be
 * loaded, but the actual data is only made available once the content state is reached.
 */
sealed class Lce<out T> {
    /** An operation is in progress. No additional information is available. */
    object Loading : Lce<Nothing>()

    /** The operation has completed successfully, and the data is available. */
    data class Content<out T>(val data: T) : Lce<T>()

    /** An error has occurred. No data is available, but a descriptive message is provided. */
    data class Error(val message: String) : Lce<Nothing>()
}
