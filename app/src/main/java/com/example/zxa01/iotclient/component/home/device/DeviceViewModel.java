package com.example.zxa01.iotclient.component.home.device;

import com.example.zxa01.iotclient.R;
import com.example.zxa01.iotclient.common.pojo.device.Device;
import com.example.zxa01.iotclient.component.detail.DetailActivity;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import java.util.List;

public class DeviceViewModel extends ViewModel {

    public ObservableBoolean isLoading = new ObservableBoolean(true);
    private DeviceModel deviceModel = new DeviceModel();
    private DeviceAdapter adapter = new DeviceAdapter(R.layout.recycler_view_device, this);
    private Context context;

    public DeviceViewModel(Context context) {
        this.context = context;
    }

    /**
     * model
     */

    public void refreshDevices() {
        deviceModel.fetchDevices();
    }

    public MutableLiveData<List<Device>> observeDevicesMLD() {
        return deviceModel.getDevicesMLD();
    }

    /**
     * create
     */
    public void createDevice(String address) {
        deviceModel.createDevice(address);
        refreshDevices();
    }

    /**
     * child model
     */

    public Device getDeviceAt(Integer index) {
        if (deviceModel.getDevicesMLD().getValue() != null &&
                index != null &&
                deviceModel.getDevicesMLD().getValue().size() > index) {
            return deviceModel.getDevicesMLD().getValue().get(index);
        }
        return null;
    }

    public void onDevicesClick(Integer index) {
        if (deviceModel.getDevicesMLD().getValue() != null &&
                index != null &&
                deviceModel.getDevicesMLD().getValue().size() > index) {
            // TODO detail of device
            context.startActivity(
                    new Intent(context, DetailActivity.class)
                            .putExtra("index", index));
        }
    }

    /**
     * adapter
     */

    public DeviceAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(List<Device> devices) {
        this.isLoading.set(false);
        this.adapter.setDevices(devices);
        this.adapter.notifyDataSetChanged();
    }

}