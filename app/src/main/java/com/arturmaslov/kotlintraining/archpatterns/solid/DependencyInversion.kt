package com.arturmaslov.kotlintraining.archpatterns.solid

data class Book(val title: String, val generateNumber: String)

//class IsbnGenerator {
//    fun generateNumber(): String {
//        // Generate 13 digits ISBN Number
//        return "ISBN" + Math.abs(
//            kotlin.random.Random.nextLong(
//                1000000000000, 9999999999999
//            )
//        )
//    }
//}
//
//class PublicationService {
//    private val isbn = IsbnGenerator()
//    fun createBook(title: String): Book {
//        // what if you need ISSN
//        // probably, you
//        // will be creating one if-else block in createBook() to check which type of
//        // book ID you need to create
//        return Book(title, isbn.generateNumber())
//    }
//}

interface BookNumberGenerator {
    fun generateNumber(): String
}

class IsbnGenerator : BookNumberGenerator {
    override fun generateNumber(): String {
        // Generate 13 digits ISBN Number
        return "ISBN" + Math.abs(
            kotlin.random.Random.nextLong(
                1000000000000, 9999999999999
            )
        )
    }
}

class IssnGenerator : BookNumberGenerator {
    override fun generateNumber(): String {
        // Return 8 digits ISSN number
        return "ISSN" + Math.abs(
            kotlin.random.Random.nextInt(
                10000000, 99999999
            )
        )
    }
}

class PublicationService(
    private val bookNumberGenerator: BookNumberGenerator
) {
    fun createBook(title: String): Book {
        return Book(title, bookNumberGenerator.generateNumber())
    }
}

