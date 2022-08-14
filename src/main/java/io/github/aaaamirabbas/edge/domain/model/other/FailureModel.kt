package io.github.aaaamirabbas.edge.domain.model.other

import androidx.annotation.Keep

@Keep
data class FailureModel(
    val code: Int,
    val message: String
)