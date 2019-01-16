package mx.irving.grintooth.mvvmview

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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
                                    drawDevices(it)
                                },
                                {
                                    showError()
                                    Timber.e(it)
                                }
                        )
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.service_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionSort -> {
                addDisposable(
                        viewModel.getDevicesSorted()
                                .applyOnUi()
                                .subscribe(
                                        {
                                            drawDevices(it)
                                        },
                                        {
                                            showError()
                                            Timber.e(it)
                                        }
                                )
                )
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
