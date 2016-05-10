package org.logitech.android.testapp.device;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import org.logitech.android.testapp.model.Devices;
import org.logitech.android.testapp.utils.HttpHelper;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 */
public class DevicesInteractor {

    private IDevicesPresenter presenter;

    public DevicesInteractor(IDevicesPresenter devicesPresenter) {
        this.presenter = devicesPresenter;
    }

    /**
     * Method creates separate thread and fetches data from server.
     */
    public void loadDeviceData() {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                HttpHelper httpHelper = new HttpHelper();
                ArrayList<Devices> deviceList = null;
                try {
                    deviceList = httpHelper.performGetRequest();
                } catch (IOException e) {
                    Message msgObj = handler.obtainMessage();
                    Bundle b = new Bundle();
                    b.putInt("error", 0);
                    b.putString("errorMsg", e.getMessage());
                    msgObj.setData(b);
                    handler.sendMessage(msgObj);
                }

                Message msgObj = handler.obtainMessage();
                Bundle b = new Bundle();
                b.putInt("error", 1);
                b.putParcelableArrayList("device_list", deviceList);
                msgObj.setData(b);
                handler.sendMessage(msgObj);
            }

            private final Handler handler = new Handler() {

                public void handleMessage(Message msg) {

                    if (msg.getData().getInt("error") == 0) {
                        presenter.onDataLoadError();
                    } else {
                        ArrayList<Devices> deviceList = msg.getData().getParcelableArrayList("device_list");
                        presenter.onDeviceDataLoad(deviceList);
                    }
                }
            };
        });
        thread.start();
    }
}
