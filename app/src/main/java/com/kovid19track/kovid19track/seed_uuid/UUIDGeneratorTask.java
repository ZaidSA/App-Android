package com.kovid19track.kovid19track.seed_uuid;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.kovid19track.kovid19track.R;

import java.util.UUID;

import com.kovid19track.kovid19track.ble.BluetoothUtils;
import com.kovid19track.kovid19track.utils.Constants;
import com.kovid19track.kovid19track.utils.CryptoUtils;

public class UUIDGeneratorTask implements Runnable {

    Context context;

    public UUIDGeneratorTask(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        if (Constants.EnableUUIDGeneration) {
            Log.e("crypto","generate uuid");
            // get the most recently generated seed
            SharedPreferences prefs = context.getSharedPreferences(Constants.SHARED_PREFENCE_NAME, Context.MODE_PRIVATE);
            long mostRecentSeedTimestamp = prefs.getLong(context.getString(R.string.most_recent_seed_timestamp_pkey), 0);

            Log.e("crypto","most recent seed timestamp "+mostRecentSeedTimestamp);
            UUID uuid = CryptoUtils.generateSeedHelperWithMostRecent(context, mostRecentSeedTimestamp);

            Log.e("ble", "changing contact uuid now");
            if (uuid != null) {
                Log.e("ble", "changing contact uuid now to " + uuid.toString());
                Constants.contactUUID = UUID.fromString(uuid.toString());
            }

            Log.e("ble", "can we broadcast?" + (Constants.blueAdapter != null));
            if (Constants.blueAdapter != null) {
                Log.e("ble", "is advertiser null?" + (Constants.blueAdapter.getBluetoothLeAdvertiser() == null));
            }
            if (Constants.blueAdapter != null && Constants.blueAdapter.getBluetoothLeAdvertiser() != null) {
                Log.e("ble", "about to stop advertising");
                Constants.blueAdapter.getBluetoothLeAdvertiser().stopAdvertising(BluetoothUtils.advertiseCallback);
                //restart beacon after UUID generation
                Log.e("ble", "about to mkbeacon");
                BluetoothUtils.mkBeacon(context);
            }
        }
    }
}
