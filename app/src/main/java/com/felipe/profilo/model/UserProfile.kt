package com.felipe.profilo.model

import kotlin.random.Random

data class UserProfile constructor(
    val id: Int,
    val name: String,
    val pictureUrl: String,
    val status: Boolean = Random.nextBoolean()
)

val MOCK_USER_PROFILES = listOf(
    UserProfile(1, "Kaden Gonzalez", "https://i.pravatar.cc/150?img=1"),
    UserProfile(2, "Patricia Garcia", "https://i.pravatar.cc/150?img=2"),
    UserProfile(3, "Brennen Lloyd", "https://i.pravatar.cc/150?img=3"),
    UserProfile(4, "Stuart Fowler", "https://i.pravatar.cc/150?img=4"),
    UserProfile(5, "Meredith Suarez", "https://i.pravatar.cc/150?img=5"),
    UserProfile(6, "Zayden Peterson", "https://i.pravatar.cc/150?img=6"),
    UserProfile(7, "Ernesto Sampson", "https://i.pravatar.cc/150?img=7"),
    UserProfile(8, "Santos Moore", "https://i.pravatar.cc/150?img=8"),
    UserProfile(9, "Gracie Madden", "https://i.pravatar.cc/150?img=9"),
    UserProfile(10, "Teresa Watts", "https://i.pravatar.cc/150?img=10"),
    UserProfile(11, "Giovanni Krause", "https://i.pravatar.cc/150?img=11"),
    UserProfile(12, "Shawn Wall", "https://i.pravatar.cc/150?img=12"),
)
