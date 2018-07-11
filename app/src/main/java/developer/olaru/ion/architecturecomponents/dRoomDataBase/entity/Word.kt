package developer.olaru.ion.architecturecomponents.dRoomDataBase.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "word_table")
 data class Word(
        @field:PrimaryKey
        @field:ColumnInfo(name = "word")
        val word: String)
