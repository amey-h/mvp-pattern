package org.logitech.android.testapp.utils;

import android.util.Log;

import com.google.gson.Gson;

import org.logitech.android.testapp.BuildConfig;
import org.logitech.android.testapp.model.DeviceResponse;
import org.logitech.android.testapp.model.Devices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 */
public class HttpHelper {

    private static String TAG = HttpHelper.class.getSimpleName();

    public ArrayList<Devices> performGetRequest() throws IOException {
        ArrayList<Devices> deviceList = new ArrayList<Devices>();
        try {
            URL url = new URL(BuildConfig.URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Log.w(TAG, "Some Error Occured");
            } else {
                // get the data length
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                br.close();
                String result = sb.toString();
                Log.d(TAG, result);
                Gson gson = new Gson();
                DeviceResponse deviceResponse = gson.fromJson(result, DeviceResponse.class);
                deviceList = deviceResponse.getDevices();
            }
        } catch (IOException ie) {
            throw ie;
        }
        return deviceList;
    }
}
