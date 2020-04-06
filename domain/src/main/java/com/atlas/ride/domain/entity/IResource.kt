package com.atlas.ride.domain.entity

/**
 * A resource is formally defined as an [IService] or an [IRelationship]. In the app, a resource is
 * usable as an executable step within a recipe.
 */
interface IResource {
    /** A resource icon is represented as a name, since it may be either an icon or a URL. */
    val icon: String
}
