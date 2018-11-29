
package com.wsoteam.diet.POJOSForVkResponse;

import com.squareup.moshi.Json;

public class Example {

    @Json(name = "response")
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
