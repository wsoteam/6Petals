package com.wsoteam.diet.BranchOfNews;

import android.util.Log;

import com.wsoteam.diet.POJOSForVkResponse.Response;

public class ObjectHolder {

    private static Response responseVk;
    private static String TYPE_PHOTO = "photo";


    public void createObject(Response response) {
        responseVk = postClearing(clearingResponse(response));
        //responseVk = clearingResponse(response);
        //responseVk = response;
    }


    private Response clearingResponse(Response response) {
        for (int i = 0; i < response.getItems().size(); i++) {


            try {
                if (!response.getItems().get(i).getAttachments().get(0).getType().equals(TYPE_PHOTO)
                        || response.getItems().get(i).getMarkedAsAds() == 1
                        /*|| response.getItems().get(i).getAttachments().get(0).getPhoto().getPhoto1280() == null*/) {
                    response.getItems().remove(i);
                    Log.i("LOL", "remove - " + String.valueOf(i));
                }
            } catch (NullPointerException e) {
                response.getItems().remove(i);
            }
        }

        return response;
    }

    private Response postClearing(Response response) {
        for (int i = 0; i < response.getItems().size(); i++) {
            if (!response.getItems().get(i).getAttachments().get(0).getType().equals(TYPE_PHOTO) || response.getItems().get(i).getMarkedAsAds() == 1) {
                response.getItems().remove(i);
                Log.i("LOL", "remove - " + String.valueOf(i));
            }

        }

        return response;
    }

    public static Response getResponseVk() {
        return responseVk;
    }
}
