package developer.olaru.ion.architecturecomponents.D_room_data_base.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "word_table")
class Word(@field:PrimaryKey
           @field:ColumnInfo(name = "word")
           val word: String)
