import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable

fun main(args: Array<String>) {
    val list:List<Any> = listOf("One", 2, "Three", "Four", 4.5)
    val observable: Observable<Any> = list.toObservable()

    observable.subscribeBy(
        onNext = { println(it) }
    )
}