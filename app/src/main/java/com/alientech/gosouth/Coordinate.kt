package com.alientech.gosouth

data class Coordinate(val units: String = "DEGREES") {
    var latitude = 0.0f
    var longitude = 0.0f

    /**
     * Used to check if two Coordinate objects are the same coordinate
     */
    fun equals(other: Coordinate): Boolean {
        return if (units == other.units) {
            latitude == other.latitude && longitude == other.longitude
        } else if (this.units == "DEGREES") {
            this == other.inDegrees()
        } else { // this.units == "RADIANS"
            this.inDegrees() == other
        }
    }

    /**
     * Used to generate a Coordinate object in degrees from one in radians
     */
    fun inDegrees(): Coordinate {
        return if (units == "RADIANS") Coordinate("DEGREES") else this
    }

    /**
     * Used to generate a Coordinate object in radians from one in degrees
     */
    fun inRadians(): Coordinate {
        return if (units == "DEGREES") Coordinate("RADIANS") else this
    }
}