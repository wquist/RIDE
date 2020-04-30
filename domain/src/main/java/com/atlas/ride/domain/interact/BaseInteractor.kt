package com.atlas.ride.domain.interact

import com.atlas.ride.domain.util.Lce

abstract class BaseInteractor<R> {
    abstract suspend fun invoke(onChange: (Lce<R>) -> Unit)
}
