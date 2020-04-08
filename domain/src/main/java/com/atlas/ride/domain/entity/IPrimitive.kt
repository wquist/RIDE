package com.atlas.ride.domain.entity

/**
 * A primitive represents common properties between every "type" of object that may be used to build
 * an IoT app. This is a base for [IService], [IRelationship], and [IRecipe].
 */
interface IPrimitive {
    /**
     * A primitive can be used as a polymorphic base, so its child types must be identifiable. Note
     * that a primitive of the given type is not necessarily castable to its child type, depending
     * on the calling site. For example, a database result may require an additional query to
     * retrieve the fields of the [IService], etc. type.
     */
    enum class Type {
        SERVICE,
        RELATIONSHIP,
        RECIPE
    }

    /** The primitive object type. */
    val type: Type

    /** A user-friendly label representing the basic identifiable form of the primitive. */
    val name: String
    /** A user-friendly description scraped from the IoT-DDL "short-description". */
    val description: String
}
