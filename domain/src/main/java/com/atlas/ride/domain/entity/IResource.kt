package com.atlas.ride.domain.entity

/**
 * A resource is formally defined as an [IService] or an [IRelationship]. Within the context of the
 * app, this refers to a primitive that has some unique displayable component; namely, the status
 * and icon. Some resources may have additional properties, such as [IService.color].
 */
interface IResource {
    /**
     * Each resource has a status representing its functionality in the smart space. [UNKNOWN]
     * refers to a resource that is currently disconnected or cannot be accessed, [PROBLEM] refers
     * to a connected resource that cannot exucute its functionality for some reason, and [WORKING]
     * refers to the standard operating state of a resource.
     */
    enum class Status {
        UNKNOWN,
        PROBLEM,
        WORKING
    }

    /** A resource status may be determined directly, or based on the state of its child objects. */
    val status: Status
    /** A resource icon is represented as a name, since it may be either an icon or a URL. */
    val icon: String
}
