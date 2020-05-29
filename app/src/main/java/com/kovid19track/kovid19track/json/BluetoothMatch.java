package com.kovid19track.kovid19track.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BluetoothMatch {
    public String userMessage;
    public BlueToothSeed[] seeds;

    public static BluetoothMatch parse(JSONObject obj) throws JSONException {
        BluetoothMatch bluetoothMatch = new BluetoothMatch();
        if (obj.has("seeds")) {
            JSONArray array = obj.getJSONArray("seeds");
            bluetoothMatch.seeds = new BlueToothSeed[array.length()];
            for (int i = 0; i < array.length(); i++) {
                bluetoothMatch.seeds[i] = BlueToothSeed.parse(array.getJSONObject(i));
            }
        }
        if (obj.has("userMessage")) {
            bluetoothMatch.userMessage = obj.getString("userMessage");
        }
        return bluetoothMatch;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject bluetooth_match = new JSONObject();
        JSONArray arr = new JSONArray();
        for (BlueToothSeed seed : seeds) {
            arr.put(seed.toJson());
        }
        bluetooth_match.put("seeds", arr);
        bluetooth_match.put("user_message", userMessage);
        return bluetooth_match;
    }
}
