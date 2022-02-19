import io.reactivex.Observable
import io.reactivex.observables.ConnectableObservable
import io.reactivex.rxkotlin.toObservable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val subject: Subject<Int> = PublishSubject.create()
    val observable = listOf(1, 2, 3).toObservable()

    subject.subscribe {
        println("subject $it")
    }

    observable.subscribe(subject)
}

fun coldObservable() {
    val coldObservable: Observable<Int> = Observable.just(1, 2, 3)

    coldObservable.subscribe {
        println("cold 1 : $it")
    }

    coldObservable.subscribe {
        println("cold 2 : $it")
    }
}

fun hotObservable() {
    val hotObservable: ConnectableObservable<Int> = Observable.just(1, 2, 3).publish()

    hotObservable.subscribe {
        println("hot 1 : $it")
    }

    hotObservable.subscribe {
        println("hot 2 : $it")
    }

    hotObservable.connect()

    hotObservable.subscribe {
        println("hot 2 : $it")
    }
}

fun subject1() {
    val subject: Subject<Int> = PublishSubject.create()

    subject.subscribe {
        println("subject $it")
    }

    subject.onNext(1)
    subject.onNext(2)
    subject.onNext(3)
    subject.onComplete()
}