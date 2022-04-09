package io.github.aaaamirabbas.edge.domain.provider.local


open class ObjectPool  {

    private val poolMap = hashMapOf<String, Any>()

    fun put(key: String, obj: Any) {
        poolMap[key] = obj
    }

    fun get(key: String): Any? {
        return poolMap.getOrDefault(key, null)
    }

    fun eraseAllData() {
        poolMap.clear()
    }
}

