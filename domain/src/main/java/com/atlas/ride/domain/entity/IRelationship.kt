package com.atlas.ride.domain.entity

/**
 * A relationship represents some composition between two services. When executed in an IoT app, the
 * trigger side is always executed, but the action side depends on the type of connection.
 */
interface IRelationship : IPrimitive, IResource {
    /**
     * Relationships may represent both cooperative and competitive connections between services. In
     * a cooperative connection ([CONTROL], [SUPPORT], [EXTEND], and [DRIVE]), the services "join"
     * in some form, meaning both are usually executed. In a competitive connection ([SUBSUME],
     * [INTERFERE], [REFINE], and [CONTEST]), the trigger "takes over" the action, meaning only one
     * is usually executed.
     */
    enum class Connection {
        CONTROL,
        SUPPORT,
        EXTEND,
        DRIVE,

        SUBSUME,
        INTERFERE,
        REFINE,
        CONTEST
    }

    /** The connection type determines how the action service is invoked in an IoT app. */
    val connection: Connection

    /**
     * The trigger service is always evaluated first, to determine whether the relationship
     * interaction should continue. This funcion may throw an exception if the trigger information
     * is not fully available, such as in a partial query.
     */
    fun getTrigger(): IService
    /**
     * The action service is always evaluated in response to the trigger. This funcion may
     * throw an exception if the action info is not fully available, such as in a partial query.
     */
    fun getAction(): IService
}
