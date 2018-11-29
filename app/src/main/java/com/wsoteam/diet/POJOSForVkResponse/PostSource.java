
package com.wsoteam.diet.POJOSForVkResponse;

import com.squareup.moshi.Json;

public class PostSource {

    @Json(name = "type")
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
