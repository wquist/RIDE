package com.atlas.ride.domain.entity

/**
 * A primitive represents common properties between every "type" of object that may be used to build
 * an IoT app. This is a base for [IService], [IRelationship], and [IRecipe].
 */
interface IPrimitive {
    /** A user-friendly label representing the basic identifiable form of the primitive. */
    val name: String
    /** A user-friendly description scraped from the IoT-DDL "short-description". */
    val description: String
}
