package io.github.aaaamirabbas.edge.ext

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

fun Disposable.addTo(cd: CompositeDisposable) {
    cd.add(this)
}

fun <F> runAfter(
    delay: Long, total: Long, fn: (Long) -> F, fc: () -> F,
    unit: TimeUnit = TimeUnit.MILLISECONDS
): Disposable {
    return Flowable.interval(0, delay, unit)
        .observeOn(AndroidSchedulers.mainThread())
        .takeWhile { it != total }
        .doOnNext { fn(it) }
        .doOnComplete { fc() }
        .subscribe()
}

fun <F> runAfter(
    delay: Long, fx: () -> F, unit: TimeUnit = TimeUnit.MILLISECONDS
): Disposable {
    return Completable.timer(delay, unit)
        .observeOn(AndroidSchedulers.mainThread())
        .doOnComplete { fx() }
        .subscribe()
}


fun <T> Flowable<T>.ioThread(): Flowable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.ioThread(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Maybe<T>.ioThread(): Maybe<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.ioThread(): Single<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Completable.ioThread(): Completable {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Flowable<T>.newThread(): Flowable<T> {
    return this.subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.newThread(): Observable<T> {
    return this.subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Maybe<T>.newThread(): Maybe<T> {
    return this.subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.newThread(): Single<T> {
    return this.subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Completable.newThread(): Completable {
    return this.subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
}