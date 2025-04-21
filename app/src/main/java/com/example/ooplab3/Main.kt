package com.example.ooplab3

import kotlinx.coroutines.*

suspend fun fetchGradesFromServer(): List<Int> {
    delay(2000)
    println("Grades fetched from server.")
    return listOf(90, 85, 88, 92, 94)
}
fun main() = runBlocking {
    val alice = Student("Alice", 20, listOf(85, 90, 88))
    val bob = Student("Bob", 21, listOf(75, 80, 70))
    val charlie = Student("Charlie", 17, listOf(92, 95, 89))

    val dave = Student("Dave")
    println("Created student: ${dave.name}, Age: ${dave.age}")

    val combinedStudent = alice + bob
    println("Combined student grades average: ${combinedStudent.getAverage()}")

    val scaledStudent = alice * 2
    println("Scaled grades average: ${scaledStudent.getAverage()}")

    println("Is Alice equal to Bob? ${alice == bob}")

    println("${alice.name} is an ${alice.status}")

    println("Fetching grades for ${charlie.name} asynchronously...")

    val gradesDeferred = async { fetchGradesFromServer() }
    val grades = gradesDeferred.await()

    charlie.updateGrades(grades)
    println("Charlie's updated average grade: ${charlie.getAverage()}")

    val group = Group(alice, bob, charlie, dave)
    println("Top student in the group: ${group.getTopStudent().name}")
    println("Student at index 2: ${group[2].name}")
}


