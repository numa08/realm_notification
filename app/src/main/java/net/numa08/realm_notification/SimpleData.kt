package net.numa08.realm_notification

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class SimpleData(
        @PrimaryKey
        var primaryKey: String = "",
        var date: Date = Date()
) : RealmObject()