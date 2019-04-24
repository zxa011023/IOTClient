package com.example.zxa01.iotclient.detail.model;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.BaseObservable;
import android.util.Log;
import com.example.zxa01.iotclient.common.http.Api;
import com.example.zxa01.iotclient.common.pojo.device.Device;
import com.example.zxa01.iotclient.common.pojo.device.Manufacturer;
import com.example.zxa01.iotclient.common.pojo.device.Model;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailModel extends BaseObservable {

    private MutableLiveData<Device> deviceMLD = new MutableLiveData<>();

    public DetailModel() {
    }

    public void setDeviceMLD(Device device){
        deviceMLD.setValue(device);
    }

    public MutableLiveData<Device> getDeviceMLD(){
        return deviceMLD;
    }

    public void fetchDevice() {
        Callback<Object> callback = new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                // TODO transfer response
                setDeviceMLD(new Device()
                        .setUDN("a1252c49-4188-4e6d-a32e-66604c664fb8")
                        .setName("指尖式血氧機")
                        .setType(Device.Type.Sensor)
                        .setManufacturer(new Manufacturer()
                                .setName("Facelake")
                                .setSerialNumber("3176927193")
                                .setUrl("http://facelake.com"))
                        .setModel(new Model()
                                .setName("指尖式血氧機")
                                .setDescription("本設備是為符合不同領域及照護應用而設計，並把這些特色融入小如指節的分析儀中，可在數秒內量測出準確可靠的血氧及心跳值。")
                                .setUrl("https://www.amazon.com/Pulse-Oximeter-Blood-Oxygen-Monitor/dp/B00HXXO332"))
                        .setUPC("B00HXXO332")
                        .setLocation("25.013068, 121.541651")
                        .setStatus(Device.Status.Disconnected));
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Log.e("fetchDevice - onFailure()", t.getMessage(), t);
            }
        };

        Api.getApi().getDevice().enqueue(callback);

    }

}
