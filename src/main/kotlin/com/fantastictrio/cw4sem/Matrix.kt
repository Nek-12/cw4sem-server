package com.nek.decisionmaker.util

interface Matrix<T> {
    val rowNames: List<String>
    val columns: Int
    val rows
        get() = rowNames.size
    fun getValue(row: Int, column: Int): T?
    fun getRowName(index: Int): String
    fun asLists(nullValue: T): List<List<T>>
}

class MutableMatrix <T>(
    rowNames: List<String>,
    columns: Int,
    values: T? = null
): Matrix<T> {
    override var rowNames = rowNames
    private set

    override var columns = columns
    private set

    var data = mutableListOf<MutableList<T?>>()

    init {
        fill(rowNames, columns, values)
    }

    fun fill(rows: List<String>, columns: Int, with: T? = null) {
        data = MutableList(columns) { MutableList(rows.size) { with } }
        rowNames = rows
        this.columns = columns
    }

    fun setValue(row: Int, column: Int, value: T?) {
        data[column][row] = value
    }

    override fun getValue(row: Int, column: Int): T? {
        return data[column][row]
    }

    private fun clear() {
        fill(rowNames, columns)
    }

    override fun getRowName(index: Int): String {
        return rowNames[index]
    }

    override fun asLists(nullValue: T): List<List<T>> {
        return data.map { column ->
            column.map {
                it ?: nullValue
            }
        }
    }
}
