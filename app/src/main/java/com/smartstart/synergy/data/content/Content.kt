package com.smartstart.synergy.data.content

import androidx.compose.ui.graphics.Color
import com.smartstart.synergy.ui.theme.Amber
import com.smartstart.synergy.ui.theme.Coral
import com.smartstart.synergy.ui.theme.Grass
import com.smartstart.synergy.ui.theme.Pink
import com.smartstart.synergy.ui.theme.SkyBlue
import com.smartstart.synergy.ui.theme.Teal

/** A single letter of the alphabet with an example word the child can relate to. */
data class Letter(val upper: Char, val lower: Char, val word: String, val emoji: String)

/** A number from 0..N with the emoji used to visualise counting. */
data class NumberItem(val value: Int, val emoji: String)

enum class ShapeKind { CIRCLE, SQUARE, TRIANGLE, RECTANGLE, OVAL, STAR, DIAMOND, HEART, PENTAGON, HEXAGON }

data class ShapeItem(val kind: ShapeKind, val label: String, val color: Color)

data class ColorItem(val label: String, val color: Color)

data class Animal(val name: String, val emoji: String, val sound: String, val wild: Boolean)

/**
 * Static learning content for Phase 1. Kept in code (no assets required) so the app runs
 * fully offline and new items can be added by editing these lists.
 */
object Content {

    val alphabet: List<Letter> = listOf(
        Letter('A', 'a', "Apple", "🍎"),
        Letter('B', 'b', "Ball", "⚽"),
        Letter('C', 'c', "Cat", "🐱"),
        Letter('D', 'd', "Dog", "🐶"),
        Letter('E', 'e', "Egg", "🥚"),
        Letter('F', 'f', "Fish", "🐟"),
        Letter('G', 'g', "Goat", "🐐"),
        Letter('H', 'h', "Hat", "🎩"),
        Letter('I', 'i', "Ice", "🧊"),
        Letter('J', 'j', "Jug", "🫙"),
        Letter('K', 'k', "Kite", "🪁"),
        Letter('L', 'l', "Lion", "🦁"),
        Letter('M', 'm', "Moon", "🌙"),
        Letter('N', 'n', "Nest", "🪺"),
        Letter('O', 'o', "Orange", "🍊"),
        Letter('P', 'p', "Pig", "🐷"),
        Letter('Q', 'q', "Queen", "👸"),
        Letter('R', 'r', "Rain", "🌧️"),
        Letter('S', 's', "Sun", "☀️"),
        Letter('T', 't', "Tree", "🌳"),
        Letter('U', 'u', "Umbrella", "☂️"),
        Letter('V', 'v', "Van", "🚐"),
        Letter('W', 'w', "Water", "💧"),
        Letter('X', 'x', "Xylophone", "🎼"),
        Letter('Y', 'y', "Yak", "🐂"),
        Letter('Z', 'z', "Zebra", "🦓"),
    )

    val numbers: List<NumberItem> = (0..20).map { n ->
        NumberItem(n, listOf("⭐", "🍎", "🎈", "🐥", "🌸", "🚗").random())
    }

    val shapes: List<ShapeItem> = listOf(
        ShapeItem(ShapeKind.CIRCLE, "Circle", Coral),
        ShapeItem(ShapeKind.SQUARE, "Square", SkyBlue),
        ShapeItem(ShapeKind.TRIANGLE, "Triangle", Grass),
        ShapeItem(ShapeKind.RECTANGLE, "Rectangle", Amber),
        ShapeItem(ShapeKind.OVAL, "Oval", Teal),
        ShapeItem(ShapeKind.STAR, "Star", Amber),
        ShapeItem(ShapeKind.DIAMOND, "Diamond", Pink),
        ShapeItem(ShapeKind.HEART, "Heart", Coral),
        ShapeItem(ShapeKind.PENTAGON, "Pentagon", SkyBlue),
        ShapeItem(ShapeKind.HEXAGON, "Hexagon", Grass),
    )

    val colors: List<ColorItem> = listOf(
        ColorItem("Red", Color(0xFFE53935)),
        ColorItem("Blue", Color(0xFF1E88E5)),
        ColorItem("Green", Color(0xFF43A047)),
        ColorItem("Yellow", Color(0xFFFDD835)),
        ColorItem("Orange", Color(0xFFFB8C00)),
        ColorItem("Purple", Color(0xFF8E24AA)),
        ColorItem("Pink", Color(0xFFEC407A)),
        ColorItem("Black", Color(0xFF212121)),
        ColorItem("White", Color(0xFFFFFFFF)),
        ColorItem("Brown", Color(0xFF6D4C41)),
        ColorItem("Grey", Color(0xFF9E9E9E)),
    )

    val animals: List<Animal> = listOf(
        Animal("Dog", "🐶", "Woof woof", wild = false),
        Animal("Cat", "🐱", "Meow", wild = false),
        Animal("Cow", "🐮", "Moo", wild = false),
        Animal("Goat", "🐐", "Maa", wild = false),
        Animal("Chicken", "🐔", "Cluck cluck", wild = false),
        Animal("Duck", "🦆", "Quack", wild = false),
        Animal("Pig", "🐷", "Oink", wild = false),
        Animal("Horse", "🐴", "Neigh", wild = false),
        Animal("Sheep", "🐑", "Baa", wild = false),
        Animal("Lion", "🦁", "Roar", wild = true),
        Animal("Elephant", "🐘", "Trumpet", wild = true),
        Animal("Monkey", "🐵", "Ooh ooh ah ah", wild = true),
        Animal("Zebra", "🦓", "Neigh", wild = true),
        Animal("Giraffe", "🦒", "Hum", wild = true),
    )
}
