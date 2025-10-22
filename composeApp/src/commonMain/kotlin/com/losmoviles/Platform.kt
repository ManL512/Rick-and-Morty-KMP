package com.losmoviles

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform