package com.atlas.ride.domain.entity

/**
 * A service represents a single API endpoint from a thing entity. This is the basic composable unit
 * of an IoT app (i.e., this primitive has no child objects).
 */
interface IService : IPrimitive, IResource {
    /**
     * A service represents different types of functionality depending on the hardware it reprsents.
     * A [REPORT] service represents a sensor, and returns a numeric value. An [ACTION] service
     * represents an actuator, and consumes a numeric value to perform its functionality. A
     * [CONDITION] service represents a special type of sensor, and returns a boolean value.
     */
    enum class Function {
        REPORT,
        ACTION,
        CONDITION
    }

    /** The service type affects how it is used/controlled during execution of an IoT app. */
    val function: Function

    /** All services are owned by a parent thing. This function may throw an exception if the parent
     * information is not available, such as from a partial query.
     */
    fun getParent(): IThing
}
