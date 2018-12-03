package com.wsoteam.diet.BranchOfNews;

import android.util.Log;
import android.widget.Toast;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiGroups;
import com.vk.sdk.api.methods.VKApiWall;
import com.vk.sdk.api.model.VKList;
import com.wsoteam.diet.POJOSForVkResponse.Response;

import org.json.JSONException;

import java.io.IOException;

public class LoaderForResponseFromVk {

    private static final String ACCES_TOKEN = "28da670228da670228da6702e528bd27cc228da28da67027325b00cc2cf21e0c7892592";
    private static final int COUNT_OF_ITEM_FROM_RESPONSE = 100;

    public static void getResponseFromVK(String idOfGroup) {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Response> jsonAdapter = moshi.adapter(Response.class);
        VKRequest vkRequest = new VKApiGroups().getById(VKParameters.from(VKApiConst.GROUP_ID, idOfGroup, VKApiConst.ACCESS_TOKEN, ACCES_TOKEN));
        vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onError(VKError error) {
                super.onError(error);
            }

            @Override
            public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                super.attemptFailed(request, attemptNumber, totalAttempts);
            }

            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                //Toast.makeText(getActivity(), R.string.downloaded, Toast.LENGTH_SHORT).show();
                VKList vkList = (VKList) response.parsedModel;
                try {
                    final VKRequest vkRequestWall = new VKApiWall().get(VKParameters.from(VKApiConst.OWNER_ID,
                            "-" + vkList.get(0).fields.getInt("id"), VKApiConst.COUNT, COUNT_OF_ITEM_FROM_RESPONSE,
                            VKApiConst.ACCESS_TOKEN, ACCES_TOKEN));
                    vkRequestWall.executeWithListener(new VKRequest.VKRequestListener() {
                        @Override
                        public void onComplete(VKResponse response) {
                            super.onComplete(response);
                            try {
                                Log.i("RESPONSE", response.responseString);
                                Response responseVk = jsonAdapter.fromJson(response.json.getString("response"));
                                ObjectHolder objectHolder = new ObjectHolder();
                                objectHolder.createObject(responseVk);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
