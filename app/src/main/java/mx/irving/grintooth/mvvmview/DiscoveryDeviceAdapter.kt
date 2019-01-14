package mx.irving.grintooth.mvvmview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.discovery_device_item.view.*
import mx.irving.grintooth.R
import mx.irving.grintooth.mvvmdata.Device
import mx.irving.grintooth.utils.UNKNOWN

class DiscoveryDeviceAdapter : RecyclerView.Adapter<DiscoveryDeviceAdapter.DeviceViewHolder>() {

    val devices: MutableList<Device> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.discovery_device_item, parent, false)
        return DeviceViewHolder(view)
    }

    override fun getItemCount(): Int = devices.size

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = devices[position]
        holder.name.text = if (device.name.isBlank()) UNKNOWN else device.name
        holder.strength.text = device.strength
        holder.saveButton.setOnClickListener {

        }
    }

    inner class DeviceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.discoveryDeviceName
        val strength = itemView.discoveryDeviceStrength
        val saveButton = itemView.discoveryDeviceSaveButton
    }
}
