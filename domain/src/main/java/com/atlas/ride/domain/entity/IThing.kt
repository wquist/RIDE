package com.atlas.ride.domain.entity

/**
 * A thing is not used directly when building a new IoT app; its services are. However, services are
 * still grouped by their thing, as this is the form a user most likely expects. The thing exists
 * separately from the [IPrimitive] hierarchy, mainly for information purposes.
 */
interface IThing {
    /**
     * Each thing has a status representing its functionality in the smart space. [UNKNOWN] refers
     * to a thing that is currently disconnected or cannot be accessed, [PROBLEM] refers to a
     * connected thing that cannot exucute its functionality for some reason, and [WORKING] refers
     * to the standard operating state of a thing.
     */
    enum class Status {
        UNKNOWN,
        PROBLEM,
        WORKING;

        override fun toString() = when (this) {
            UNKNOWN -> "Offline"
            PROBLEM -> "Partially Operational"
            WORKING -> "Online"
        }
    }

    /** A user-friendly label representing the thing as a whole. */
    val name: String
    /** A user-friendly description scraped from the IoT-DDL "short-description". */
    val description: String

    /** A thing icon is represented as a name, since it may be either an icon or a URL. */
    val icon: String
    /** Each thing has a choosable background color. */
    val color: Int
    /** A thing status must be updated based on its last known state. */
    val status: Status

    /**
     * Retrieve a list of service objects belonging to this thing. This function may throw an
     * exception in cases where the service data is not available, such as a partial query.
     */
    fun getServices(): List<IService>
}
