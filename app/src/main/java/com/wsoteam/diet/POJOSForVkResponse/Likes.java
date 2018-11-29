
package com.wsoteam.diet.POJOSForVkResponse;

import com.squareup.moshi.Json;

public class Likes {

    @Json(name = "count")
    private Integer count;
    @Json(name = "user_likes")
    private Integer userLikes;
    @Json(name = "can_like")
    private Integer canLike;
    @Json(name = "can_publish")
    private Integer canPublish;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getUserLikes() {
        return userLikes;
    }

    public void setUserLikes(Integer userLikes) {
        this.userLikes = userLikes;
    }

    public Integer getCanLike() {
        return canLike;
    }

    public void setCanLike(Integer canLike) {
        this.canLike = canLike;
    }

    public Integer getCanPublish() {
        return canPublish;
    }

    public void setCanPublish(Integer canPublish) {
        this.canPublish = canPublish;
    }

}
