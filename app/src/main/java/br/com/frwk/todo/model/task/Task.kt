package br.com.frwk.todo.model.task

import com.vicpin.krealmextensions.AutoIncrementPK
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.*
import java.io.Serializable

@RealmClass
@AutoIncrementPK
open class Task(
    @PrimaryKey
    var id: Long? = null,
    var description: String = "",
    var date: Long = Date().time,
    var status: Boolean = false
) : RealmObject(), Serializable