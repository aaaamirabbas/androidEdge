package io.github.aaaamirabbas.edge.base.data

import androidx.room.*

@Dao
interface BaseDao<T> {

    @Insert
    fun insert(vararg obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(objList: List<T>)

    @Delete
    fun delete(vararg obj: T)

    @Delete
    fun deleteList(objList: List<T>)

    @Update
    fun update(vararg obj: T)

    @Update
    fun updateList(objList: List<T>)
}