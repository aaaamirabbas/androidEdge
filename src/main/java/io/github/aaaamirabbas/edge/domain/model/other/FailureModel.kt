package io.github.aaaamirabbas.edge.domain.model.other

data class FailureModel(
    val time: Long,
    val code: Int,
    val data: Any?
)