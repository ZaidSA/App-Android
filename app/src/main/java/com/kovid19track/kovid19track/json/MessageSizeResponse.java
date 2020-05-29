package com.kovid19track.kovid19track.json;

import org.json.JSONException;
import org.json.JSONObject;

public class MessageSizeResponse {

    public int sizeOfQueryResponse;

    public static MessageSizeResponse parse(JSONObject obj) throws JSONException {
        MessageSizeResponse response = new MessageSizeResponse();
        if (obj.has("sizeOfQueryResponse")) {
            response.sizeOfQueryResponse = obj.getInt("sizeOfQueryResponse");
        }
        return response;
    }
}
