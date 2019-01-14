package mx.irving.grintooth.mvvmview

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity<VM : ViewModel> : AppCompatActivity() {

    abstract val viewModelClazz: Class<VM>
    val viewModel: VM by lazy {
        ViewModelProviders.of(this).get(viewModelClazz)
    }
    private val compositeDisposable = CompositeDisposable()

    override fun onResume() {
        super.onResume()
        addDisposables()
    }

    override fun onPause() {
        compositeDisposable.dispose()
        super.onPause()
    }

    abstract fun addDisposables()

    fun addDisposable(disposable: Disposable) {
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.add(disposable)
        }
    }
}
