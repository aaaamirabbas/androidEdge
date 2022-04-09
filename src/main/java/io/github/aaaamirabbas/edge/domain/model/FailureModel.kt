package io.github.aaaamirabbas.edge.domain.model

data class FailureModel(
    val time: Long,
    val code: Int,
    val data: Any?
)