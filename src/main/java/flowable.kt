import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.rxkotlin.toFlowable
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val observable = Observable.interval(100, TimeUnit.MILLISECONDS)
    val flowable = observable.toFlowable( BackpressureStrategy.BUFFER)
    flowable.subscribe {
        runBlocking { delay(200) }
        println("received $it")
    }

    runBlocking {
        delay(3000)
    }
}