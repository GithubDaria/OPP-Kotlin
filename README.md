## Класс Student
```
class Student(name: String, age: Int, grades: List<Int>) {

    private var _name: String = name.trim().replaceFirstChar { it.uppercase() }
    private var _age: Int = if (age >= 0) age else 0
    private var _grades: List<Int> = grades

    var name: String
        get() = _name
        set(value) {
            _name = value.trim().replaceFirstChar { it.uppercase() }
        }

    var age: Int
        get() = _age
        set(value) {
            if (value >= 0) _age = value
        }
    val isAdult: Boolean
        get() = _age >= 18

    val status: String by lazy {
        if (isAdult) "Adult" else "Minor"
    }
    init {
        println("Student '$name' created with age $age and grades $grades")
    }

    constructor(name: String) : this(name, 0, emptyList())

    fun getAverage(): Double {
        return if (_grades.isNotEmpty()) _grades.average() else 0.0
    }
    fun processGrades(operation: (Int) -> Int) {
        _grades = _grades.map(operation)
    }
    fun updateGrades(grades: List<Int>) {
        _grades = grades
    }
    operator fun plus(other: Student): Student {
        val combinedGrades = _grades + other._grades
        return Student(this.name, this.age, combinedGrades)
    }
    operator fun times(multiplier: Int): Student {
        val scaledGrades = _grades.map { it * multiplier }
        return Student(this.name, this.age, scaledGrades)
    }
    override operator fun equals(other: Any?): Boolean {
        return other is Student &&
                this.name == other.name &&
                this.getAverage() == other.getAverage()
    }

}

```

## Клас Group
```
class Group(vararg students: Student) {
    private val studentList = students.toList()

    operator fun get(index: Int): Student {
        return studentList[index]
    }

    fun getTopStudent(): Student {
        return studentList.maxByOrNull { it.getAverage() } ?: throw NoSuchElementException("Group is empty")
    }
}
```
