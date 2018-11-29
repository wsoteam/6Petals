
package com.wsoteam.diet.POJOSForVkResponse;

import com.squareup.moshi.Json;

public class Photo {

    @Json(name = "id")
    private Integer id;
    @Json(name = "album_id")
    private Integer albumId;
    @Json(name = "owner_id")
    private Integer ownerId;
    @Json(name = "user_id")
    private Integer userId;
    @Json(name = "photo_75")
    private String photo75;
    @Json(name = "photo_130")
    private String photo130;
    @Json(name = "photo_604")
    private String photo604;
    @Json(name = "photo_807")
    private String photo807;
    @Json(name = "photo_1280")
    private String photo1280;
    @Json(name = "width")
    private Integer width;
    @Json(name = "height")
    private Integer height;
    @Json(name = "text")
    private String text;
    @Json(name = "date")
    private Integer date;
    @Json(name = "post_id")
    private Integer postId;
    @Json(name = "access_key")
    private String accessKey;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhoto75() {
        return photo75;
    }

    public void setPhoto75(String photo75) {
        this.photo75 = photo75;
    }

    public String getPhoto130() {
        return photo130;
    }

    public void setPhoto130(String photo130) {
        this.photo130 = photo130;
    }

    public String getPhoto604() {
        return photo604;
    }

    public void setPhoto604(String photo604) {
        this.photo604 = photo604;
    }

    public String getPhoto807() {
        return photo807;
    }

    public void setPhoto807(String photo807) {
        this.photo807 = photo807;
    }

    public String getPhoto1280() {
        return photo1280;
    }

    public void setPhoto1280(String photo1280) {
        this.photo1280 = photo1280;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

}
