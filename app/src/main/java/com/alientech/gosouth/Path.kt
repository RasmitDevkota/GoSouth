package com.alientech.gosouth

import java.util.*

class Path(origin: Node, dest: Node) {
    var path: NodeList = alg.pathfind(origin, dest)

    val distance: Float
        get() {
          return alg.distance(this.path[0], this.path[1])
        }
}