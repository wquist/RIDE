package com.atlas.ride.domain.entity

/**
 * A recipe represents the top level of an IoT app. A complete app may consist of one or more
 * recipes, each of which are executed sequentially.
 */
interface IRecipe : IPrimitive {
    /**
     * A recipe contains a list that is a mix of services and relationships in a specified order.
     * This function may throw an exception in cases where the sequence primitives are not availble,
     * such as in a partial query.
     */
    fun getResources(): List<IResource>
}
