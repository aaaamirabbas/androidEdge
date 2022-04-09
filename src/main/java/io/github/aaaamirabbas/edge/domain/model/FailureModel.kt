package io.github.aaaamirabbas.edge.domain.model

data class ErrorModel(
    val time: Long,
    val code: Int,
    val message: String
)