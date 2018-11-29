
package com.wsoteam.diet.POJOSForVkResponse;

import com.squareup.moshi.Json;

public class Comments {

    @Json(name = "count")
    private Integer count;
    @Json(name = "can_post")
    private Integer canPost;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCanPost() {
        return canPost;
    }

    public void setCanPost(Integer canPost) {
        this.canPost = canPost;
    }

}
