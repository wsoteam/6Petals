
package com.wsoteam.diet.POJOSForVkResponse;

import java.util.List;
import com.squareup.moshi.Json;

public class Response {

    @Json(name = "count")
    private Integer count;
    @Json(name = "items")
    private List<Item> items = null;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
