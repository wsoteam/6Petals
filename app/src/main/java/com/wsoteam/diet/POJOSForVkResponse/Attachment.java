
package com.wsoteam.diet.POJOSForVkResponse;

import com.squareup.moshi.Json;

public class Attachment {

    @Json(name = "type")
    private String type;
    @Json(name = "photo")
    private Photo photo;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

}
