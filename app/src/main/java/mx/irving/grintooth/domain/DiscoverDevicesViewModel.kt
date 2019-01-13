package mx.irving.grintooth.domain

import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class DiscoverDevicesViewModel : ViewModel() {

    private val hasBluetoothSubject: PublishSubject<Boolean> = PublishSubject.create()
    var mobileHasBluetooth: Boolean = false
        set(value) {
            field = value
            hasBluetoothSubject.onNext(value)
        }

    fun getHasBluetooth(): Observable<Boolean> = hasBluetoothSubject.map { it }
}
