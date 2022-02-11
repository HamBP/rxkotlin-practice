import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.observables.ConnectableObservable
import io.reactivex.rxkotlin.toObservable

fun main(args: Array<String>) {
    val observable: Observable<Int> = getResponse()
    observable.subscribe {
        println("received $it")
    }
}

fun getResponse() : Observable<Int> = listOf(2, 1, 3, 4, 5, 6).toObservable()

class TestObserver : Observer<Int> {
    override fun onSubscribe(d: Disposable) {
        println("TestObserver.onSubscribe")
    }

    override fun onNext(data: Int) {
        println("data = [${data}]")
    }

    override fun onError(e: Throwable) {
        println("TestObserver.onError")
    }

    override fun onComplete() {
        println("TestObserver.onComplete")
    }
}

fun observable1() {
    val list = listOf(2, 1, 3, 4, 5, 6)
    val observable = list.toObservable()
    val observer = object : Observer<Any> {
        override fun onSubscribe(d: Disposable) {
            println("new subscription")
            d.dispose()
        }
        override fun onNext(t: Any) {
            println("on next called! : $t")
        }
        override fun onError(e: Throwable) {
            println("error!")
        }
        override fun onComplete() {
            println("on complete")
        }
    }
    observable.subscribe(observer)
}

fun observable2() {
    val list = listOf(2, 1, 3, 4, 5, 6)
    val observable: Observable<Int> = list.toObservable()
    observable.subscribe {
        println("on next : $it")
    }
}

fun observable3() {
    val list = listOf(2, 1, 3, 4, 5, 6)
    val observable: Observable<Int> = Observable.fromIterable(list)
    observable.subscribe {
        println("on next : $it")
    }
}

fun observable4() {
    val observable = Observable.just(3, 2, 1, 4, 5)
    observable.subscribe {
        println("on next : $it")
    }
}

fun observable5() {
    val list = listOf(2, 1, 3, 4, 5, 6)
    val observable = list.toObservable().publish()
    observable.subscribe { println("on next called! : $it") }
    observable.connect()
    observable.subscribe { println("on next called!! : $it") }
}