package mx.irving.grintooth.utils

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Observable<T>.applyOnBackground(): Observable<T> =
        subscribeOn(Schedulers.io()).observeOn(Schedulers.io())

fun <T> Observable<T>.applyOnUi(): Observable<T> =
        subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
