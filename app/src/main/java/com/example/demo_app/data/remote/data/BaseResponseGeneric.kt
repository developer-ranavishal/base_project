package app.beYou.data.remote.data

open class BaseResponseGeneric<T> {
    var message: String = ""
    var status: Boolean = false
    var data: T? = null
}