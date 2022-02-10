import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun main(args: Array<String>) {
    errorHandling3()
}

fun errorHandling1() {
    val observable = Observable.range(1, 10).map { it / (3-it) }
    val observer = object : Observer<Int> {
        override fun onSubscribe(d: Disposable) {}
        override fun onNext(t: Int) {
            println("received $t")
        }
        override fun onError(e: Throwable) {
            println("error!")
        }
        override fun onComplete() {}
    }
    observable.subscribe (observer)
}

fun errorHandling2() {
    val observable = Observable.range(1, 10).map { it / (3-it) }.onErrorReturn { -1 }
    observable.subscribe {
        println("received $it")
    }
}

fun errorHandling3() {
    val observable = Observable.range(1, 10).map { it / (3-it) }.onErrorResumeNext( Observable.range(20, 5) )
    observable.subscribe {
        println("received $it")
    }
}