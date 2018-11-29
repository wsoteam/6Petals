
package com.wsoteam.diet.POJOSForVkResponse;

import com.squareup.moshi.Json;

public class Reposts {

    @Json(name = "count")
    private Integer count;
    @Json(name = "user_reposted")
    private Integer userReposted;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getUserReposted() {
        return userReposted;
    }

    public void setUserReposted(Integer userReposted) {
        this.userReposted = userReposted;
    }

}
