package com.alientech.gosouth

/**
 * Newline-separated list of all nodes
 *
 * Used for initial graph creation
 *
 * Each line follows pattern:
 *
 * Latitude(Float),Longitude(Float),Type(ROOM|CORNER),Extras...(Comma-separated)
 */
val data: String = """
    1,2,ROOM,teacher=
""".trimIndent()