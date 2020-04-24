package com.atlas.ride.domain.entity

import java.util.Date

interface INotification {
    val event: String
    val description: String

    val raisedOn: Date
}
