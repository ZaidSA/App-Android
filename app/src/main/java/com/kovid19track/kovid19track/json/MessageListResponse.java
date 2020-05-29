package com.kovid19track.kovid19track.json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageListResponse {
    public MessageInfo[] messageInfo;
    public long query_time;
    public long maxResponseTimestamp = 0;

    public static MessageListResponse parse(JSONObject obj) throws JSONException {
        MessageListResponse messageListResponse = new MessageListResponse();
        if (obj.has("messageInfoes")) {
            JSONArray arr = obj.getJSONArray("messageInfoes");
            messageListResponse.messageInfo = new MessageInfo[arr.length()];
            for (int i = 0; i < arr.length(); i++) {
                messageListResponse.messageInfo[i] = MessageInfo.parse(arr.getJSONObject(i));
            }
        }
        if (obj.has("query_time")) {
            messageListResponse.query_time = obj.getLong("query_time");
        }
        if (obj.has("maxResponseTimestamp")) {
            messageListResponse.maxResponseTimestamp = obj.getLong("maxResponseTimestamp");
        }
        return messageListResponse;
    }
}
