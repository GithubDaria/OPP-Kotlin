package com.example.ooplab3

class Group(vararg students: Student) {
    private val studentList = students.toList()

    operator fun get(index: Int): Student {
        return studentList[index]
    }

    fun getTopStudent(): Student {
        return studentList.maxByOrNull { it.getAverage() } ?: throw NoSuchElementException("Group is empty")
    }
}
