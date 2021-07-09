package com.alientech.gosouth

data class Node(val coordinate: Coordinate) {
    var neighbors: NodeList = NodeList()

    fun connect(neighbor: Node) {
        neighbors.add(neighbor)
    }
}