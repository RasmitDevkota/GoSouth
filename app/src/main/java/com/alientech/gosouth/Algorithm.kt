package com.alientech.gosouth

import kotlin.math.*

typealias NodeList = ArrayList<Node>

val alg = Algorithm()

class Algorithm {
    lateinit var graph: Graph

    // Initialization
    /**
     * Used to build graph from data variable
     * @see data
     */
    fun build() {

    }

    // Navigation
    /**
     * Used to generate Path object from Node to Node
     *
     * Used for UI
     */
    fun navigate(origin: Node, destination: Node): Path {
        return Path(origin, destination)
    }

    /**
     * Used to generate Path object from current location to Node
     *
     * Used for UI
     */
    fun navigate(origin: Coordinate, destination: Node): Path {
        return Path(Node(origin), destination)
    }

    // Pathfinding (A*)
    /**
     * Used to calculate path from Node to Node
     *
     * Used to initialize Path object
     */
    fun pathfind(origin: Node, destination: Node): NodeList {
        val path: NodeList = arrayListOf()

        path.add(origin)

        // Calculate and add intermediary nodes
        // ...

        path.add(destination)

        return path
    }

    // Distance
    /**
     * Used to calculate distance from Coordinate to Coordinate
     */
    fun distance(A: Coordinate, B: Coordinate): Float {
        val latA = A.latitude
        val latB = B.latitude

        val latDelta = latB - latA
        val longDelta = B.longitude - A.longitude

        val a = hav(latDelta) + hav(longDelta) * cos(latA) * cos(latB)

        return 2 * asin(sqrt(a)) * 6378137
    }

    /**
     * Used to calculate distance from Node to Node
     */
    fun distance(A: Node, B: Node): Float {
        val radA = A.coordinate.inRadians()
        val radB = B.coordinate.inRadians()

        return distance(radA, radB)
    }

    // Math
    /**
     * Implements Haversine Formula
     */
    fun hav(theta: Float): Float {
        return sin(theta/2).pow(2)
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