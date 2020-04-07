package com.atlas.ride.domain.entity

/**
 * A recipe represents the top level of an IoT app. A complete app may consist of one or more
 * recipes, each of which are executed sequentially.
 */
interface IRecipe : IPrimitive {
    /** A recipe may contain services or relationships. */
    val sequence: List<IResource>
}
