package mx.irving.grintooth.mvvmview

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_service_devices.*
import mx.irving.grintooth.R
import mx.irving.grintooth.mvvmdata.Device
import mx.irving.grintooth.mvvmviewmodels.ServiceDevicesViewModel
import mx.irving.grintooth.utils.applyOnUi
import timber.log.Timber

class ServiceDevicesActivity : BaseActivity<ServiceDevicesViewModel>() {

    override val viewModelClazz = ServiceDevicesViewModel::class.java
    private val serviceDevicesAdapter by lazy { ServiceDevicesAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_devices)
        serviceDevicesRecyclerView.layoutManager = LinearLayoutManager(this)
        serviceDevicesRecyclerView.adapter = serviceDevicesAdapter
    }

    override fun addDisposables() {
        addDisposable(
                viewModel.getDevices()
                        .doOnSubscribe { serviceDevicesLoader.visibility = View.VISIBLE }
                        .applyOnUi()
                        .subscribe(
                                {
                                    if (it?.value != null) drawDevices(it.value)
                                    else showError()
                                },
                                {
                                    showError()
                                    Timber.e(it)
                                }
                        )
        )
    }

    private fun drawDevices(devices: List<Device>) {
        serviceDevicesLoader.visibility = View.GONE
        serviceDevicesAdapter.devices.clear()
        serviceDevicesAdapter.devices.addAll(devices)
        serviceDevicesAdapter.notifyDataSetChanged()
    }

    private fun showError() {
        serviceDevicesLoader.visibility = View.GONE
        Toast.makeText(this, R.string.general_service_error, Toast.LENGTH_SHORT).show()
    }
}
