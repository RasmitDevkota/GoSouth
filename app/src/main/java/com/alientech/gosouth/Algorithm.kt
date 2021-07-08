package com.alientech.gosouth

import kotlin.math.*

class Algorithm {
    fun distance(latA: Float, longA: Float, latB: Float, longB: Float): Float {
        val latArad = Math.toRadians(latA.toDouble())
        val latBrad = Math.toRadians(latB.toDouble())

        val latDelta = latBrad - latArad
        val longDelta = Math.toRadians(longB.toDouble() - longA.toDouble())

        val a = hav(latDelta) + hav(longDelta) * cos(latArad) * cos(latBrad)
        val c = 2 * asin(sqrt(a))

        return c.toFloat() * 6378137
    }

    fun hav(theta: Double): Float {
        return sin(theta.toFloat()/2).pow(2)
    }
}

/*
Initialization:
  1. List of coordinates
  2. Assemble (minimal) Graph and store in a variable (in Algorithm object)
  3. (Background) assemble rest of graph

Request:
    1. Find their coordinate
    2. Find nearest Node (node 1)
    3. Find the Node that they want to go to in the graph
    4. Determine if it is in minimal graph, choose appropriate graph
*/