package jp.tokushima.tatsuhama.realmmodellib.model

import io.realm.RealmObject

open class Book(var name: String? = null, var price: Int = 0) : RealmObject()
